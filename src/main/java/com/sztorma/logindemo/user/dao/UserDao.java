package com.sztorma.logindemo.user.dao;

import com.sztorma.logindemo.common.EntityDao;
import com.sztorma.logindemo.user.entity.User;

public interface UserDao extends EntityDao<User> {

    User findUserByUsername(String username);

}
