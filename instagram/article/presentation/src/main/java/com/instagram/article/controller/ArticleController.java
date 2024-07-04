package com.instagram.article.controller;

import com.instagram.article.entity.Article;
import com.instagram.article.entity.ArticleThumbnail;
import com.instagram.article.usecase.CreateArticleUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    private final CreateArticleUsecase createArticleUsecase;

    @PostMapping
    Mono<ArticleResponse> createArticle(
        @RequestBody CreateArticleRequest createArticleRequest,
        @RequestHeader("X-User-Id") String userId
    ) {
        var input = new CreateArticleUsecase.Input(
                createArticleRequest.title(),
                createArticleRequest.content(),
                createArticleRequest.thumbnailImageIds(),
                userId
        );

        return createArticleUsecase.execute(input)
                .map(this::fromEntity);
    }

    private ArticleResponse fromEntity(Article article) {
        return new ArticleResponse(
                article.id(),
                article.title(),
                article.content(),
                article.creatorId(),
                article.thumbnails().stream()
                        .map(this::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    private ThumbnailResponse fromEntity(ArticleThumbnail thumbnail) {
        return new ThumbnailResponse(
                thumbnail.getId(),
                thumbnail.getUrl(),
                thumbnail.getWidth(),
                thumbnail.getHeight()
        );
    }
}
