package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwt;
    private String captchaToken;

    // need default constructor for JSON Parsing
    public JwtResponse() {
    }

    public JwtResponse(String jwt, String captchaToken) {
        this.jwt = jwt;
        this.captchaToken = captchaToken;
    }
}
