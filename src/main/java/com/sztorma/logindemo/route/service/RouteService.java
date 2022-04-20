package com.sztorma.logindemo.route.service;

import java.util.Set;

import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

public interface RouteService {

    public Set<Route> getComponentRoutesForRoles(Set<Role> roles, String component);

}
