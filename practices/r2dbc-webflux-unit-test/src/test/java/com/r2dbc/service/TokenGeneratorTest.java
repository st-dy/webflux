package com.r2dbc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.r2dbc.service
 * fileName       : TokenGeneratorTest
 * author         : okdori
 * date           : 3/18/24
 * description    :
 */
class TokenGeneratorTest {
    TokenGenerator tokenGenerator;

    @BeforeEach
    void setup() {
        tokenGenerator = new TokenGenerator();
    }

    @Test
    void when_call_then_returns_token() {
        //when
        String result = tokenGenerator.execute();

        //then
        assertLinesMatch(List.of("^[A-Z]{6}$"), List.of(result));
    }
}
