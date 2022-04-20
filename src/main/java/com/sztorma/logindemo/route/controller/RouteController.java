package com.sztorma.logindemo.route.controller;

import java.util.List;

import com.sztorma.logindemo.route.facade.RouteFacade;
import com.sztorma.logindemo.route.model.dto.RouteDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/route", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RouteController {

    @Autowired
    private RouteFacade routeFacade;

    @GetMapping(value = "/get/for-current-roles/for-component/{component}")
    public ResponseEntity<List<RouteDto>> getComponentRolesForCurrentUser(
            @RequestHeader("Authorization") String authorization, @PathVariable String component) {
        final List<RouteDto> routes = routeFacade.getComponentRolesForCurrentUser(authorization, component);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
}
