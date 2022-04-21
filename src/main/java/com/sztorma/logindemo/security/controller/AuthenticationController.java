package com.sztorma.logindemo.security.controller;

import com.sztorma.logindemo.security.facade.AuthenticationFacade;
import com.sztorma.logindemo.security.model.JwtRequest;
import com.sztorma.logindemo.security.model.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        JwtResponse response = authenticationFacade.createAuthenticationResponse(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/authenticate/captcha", method = RequestMethod.GET)
    public ResponseEntity<Void> captchaAuthentication(@RequestHeader("Authorization") String authorization) {
        authenticationFacade.finalizeAuthAfterCaptcha(authorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
