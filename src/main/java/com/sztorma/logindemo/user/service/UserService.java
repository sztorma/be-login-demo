package com.sztorma.logindemo.user.service;

import java.util.Date;

import com.sztorma.logindemo.user.entity.User;

public interface UserService {

    public User getUserByName(String name);

    public User getUserFromJwt(String jwt);

    public void saveLastLogin(String username, Date date);

}
