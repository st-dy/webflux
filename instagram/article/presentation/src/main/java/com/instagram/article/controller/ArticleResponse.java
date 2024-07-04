package com.instagram.article.controller;

import java.util.List;

/**
 * @param creatorId 생성자의 userId
 */
public record ArticleResponse(
        String id, String title, String content, String creatorId, List<ThumbnailResponse> thumbnails) {
}
