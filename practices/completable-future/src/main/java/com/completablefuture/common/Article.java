package com.completablefuture.common;

import lombok.Data;

/**
 * packageName    : com.completablefuture.common
 * fileName       : Article
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Data
public class Article {
    private final String id;
    private final String title;
    private final String content;
}
