package com.sztorma.logindemo.route.dao;

import java.util.List;
import java.util.Set;

import com.sztorma.logindemo.common.EntityDaoImpl;
import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.route.repository.RouteRepository;
import com.sztorma.logindemo.user.entity.Role;

import org.springframework.stereotype.Service;

@Service
public class RouteDaoImpl extends EntityDaoImpl<Route, RouteRepository> implements RouteDao {

    @Override
    public List<Route> getComponentRoutesForRoles(Set<Role> roles, String component) {
        return repository.findByRoleInAndComponentOrderByOrder(roles, component);
    }

}
