package com.coroutinemongochat.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing

/**
 * packageName    : com.mongochat.config
 * fileName       : MongoConfig
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

@EnableReactiveMongoAuditing
@Configuration
class MongoConfig
