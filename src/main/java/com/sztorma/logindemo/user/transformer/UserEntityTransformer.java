package com.sztorma.logindemo.user.transformer;

import java.text.SimpleDateFormat;

import com.sztorma.logindemo.common.EntityTransformer;
import com.sztorma.logindemo.user.entity.User;
import com.sztorma.logindemo.user.model.dto.UserDto;

import org.springframework.stereotype.Service;

@Service
public class UserEntityTransformer extends EntityTransformer<UserDto, User> {

    @Override
    public UserDto entityToDto(User entity) {
        final UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        final String[] roles = entity.getRoles().stream().map(erole -> erole.getName().name()).toArray(String[]::new);
        dto.setRoles(roles);
        dto.setLastLogin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getLastLogin()));
        return dto;
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
