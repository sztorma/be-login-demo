package com.sztorma.logindemo.user.service;

import com.sztorma.logindemo.user.dao.UserDao;
import com.sztorma.logindemo.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String name) {
        return userDao.findUserByUsername(name);
    }

}
