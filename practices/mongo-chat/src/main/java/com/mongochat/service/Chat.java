package com.mongochat.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Chat {
    private String message;
    private String from;
}
