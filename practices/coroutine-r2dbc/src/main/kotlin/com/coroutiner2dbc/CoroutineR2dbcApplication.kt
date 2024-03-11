package com.coroutiner2dbc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * packageName    : com
 * fileName       : CoroutineR2dbcApplication
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

@SpringBootApplication
class CoroutineR2dbcApplication

fun main(args: Array<String>) {
    runApplication<CoroutineR2dbcApplication>(*args)
}
