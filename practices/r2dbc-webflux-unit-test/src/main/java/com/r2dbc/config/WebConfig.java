package com.r2dbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * packageName    : com.r2dbc.config
 * fileName       : WebConfig
 * author         : okdori
 * date           : 3/15/24
 * description    :
 */

@Configuration
public class WebConfig {
    @Bean
    WebClient imageWebClient() {
        return WebClient.create("http://localhost:8081");
    }
}
