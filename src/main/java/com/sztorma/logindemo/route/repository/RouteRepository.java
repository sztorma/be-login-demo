package com.sztorma.logindemo.route.repository;

import java.util.List;
import java.util.Set;

import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.user.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    List<Route> findByRoleInAndComponentOrderByOrder(Set<Role> roles, String component);

}
