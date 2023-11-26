package com.webfluximage.service;

import com.webfluximage.entity.common.Image;
import com.webfluximage.repository.ImageReactorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * packageName    : com.webfluximage.service
 * fileName       : ImageService
 * author         : okdori
 * date           : 11/26/23
 * description    :
 */

@Service
public class ImageService {
    private ImageReactorRepository imageRepository = new ImageReactorRepository();

    public Mono<Image> getImageById(String imageId) {
        return imageRepository.findById(imageId)
                .map(imageEntity ->
                        new Image(imageEntity.getId(), imageEntity.getName(), imageEntity.getUrl())
                );
    }
}
