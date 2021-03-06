package nz.co.fortytwo.signalk.artemis.intercept;

import static nz.co.fortytwo.signalk.artemis.util.Config.INCOMING_RAW;

import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.ICoreMessage;
import org.apache.activemq.artemis.api.core.Interceptor;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.core.protocol.core.Packet;
import org.apache.activemq.artemis.core.protocol.core.impl.wireformat.SessionSendMessage;
import org.apache.activemq.artemis.spi.core.protocol.RemotingConnection;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mjson.Json;
import nz.co.fortytwo.signalk.artemis.util.Config;
import nz.co.fortytwo.signalk.artemis.util.SecurityUtils;
import nz.co.fortytwo.signalk.artemis.util.Util;

/**
 * Remove rubbish messages, add some headers to categorize message
 * 
 * @author robert
 *
 */
public class GarbageInterceptor extends BaseInterceptor implements Interceptor {

	private static Logger logger = LogManager.getLogger(GarbageInterceptor.class);

	@Override
	public boolean intercept(Packet packet, RemotingConnection connection) throws ActiveMQException {
		
		if (packet instanceof SessionSendMessage) {
			SessionSendMessage realPacket = (SessionSendMessage) packet;

			ICoreMessage msg = realPacket.getMessage();
			if(msg instanceof ClientMessage) {
				((ClientMessage)msg).acknowledge();
			}
			if(!StringUtils.equals(msg.getAddress(), INCOMING_RAW))return true;
			if(msg.getStringProperty(Config.AMQ_CONTENT_TYPE)!=null) {
				return true;
			}
			
			String msgType = getContentType(msg);
			if (logger.isDebugEnabled())
				logger.debug("Msg type is: {}", msgType);
			
			if (msgType != null) {
				msg.putStringProperty(Config.AMQ_CONTENT_TYPE, msgType);
			} else {
				return false;
			}

			return true;
		}
		return true;
	}

	public String getContentType(ICoreMessage message) {
		if (logger.isDebugEnabled()) {
			logger.debug("Msg class: {}", message.getClass());
		}

		String msg = Util.readBodyBufferToString(message);

		if (logger.isDebugEnabled()) {
			logger.debug("Msg class: {}, body is:{}", message.getClass(),msg);
		}

		if (msg != null) {
			msg = msg.trim();
			// stomp messages are prefixed with 'ascii:'
			if (msg.startsWith("ascii:")) {
				return Config.STOMP;
			}
			msg = StringUtils.chomp(msg);
			if (msg.startsWith("!AIVDM")) {
				// AIS
				// !AIVDM,1,1,,B,15MwkRUOidG?GElEa<iQk1JV06Jd,0*6D
				return Config.AIS;
			} else if (msg.startsWith("$")) {
				return Config._0183;
			} else if (msg.startsWith("{") && msg.endsWith("}")) {
				Json node = Json.read(msg);
				if(isN2k(node)) {
					return Config.N2K;
				}
				if (isFullFormat(node))
					return Config.JSON_FULL;
				if (isAuth(node))
					return Config.JSON_AUTH;
				if (isGet(node)) {
					//if the message has a token, inject into header
					SecurityUtils.injectTokenFromMessage(message, node);
					return Config.JSON_GET;
				}
				if (isDelta(node)) {
					SecurityUtils.injectTokenFromMessage(message, node);
					return Config.JSON_DELTA;
				}
				if (isSubscribe(node)) {
					SecurityUtils.injectTokenFromMessage(message, node);
					return Config.JSON_SUBSCRIBE;
				}
				node.clear(true);
			}
		}
		if (logger.isWarnEnabled()) {
			logger.warn("Msg is garbage: {}", msg);
		}
		return null;
	}


}
