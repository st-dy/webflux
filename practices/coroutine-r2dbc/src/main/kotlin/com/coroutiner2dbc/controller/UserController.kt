package com.coroutiner2dbc.controller

import com.coroutiner2dbc.common.Image
import com.coroutiner2dbc.common.User
import com.coroutiner2dbc.controller.dto.ProfileImageResponse
import com.coroutiner2dbc.controller.dto.SignupUserRequest
import com.coroutiner2dbc.controller.dto.UserResponse
import com.coroutiner2dbc.service.UserCoroutineService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

/**
 * packageName    : com.coroutiner2dbc.controller
 * fileName       : UserController
 * author         : okdori
 * date           : 3/8/24
 * description    :
 */

@RequestMapping("/api/users")
@RestController
class UserController(
    private val userService: UserCoroutineService,
) {
    @GetMapping("/{userId}")
    suspend fun getUserById(
        @PathVariable userId: String
    ): UserResponse {
        val context = ReactiveSecurityContextHolder
            .getContext()
            .awaitSingle()

        val name = context.authentication.name
        if (name != userId) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

        return userService.findById(userId)
            ?.let(this::map)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    suspend fun signupUser(
        @RequestBody request: SignupUserRequest
    ): UserResponse {
        return userService.createUser(
            request.name, request.age,
            request.password, request.profileImageId
        ).let(this::map)
    }

    private fun map(user: User): UserResponse {
        return UserResponse(
            user.id,
            user.name,
            user.age,
            user.followCount,
            user.profileImage.map { image: Image ->
                ProfileImageResponse(
                    image.id,
                    image.name,
                    image.url
                )
            }
        )
    }
}
