package nz.co.fortytwo.signalk.artemis.transformer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.apache.activemq.artemis.api.core.RoutingType;
import org.apache.activemq.artemis.api.core.client.ClientConsumer;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientProducer;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import nz.co.fortytwo.signalk.artemis.server.BaseServerTest;
import nz.co.fortytwo.signalk.artemis.util.Config;
import nz.co.fortytwo.signalk.artemis.util.SecurityUtils;
import nz.co.fortytwo.signalk.artemis.util.Util;

public class DeltaToKvTest extends BaseServerTest{
	
	private static Logger logger = LogManager.getLogger(DeltaToKvTest.class);

	
	@Test
	public void shouldReadKvQueue() throws Exception {
		readPartialKeys("admin", 2);
	}
	
	

	private void readPartialKeys(String user, int expected) throws Exception{
		try (ClientSession session = Util.getLocalhostClientSession("admin", "admin");
				ClientProducer producer = session.createProducer();	
				ClientConsumer consumer = session.createConsumer(Config.INTERNAL_KV);){
			session.start();
		
			String body = FileUtils.readFileToString(new File("./src/test/resources/samples/delta/docs-data_model_multiple_values.json"));
			//sendSubsribeMsg(session,producer, "vessels." + self, "navigation","kvQ");
			String token = SecurityUtils.authenticateUser("admin", "admin");
			sendMessage(session, producer, body, token);
			
			logger.debug("Input sent");
		
			logger.debug("Receive started");
			List<ClientMessage> replies = listen(session, consumer, Config.INTERNAL_KV, 3, 20);
			//assertEquals(expected, replies.size());
			logger.debug("Received {} replies", replies.size());
			replies.forEach((m)->{
				logger.debug("Received {}", m);
			});
			assertTrue(replies.size()>=expected);
		} 
	}

	

	

}
