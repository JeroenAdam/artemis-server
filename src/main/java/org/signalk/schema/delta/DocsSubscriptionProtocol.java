
package org.signalk.schema.delta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "context",
    "updates"
})
public class DocsSubscriptionProtocol {

    @JsonProperty("context")
    public String context;
    @JsonProperty("updates")
    public List<Update______> updates = new ArrayList<Update______>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DocsSubscriptionProtocol withContext(String context) {
        this.context = context;
        return this;
    }

    public DocsSubscriptionProtocol withUpdates(List<Update______> updates) {
        this.updates = updates;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public DocsSubscriptionProtocol withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("context", context).append("updates", updates).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(context).append(additionalProperties).append(updates).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DocsSubscriptionProtocol) == false) {
            return false;
        }
        DocsSubscriptionProtocol rhs = ((DocsSubscriptionProtocol) other);
        return new EqualsBuilder().append(context, rhs.context).append(additionalProperties, rhs.additionalProperties).append(updates, rhs.updates).isEquals();
    }

}