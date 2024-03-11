package com.coroutiner2dbc.repository

import com.coroutiner2dbc.common.repository.UserEntity
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

/**
 * packageName    : com.coroutiner2dbc.repository
 * fileName       : UserR2dbcRepository
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

interface UserR2dbcRepository : CoroutineSortingRepository<UserEntity, Long>
