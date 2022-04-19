package com.sztorma.logindemo.user.controller;

import com.sztorma.logindemo.user.facade.UserFacade;
import com.sztorma.logindemo.user.model.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "/get/jwt")
    public ResponseEntity<UserDto> getUserFromAuthorization(@RequestHeader("Authorization") String authorization) {
        final UserDto userDto = userFacade.getUserFromAuthorization(authorization);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }
}
