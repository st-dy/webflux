package com.r2dbc.service;

/**
 * packageName    : com.r2dbc.service
 * fileName       : TokenGenerator
 * author         : okdori
 * date           : 3/18/24
 * description    :
 */
public class TokenGenerator {
    public String execute() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char item = (char) ('A' + (Math.random() * 26));
            token.append(item);
        }

        return token.toString();
    }
}
