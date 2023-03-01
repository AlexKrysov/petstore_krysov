package com.krysov.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;
    private Integer userStatus;
    private String username;
    private String    firstName;
    private String   lastName;
    private String   email;
    private String   password;
    private String   phone;
}