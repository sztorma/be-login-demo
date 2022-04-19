package com.sztorma.logindemo.common;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityTransformer<Dto, Entity> {

    public abstract Dto entityToDto(Entity entity);

    public abstract Entity dtoToEntity(Dto dto);

    public List<Dto> entityListToDtoList(List<Entity> entityList) {
        return entityList.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    public List<Entity> dtoListToEntityList(List<Dto> dtoList) {
        return dtoList.stream().map(dto -> dtoToEntity(dto)).collect(Collectors.toList());
    }
}
