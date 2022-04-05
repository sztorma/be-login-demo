package com.sztorma.logindemo.user.repository;

import com.sztorma.logindemo.user.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
