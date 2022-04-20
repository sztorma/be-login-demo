package com.sztorma.logindemo.route.facade;

import java.util.List;

import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.route.model.dto.RouteDto;
import com.sztorma.logindemo.route.service.RouteService;
import com.sztorma.logindemo.route.transformer.RouteEntityTransformer;
import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.user.entity.User;
import com.sztorma.logindemo.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RouteFacade {

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RouteEntityTransformer routeEntityTransformer;

    @Transactional(readOnly = true)
    public List<RouteDto> getComponentRolesForCurrentUser(String authorization, String component) {
        final String jwt = JwtTokenUtil.getJwtFromAuthHeader(authorization);
        final User user = userService.getUserFromJwt(jwt);
        final List<Route> routes = routeService.getComponentRoutesForRoles(user.getRoles(), component);
        return routeEntityTransformer.entityListToDtoList(routes);
    }

}
