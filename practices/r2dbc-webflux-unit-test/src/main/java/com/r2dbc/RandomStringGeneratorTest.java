package com.r2dbc;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName    : com.r2dbc
 * fileName       : RandomStringGeneratorTest
 * author         : okdori
 * date           : 2/14/24
 * description    :
 */

@Slf4j
public class RandomStringGeneratorTest {
    public static void main(String[] args) {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char item = (char) ('A' + (Math.random() * 26));
            token.append(item);
        }

        log.info(token.toString());
    }
}
