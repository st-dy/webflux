package com.completablefuture.common.entity;

import lombok.Data;

/**
 * packageName    : com.completablefuture.common.repository
 * fileName       : UserEntity
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Data
public class UserEntity {
    private final String id;
    private final String name;
    private final int age;
    private final String profileImageId;
}
