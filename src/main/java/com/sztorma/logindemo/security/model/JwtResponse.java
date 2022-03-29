package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class JwtResponse {
    private String jwttoken;

    // need default constructor for JSON Parsing
    public JwtResponse() {
    }

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
