package com.sztorma.logindemo.common;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EntityTransformer<Dto, Entity> {

    public abstract Dto entityToDto(Entity entity);

    public abstract Entity dtoToEntity(Dto dto);

    public List<Dto> entityListToDtoList(List<Entity> entityList) {
        return entityToDtoCollectionStream(entityList).collect(Collectors.toList());
    }

    public List<Entity> dtoListToEntityList(List<Dto> dtoList) {
        return dtoToEntityCollectionStream(dtoList).collect(Collectors.toList());
    }

    public Set<Dto> entitySetToDtoSet(Set<Entity> entitySet) {
        return entityToDtoCollectionStream(entitySet).collect(Collectors.toSet());
    }

    private Stream<Dto> entityToDtoCollectionStream(Collection<Entity> entityCollection) {
        return entityCollection.stream().map(entity -> entityToDto(entity));
    }

    private Stream<Entity> dtoToEntityCollectionStream(Collection<Dto> dtoCollection) {
        return dtoCollection.stream().map(dto -> dtoToEntity(dto));
    }
}
