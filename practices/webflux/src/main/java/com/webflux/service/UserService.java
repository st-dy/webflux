package com.webflux.service;

import com.webflux.common.EmptyImage;
import com.webflux.common.Image;
import com.webflux.common.User;
import com.webflux.repository.UserReactorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName    : com.webflux.service
 * fileName       : UserService
 * author         : okdori
 * date           : 11/27/23
 * description    :
 */

@Service
public class UserService {
    private WebClient webClient = WebClient.create("http://localhost:8081");
    private final UserReactorRepository userRepository = new UserReactorRepository();

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId)
                .flatMap(userEntity -> {
                    String imageId = userEntity.getProfileImageId();

                    Map<String, String> uriVariableMap = Map.of("imageId", imageId);
                    return webClient.get()
                            .uri("/api/images/{imageId}", uriVariableMap)
                            .retrieve()
                            .toEntity(ImageResponse.class)
                            .map(response -> response.getBody())
                            .map(imageResponse -> new Image(
                                    imageResponse.getId(),
                                    imageResponse.getName(),
                                    imageResponse.getUrl()
                            ))
                            .switchIfEmpty(Mono.just(new EmptyImage()))
                            .map(image -> {
                                Optional<Image> profileImage = Optional.empty();
                                if (!(image instanceof EmptyImage)) {
                                    profileImage = Optional.of(image);
                                }

                                return new User(
                                        userEntity.getId(),
                                        userEntity.getName(),
                                        userEntity.getAge(),
                                        profileImage,
                                        List.of(),
                                        0L
                                );
                            });
                });
    }
}
