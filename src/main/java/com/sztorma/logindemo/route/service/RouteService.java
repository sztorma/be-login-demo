package com.sztorma.logindemo.route.service;

import java.util.List;
import java.util.Set;

import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

public interface RouteService {

    public List<Route> getComponentRoutesForRoles(Set<Role> roles, String component);

}
