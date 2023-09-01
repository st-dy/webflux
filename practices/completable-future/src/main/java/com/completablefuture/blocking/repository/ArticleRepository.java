package com.completablefuture.blocking.repository;

import com.completablefuture.common.entity.ArticleEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.completablefuture.blocking.repository
 * fileName       : ArticleFutureRepository
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class ArticleRepository {
    private static List<ArticleEntity> articleEntities;

    public ArticleRepository() {
        articleEntities = List.of(
                new ArticleEntity("1", "소식1", "내용1", "1234"),
                new ArticleEntity("2", "소식2", "내용2", "1234"),
                new ArticleEntity("3", "소식3", "내용3", "10000")
        );
    }

    @SneakyThrows
    public List<ArticleEntity> findAllByUserId(String userId) {
        log.info("ArticleFutureRepository.findAllByUserId: {}", userId);
        Thread.sleep(1000);
        return articleEntities.stream()
                .filter(articleEntity -> articleEntity.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
