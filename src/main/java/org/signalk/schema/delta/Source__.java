
package org.signalk.schema.delta;

import java.util.HashMap;
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
    "label",
    "type",
    "talker",
    "sentence",
    "src",
    "pgn"
})
public class Source__ {

    @JsonProperty("label")
    public String label;
    @JsonProperty("type")
    public String type;
    @JsonProperty("talker")
    public String talker;
    @JsonProperty("sentence")
    public String sentence;
    @JsonProperty("src")
    public String src;
    @JsonProperty("pgn")
    public Integer pgn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Source__ withLabel(String label) {
        this.label = label;
        return this;
    }

    public Source__ withType(String type) {
        this.type = type;
        return this;
    }

    public Source__ withTalker(String talker) {
        this.talker = talker;
        return this;
    }

    public Source__ withSentence(String sentence) {
        this.sentence = sentence;
        return this;
    }

    public Source__ withSrc(String src) {
        this.src = src;
        return this;
    }

    public Source__ withPgn(Integer pgn) {
        this.pgn = pgn;
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

    public Source__ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("label", label).append("type", type).append("talker", talker).append("sentence", sentence).append("src", src).append("pgn", pgn).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sentence).append(talker).append(src).append(pgn).append(label).append(additionalProperties).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Source__) == false) {
            return false;
        }
        Source__ rhs = ((Source__) other);
        return new EqualsBuilder().append(sentence, rhs.sentence).append(talker, rhs.talker).append(src, rhs.src).append(pgn, rhs.pgn).append(label, rhs.label).append(additionalProperties, rhs.additionalProperties).append(type, rhs.type).isEquals();
    }

}
