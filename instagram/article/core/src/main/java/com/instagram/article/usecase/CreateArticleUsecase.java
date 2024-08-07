package com.instagram.article.usecase;

import com.instagram.article.entity.Article;
import com.instagram.article.entity.ArticleThumbnail;
import com.instagram.article.entity.ArticleThumbnailIdOnly;
import com.instagram.article.publisher.ArticleCreatedEvent;
import com.instagram.article.publisher.ArticleEventPublisher;
import com.instagram.article.repository.ArticleRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Named
public class CreateArticleUsecase {
    private final ArticleRepository articleRepository;
    private final ArticleEventPublisher articleEventPublisher;

    @Data
    @EqualsAndHashCode
    public static class Input {
        private final String title;
        private final String content;
        private final List<String> thumbnailImageIds;
        private final String creatorId;
    }

    public Mono<Article> execute(Input input) {
        List<ArticleThumbnail> thumbnails = input.thumbnailImageIds.stream()
                .map(ArticleThumbnailIdOnly::new)
                .collect(Collectors.toList());

        var newArticle = Article.create(
                input.title,
                input.content,
                thumbnails,
                input.creatorId
        );

        return articleRepository.save(newArticle)
                .doOnNext(article -> {
                    var event = new ArticleCreatedEvent(
                            article.id(), article.creatorId()
                    );
                    articleEventPublisher.publish(event);
                });
    }
}
