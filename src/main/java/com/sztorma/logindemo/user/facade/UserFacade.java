package com.sztorma.logindemo.user.facade;

import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.user.entity.User;
import com.sztorma.logindemo.user.model.dto.UserDto;
import com.sztorma.logindemo.user.service.UserService;
import com.sztorma.logindemo.user.transformer.UserEntityTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserEntityTransformer userEntityTransformer;

    @Transactional(readOnly = true)
    public UserDto getUserFromAuthorization(String authorization) {
        final String jwt = JwtTokenUtil.getJwtFromAuthHeader(authorization);
        final User user = userService.getUserFromJwt(jwt);
        return userEntityTransformer.entityToDto(user);
    }
}
