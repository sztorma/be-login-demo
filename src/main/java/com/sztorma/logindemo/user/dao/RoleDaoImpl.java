package com.sztorma.logindemo.user.dao;

import com.sztorma.logindemo.common.EntityDaoImpl;
import com.sztorma.logindemo.user.entity.Role;
import com.sztorma.logindemo.user.repository.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleDaoImpl extends EntityDaoImpl<Role, RoleRepository> {

}
