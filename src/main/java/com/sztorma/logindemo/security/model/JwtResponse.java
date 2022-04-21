package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwt;
    private boolean captchaRequired;

    // need default constructor for JSON Parsing
    public JwtResponse() {
    }

    public JwtResponse(String jwt, boolean captchaRequired) {
        this.jwt = jwt;
        this.captchaRequired = captchaRequired;
    }
}
