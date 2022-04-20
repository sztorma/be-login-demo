package com.sztorma.logindemo.route.dao;

import java.util.Set;

import com.sztorma.logindemo.common.EntityDao;
import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

public interface RouteDao extends EntityDao<Route> {

    Set<Route> getComponentRoutesForRoles(Set<Role> roles, String component);

}
