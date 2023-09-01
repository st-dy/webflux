package com.completablefuture.blocking.repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * packageName    : com.completablefuture.blocking.repository
 * fileName       : FollowFutureRepository
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class FollowRepository {
    private Map<String, Long> userFollowCountMap;

    public FollowRepository() {
        userFollowCountMap = Map.of("1234", 1000L);
    }

    @SneakyThrows
    public Long countByUserId(String userId) {
        log.info("FollowFutureRepository.countByUserId: {}", userId);
        Thread.sleep(1000);
        return userFollowCountMap.getOrDefault(userId, 0L);
    }
}
