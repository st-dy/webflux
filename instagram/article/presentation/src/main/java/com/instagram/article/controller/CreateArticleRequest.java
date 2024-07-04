package com.instagram.article.controller;

import java.util.List;

public record CreateArticleRequest(String title, String content, List<String> thumbnailImageIds) {
}
