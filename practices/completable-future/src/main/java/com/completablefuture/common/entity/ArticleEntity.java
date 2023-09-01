package com.completablefuture.common.entity;

import lombok.Data;

/**
 * packageName    : com.completablefuture.common.repository
 * fileName       : ArticleEntity
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Data
public class ArticleEntity {
    private final String id;
    private final String title;
    private final String content;
    private final String userId;
}
