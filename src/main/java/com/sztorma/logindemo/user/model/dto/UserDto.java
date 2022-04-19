package com.sztorma.logindemo.user.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String username;

    private String[] roles;

    private String lastLogin;
}
