package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;

    // need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
