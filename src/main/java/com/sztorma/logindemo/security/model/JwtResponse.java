package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class JwtResponse {
    private String jwt;

    // need default constructor for JSON Parsing
    public JwtResponse() {
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
