package com.sztorma.logindemo.content.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import com.sztorma.logindemo.LoginDemoApplication;
import com.sztorma.logindemo.security.JwtTokenUtil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = LoginDemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContentControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    @DisplayName("Getting admin content successfuly")
    public void testGetAdminContentSuccess() {
        final HttpEntity<String> request = new HttpEntity<>(generateAuthHeaders("Admin"));
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("/api/content/admin", HttpMethod.GET,
                request, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("{\"response\": \"response for admin\"}", responseEntity.getBody());

    }

    @Test
    @DisplayName("Getting admin content with not proper authority")
    public void testGetAdminContentFail() {
        final HttpEntity<String> request = new HttpEntity<>(generateAuthHeaders("User 1"));
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("/api/content/admin", HttpMethod.GET,
                request, String.class);
        assertEquals(403, responseEntity.getStatusCodeValue());
    }

    private HttpHeaders generateAuthHeaders(String username) {
        final HttpHeaders headers = new HttpHeaders();
        final String token = jwtTokenUtil.doGenerateToken(new HashMap<>(), username, System.currentTimeMillis(),
                5 * 60 * 60);
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
