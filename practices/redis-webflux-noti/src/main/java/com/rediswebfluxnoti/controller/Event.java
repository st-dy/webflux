package com.rediswebfluxnoti.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String type;
    private String message;
}
