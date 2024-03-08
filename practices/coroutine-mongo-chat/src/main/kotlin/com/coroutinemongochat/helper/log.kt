package com.coroutinemongochat.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * packageName    : com.mongochat.helper
 * fileName       : log
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

inline fun <reified T> logger() : Logger {
    return LoggerFactory.getLogger(T::class.java)
}
