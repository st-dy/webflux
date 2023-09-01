package com.completablefuture.blocking.repository;

import com.completablefuture.common.entity.UserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * packageName    : com.completablefuture.blocking.repository
 * fileName       : UserFutureRepository
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class UserRepository {
    private final Map<String, UserEntity> userMap;

    public UserRepository() {
        var user = new UserEntity("1234", "taewoo", 32, "image#1000");

        userMap = Map.of("1234", user);
    }

    @SneakyThrows
    public Optional<UserEntity> findById(String userId) {
        log.info("UserFutureRepository.findById: {}", userId);
        Thread.sleep(1000);
        var user = userMap.get(userId);
        return Optional.ofNullable(user);
    }
}
