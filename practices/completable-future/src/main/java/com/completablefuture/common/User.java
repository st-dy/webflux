package com.completablefuture.common;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.completablefuture.common
 * fileName       : User
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Data
public class User {
    private final String id;
    private final String name;
    private final int age;
    private final Optional<Image> profileImage;
    private final List<Article> articleList;
    private final Long followCount;
}
