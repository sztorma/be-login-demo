package com.sztorma.logindemo.security.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;

import com.sztorma.logindemo.LoginDemoApplication;
import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.security.model.CaptchaRequest;
import com.sztorma.logindemo.security.model.JwtRequest;
import com.sztorma.logindemo.security.model.JwtResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = LoginDemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerIT {

    private static final String USER = "Admin";
    private static final String PASSWORD = "admin";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtTokenUtil captchaTokenUtil;

    @Test
    @DisplayName("Succesful authentication")
    public void testAuthenticateSuccess() {
        final JwtRequest jwtRequest = new JwtRequest(USER, PASSWORD);
        final HttpEntity<JwtRequest> request = new HttpEntity<>(jwtRequest, new HttpHeaders());
        ResponseEntity<JwtResponse> responseEntity = this.restTemplate.postForEntity("/api/authenticate", request,
                JwtResponse.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        final String token = responseEntity.getBody().getJwt();
        final String captchaToken = responseEntity.getBody().getCaptchaToken();
        assertEquals(USER, jwtTokenUtil.getUsernameFromToken(token));
        assertNull(captchaToken);
    }

    @Test
    @DisplayName("No such user authentication attempt")
    public void testNoSuchUserAuthentication() {
        final JwtRequest jwtRequest = new JwtRequest("invalidUser", PASSWORD);
        unauthorizedAuthentication(new HttpEntity<>(jwtRequest, new HttpHeaders()));
    }

    @Test
    @DisplayName("Invalid password authentication attempt")
    public void testInvalidPasswordAuthentication() {
        final JwtRequest jwtRequest = new JwtRequest(USER, "invalidPassword");
        unauthorizedAuthentication(new HttpEntity<>(jwtRequest, new HttpHeaders()));
    }

    private void unauthorizedAuthentication(final HttpEntity<JwtRequest> request) {
        final ResponseEntity<?> responseEntity = this.restTemplate.postForEntity("/api/authenticate", request,
                JwtRequest.class);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Succesful captcha authentication")
    public void testCaptchaAuthenticateSuccess() {
        final String captchaToken = captchaTokenUtil.doGenerateToken(new HashMap<>(), "Admin",
                System.currentTimeMillis(),
                5 * 60 * 60);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        CaptchaRequest requestBody = new CaptchaRequest(captchaToken);
        final HttpEntity<CaptchaRequest> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<JwtResponse> responseEntity = this.restTemplate.exchange("/api/authenticate/captcha",
                HttpMethod.POST,
                request, JwtResponse.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        final String jwtResponse = responseEntity.getBody().getJwt();
        final String captchaTokenResponse = responseEntity.getBody().getCaptchaToken();
        assertEquals(USER, jwtTokenUtil.getUsernameFromToken(jwtResponse));
        assertNull(captchaTokenResponse);
    }
}
