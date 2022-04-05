package com.sztorma.logindemo.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.sztorma.logindemo.common.Identity;

import lombok.Getter;

@Entity
@Table(name = "roles")
@Getter
public class Role extends Identity {

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ERole name;

}
