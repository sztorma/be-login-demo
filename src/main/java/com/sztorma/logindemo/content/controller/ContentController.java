package com.sztorma.logindemo.content.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/content", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContentController {

    @GetMapping(value = "/admin")
    @ResponseBody
    public ResponseEntity<String> adminContent() {
        return new ResponseEntity<>(new String("{\"response\": \"response for admin\"}"), HttpStatus.OK);
    }

    @GetMapping(value = "/moderator")
    @ResponseBody
    public ResponseEntity<String> moderatorContent() {
        return new ResponseEntity<>(new String("{\"response\": \"response for moderator\"}"), HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<String> userContent() {
        return new ResponseEntity<>(new String("{\"response\": \"response for user\"}"), HttpStatus.OK);
    }

}