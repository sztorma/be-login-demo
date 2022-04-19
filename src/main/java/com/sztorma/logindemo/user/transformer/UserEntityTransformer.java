package com.sztorma.logindemo.user.transformer;

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
        // TODO
        dto.setLastLogin(null);
        return dto;
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
