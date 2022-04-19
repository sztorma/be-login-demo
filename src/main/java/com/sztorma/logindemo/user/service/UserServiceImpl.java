package com.sztorma.logindemo.user.service;

import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.user.dao.UserDao;
import com.sztorma.logindemo.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserByName(String name) {
        return userDao.findUserByUsername(name);
    }

    @Override
    public User getUserFromJwt(String jwt) {
        final String username = this.jwtTokenUtil.getClaimFromToken(jwt, Claims::getSubject);
        return getUserByName(username);
    }

}
