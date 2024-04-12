package com.r2dbcwebfluxintegrationtest;

import com.r2dbcwebfluxintegrationtest.common.repository.UserEntity;
import com.r2dbcwebfluxintegrationtest.common.User;
import com.r2dbcwebfluxintegrationtest.common.Image;

import java.util.Collections;
import java.util.Optional;

public class TestDataBuilder {
    public static User createUser(String id) {
        var profileImage = new Image(
                "1",
                "profile",
                "https://okdol/images/1"
        );

        return new User(
                id,
                "okdol",
                20,
                Optional.of(profileImage),
                Collections.emptyList(),
                100L
        );
    }

    public static User createUser(
            String id,
            String name,
            Integer age,
            String password,
            String profileImageId
    ) {
        var profileImage = new Image(
                profileImageId,
                "profile",
                "https://okdol/images/1"
        );

        return new User(
                id,
                name,
                age,
                Optional.of(profileImage),
                Collections.emptyList(),
                100L
        );
    }

    public static UserEntity createUnsavedUserEntity(
            String name
    ) {
        return new UserEntity(
                name,
                20,
                "1",
                "password"
        );
    }

    public static UserEntity createUnsavedUserEntity(
            String name,
            Integer age,
            String profileImageId,
            String password
    ) {
        return new UserEntity(
                name,
                age,
                profileImageId,
                password
        );
    }
}
