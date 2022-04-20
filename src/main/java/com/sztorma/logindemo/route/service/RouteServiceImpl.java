package com.sztorma.logindemo.route.service;

import java.util.List;
import java.util.Set;

import com.sztorma.logindemo.route.dao.RouteDao;
import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public List<Route> getComponentRoutesForRoles(Set<Role> roles, String component) {
        return routeDao.getComponentRoutesForRoles(roles, component);
    }

}
