package com.sztorma.logindemo.user.dao;

import com.sztorma.logindemo.common.EntityDaoImpl;
import com.sztorma.logindemo.user.entity.User;
import com.sztorma.logindemo.user.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl extends EntityDaoImpl<User, UserRepository> implements UserDao {

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

}
