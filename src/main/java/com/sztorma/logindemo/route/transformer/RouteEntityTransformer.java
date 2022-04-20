package com.sztorma.logindemo.route.transformer;

import com.sztorma.logindemo.common.EntityTransformer;
import com.sztorma.logindemo.route.entity.Route;
import com.sztorma.logindemo.route.model.dto.RouteDto;

import org.springframework.stereotype.Component;

@Component
public class RouteEntityTransformer extends EntityTransformer<RouteDto, Route> {

    @Override
    public RouteDto entityToDto(Route entity) {
        final RouteDto dto = new RouteDto();
        dto.setId(entity.getId());
        dto.setComponent(entity.getComponent());
        dto.setOrder(entity.getOrder());
        dto.setName(entity.getName());
        dto.setLink(entity.getLink());
        return dto;
    }

    @Override
    public Route dtoToEntity(RouteDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
