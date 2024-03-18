package com.r2dbc.service;

import com.r2dbc.common.EmptyImage;
import com.r2dbc.common.Image;
import com.r2dbc.common.User;
import com.r2dbc.common.repository.AuthEntity;
import com.r2dbc.common.repository.UserEntity;
import com.r2dbc.repository.ImageHttpClient;
import com.r2dbc.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService2 {
    private final ImageHttpClient imageHttpClient;
    private final UserR2dbcRepository userRepository;
    private final R2dbcEntityTemplate entityTemplate;

    public Mono<User> findById(String userId) {
        return userRepository.findById(Long.valueOf(userId))
                .flatMap(userEntity -> {
                    String imageId = userEntity.getProfileImageId();

                    return imageHttpClient.getImageResponseByImageId(imageId)
                            .map(imageResp -> new Image(
                                    imageResp.getId(),
                                    imageResp.getName(),
                                    imageResp.getUrl()
                            )).switchIfEmpty(Mono.just(new EmptyImage()))
                            .map(image -> {
                                Optional<Image> profileImage = Optional.empty();
                                if (!(image instanceof EmptyImage)) {
                                    profileImage = Optional.of(image);
                                }
                                return map(userEntity, profileImage);
                            });
                });
    }

    @Transactional
    public Mono<User> createUser(
            String name, Integer age,
            String password, String profileImageId) {

        var newUser = new UserEntity(
                name,
                age,
                profileImageId,
                password
        );

        return userRepository.save(newUser)
                .flatMap(userEntity -> {
                    String token = new TokenGenerator().execute();
                    AuthEntity auth = new AuthEntity(userEntity.getId(), token);

                    return entityTemplate.insert(auth)
                            .map(authEntity -> userEntity);
                })
                .map(userEntity ->
                        map(userEntity, Optional.of(new EmptyImage()))
                );
    }

    private User map(UserEntity userEntity, Optional<Image> profileImage) {
        return new User(
                userEntity.getId().toString(),
                userEntity.getName(),
                userEntity.getAge(),
                profileImage,
                List.of(),
                0L
        );
    }
}
