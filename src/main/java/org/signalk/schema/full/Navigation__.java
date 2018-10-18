
package org.signalk.schema.full;

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
    "position",
    "courseOverGroundTrue"
})
public class Navigation__ {

    @JsonProperty("position")
    public Position__ position;
    @JsonProperty("courseOverGroundTrue")
    public CourseOverGroundTrue__ courseOverGroundTrue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Navigation__ withPosition(Position__ position) {
        this.position = position;
        return this;
    }

    public Navigation__ withCourseOverGroundTrue(CourseOverGroundTrue__ courseOverGroundTrue) {
        this.courseOverGroundTrue = courseOverGroundTrue;
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

    public Navigation__ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("position", position).append("courseOverGroundTrue", courseOverGroundTrue).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(courseOverGroundTrue).append(position).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Navigation__) == false) {
            return false;
        }
        Navigation__ rhs = ((Navigation__) other);
        return new EqualsBuilder().append(courseOverGroundTrue, rhs.courseOverGroundTrue).append(position, rhs.position).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
