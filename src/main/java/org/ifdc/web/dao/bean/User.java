package org.ifdc.web.dao.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 *
 * @author Meng Zhang
 */
@Value // All fields are private and final. Getters (but not setters) are generated (https://projectlombok.org/features/Value.html)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("userName") String userName;
    @JsonProperty("salt") String salt;
    @JsonProperty("hashedPassword") String hashedPassword;
}
