package com.globant.bootcamp.dia15.component;

import org.springframework.stereotype.Component;

@Component
public class ClientCredentials {
    private String username;
    private String password;

    public ClientCredentials() {
    }

    public ClientCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
