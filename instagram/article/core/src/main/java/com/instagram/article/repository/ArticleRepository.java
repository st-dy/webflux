package com.instagram.article.repository;

import com.instagram.article.entity.Article;
import reactor.core.publisher.Mono;

public interface ArticleRepository {
    Mono<Article> save(Article article);
}
