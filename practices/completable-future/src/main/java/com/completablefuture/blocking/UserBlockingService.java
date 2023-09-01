package com.completablefuture.blocking;

import com.completablefuture.blocking.repository.ArticleRepository;
import com.completablefuture.blocking.repository.FollowRepository;
import com.completablefuture.blocking.repository.ImageRepository;
import com.completablefuture.blocking.repository.UserRepository;
import com.completablefuture.common.Article;
import com.completablefuture.common.Image;
import com.completablefuture.common.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : com.completablefuture.blocking
 * fileName       : UserBlockingService
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@RequiredArgsConstructor
public class UserBlockingService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;
    private final FollowRepository followRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id)
                .map(user -> {
                    var image = imageRepository.findById(user.getProfileImageId())
                            .map(imageEntity -> {
                                return new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl());
                            });

                    var articles = articleRepository.findAllByUserId(user.getId())
                            .stream().map(articleEntity ->
                                    new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent()))
                            .collect(Collectors.toList());

                    var followCount = followRepository.countByUserId(user.getId());

                    return new User(
                            user.getId(),
                            user.getName(),
                            user.getAge(),
                            image,
                            articles,
                            followCount
                    );
                });
    }
}
