package org.satish.ecommerce.authservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class User {

    private String username;

    private String password;

    private Set<String> roles;

    private boolean enabled;

    private boolean accountLocked;

    private boolean accountExpired;

    private boolean credentialsExpired;

}
