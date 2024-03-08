package com.coroutinemongochat.config

import com.coroutinemongochat.handler.ChatCoroutineWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping

/**
 * packageName    : com.mongochat.config
 * fileName       : MappingConfig
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

@Configuration
class MappingConfig {
    @Bean
    fun simpleUrlHandlerMapping(
        chatWebSocketHandler: ChatCoroutineWebSocketHandler
    ): SimpleUrlHandlerMapping {
        val urlMapper = mapOf("/chat" to chatWebSocketHandler)
        return SimpleUrlHandlerMapping().also {
            it.order = 1
            it.urlMap = urlMapper
        }
    }
}
