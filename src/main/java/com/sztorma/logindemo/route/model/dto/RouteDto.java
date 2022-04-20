package com.sztorma.logindemo.route.model.dto;

import lombok.Data;

@Data
public class RouteDto {

    private Long id;

    private String component;

    private Integer order;

    private String name;

    private String link;

}
