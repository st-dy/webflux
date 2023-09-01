package com.completablefuture.common.entity;

import lombok.Data;

/**
 * packageName    : com.completablefuture.common.repository
 * fileName       : ImageEntity
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Data
public class ImageEntity {
    private final String id;
    private final String name;
    private final String url;
}
