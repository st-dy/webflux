package com.r2dbc.repository;

import com.r2dbc.service.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * packageName    : com.r2dbc.repository
 * fileName       : ImageHttpClient
 * author         : okdori
 * date           : 3/15/24
 * description    :
 */

@RequiredArgsConstructor
@Component
public class ImageHttpClient {
    private final WebClient imageWebClient;

    public Mono<ImageResponse> getImageResponseByImageId(String imageId) {
        Map<String, String> uriVariableMap = Map.of("imageId", imageId);
        return imageWebClient.get()
                .uri("/api/images/{imageId}", uriVariableMap)
                .retrieve()
                .toEntity(ImageResponse.class)
                .map(resp -> resp.getBody());
    }
}
