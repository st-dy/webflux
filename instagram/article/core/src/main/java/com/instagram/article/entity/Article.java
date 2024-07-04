package com.instagram.article.entity;

import java.util.List;

public record Article(String id, String title, String content, List<ArticleThumbnail> thumbnails, String creatorId) {
    public static Article create(
            String title,
            String content,
            List<ArticleThumbnail> thumbnails,
            String creatorId
    ) {
        return new Article(null, title, content, thumbnails, creatorId);
    }
}
