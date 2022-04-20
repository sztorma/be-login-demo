package com.sztorma.logindemo.route.dao;

import java.util.List;
import java.util.Set;

import com.sztorma.logindemo.common.EntityDao;
import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

public interface RouteDao extends EntityDao<Route> {

    List<Route> getComponentRoutesForRoles(Set<Role> roles, String component);

}
