package com.sztorma.logindemo.route.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sztorma.logindemo.common.Identity;
import com.sztorma.logindemo.user.entity.Role;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route extends Identity {

    @NonNull
    @Column(nullable = false, length = 20)
    private String name;

    @NonNull
    @Column(nullable = false, length = 50)
    private String component;

    @NonNull
    @Column(name = "order_number", nullable = false)
    private Integer order;

    @NonNull
    @Column(nullable = false, length = 50)
    private String link;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
