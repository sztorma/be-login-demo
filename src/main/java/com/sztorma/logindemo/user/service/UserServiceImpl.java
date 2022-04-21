package com.sztorma.logindemo.user.service;

import java.util.Date;

import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.user.dao.UserDao;
import com.sztorma.logindemo.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${security.max-login}")
    private static int MAX_LOGIN;

    @Override
    public User getUserByName(String name) {
        return userDao.findUserByUsername(name);
    }

    @Override
    public User getUserFromJwt(String jwt) {
        final String username = this.jwtTokenUtil.getClaimFromToken(jwt, Claims::getSubject);
        return getUserByName(username);
    }

    @Override
    public void saveLastLogin(String username, Date date) {
        final User user = getUserByName(username);
        user.setLastLogin(date);
        userDao.save(user);
    }

    @Override
    public void increaseLoginAttempt(String username) {
        final User user = getUserByName(username);
        if (user != null) {
            if (user.getLoginAttempt() != null) {
                user.setLoginAttempt(user.getLoginAttempt() + 1);
            } else {
                user.setLoginAttempt(1);
            }
            userDao.save(user);
        }
    }

    @Override
    public boolean getCaptchaRequired(String username) {
        final User user = getUserByName(username);
        if (user == null) {
            return false;
        }

        if (user.getLoginAttempt() != null) {
            return user.getLoginAttempt() > MAX_LOGIN;
        }
        return false;
    }

}
