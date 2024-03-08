package com.coroutinemongochat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * packageName    : com.mongochat
 * fileName       : MongoChatApplication
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

@SpringBootApplication
class MongoChatApplication

fun main(args: Array<String>) {
    runApplication<MongoChatApplication>(*args)
}
