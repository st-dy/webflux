package com.rediswebfluximage.handler.dto;

import lombok.Data;

@Data
public class CreateRequest {
    private String id;
    private String name;
    private String url;
}
