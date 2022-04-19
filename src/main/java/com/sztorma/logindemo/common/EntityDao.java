package com.sztorma.logindemo.common;

public interface EntityDao<Entity extends Identity> {

    Entity getById(Long id);

    void deleteById(Long id);

    Entity save(Entity entity);
}
