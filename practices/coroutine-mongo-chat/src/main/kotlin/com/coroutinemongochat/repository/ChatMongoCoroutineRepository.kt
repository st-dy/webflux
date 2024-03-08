package com.coroutinemongochat.repository

import com.coroutinemongochat.entity.ChatDocument
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

/**
 * packageName    : com.mongochat.repository
 * fileName       : ChatMongoCoroutineRepository
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */
interface ChatMongoCoroutineRepository : CoroutineSortingRepository<ChatDocument, ObjectId>
