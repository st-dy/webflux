package com.r2dbc.repository;

import com.r2dbc.TestDataBuilder;
import com.r2dbc.common.repository.UserEntity;
import com.r2dbc.testconfig.TestR2dbcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.r2dbc.repository
 * fileName       : UserR2dbcRepositoryH2SliceTest
 * author         : okdori
 * date           : 3/21/24
 * description    :
 */

@Import(TestR2dbcConfig.class)
@ActiveProfiles("h2")
@DataR2dbcTest
class UserR2dbcRepositoryH2SliceTest {
    @Autowired
    UserR2dbcRepository userR2dbcRepository;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @BeforeEach
    void setup() {
        r2dbcEntityTemplate.delete(UserEntity.class)
                .all()
                .block();
    }

    @Test
    void r2dbcEntityTemplate_should_not_be_null() {
        assertNotNull(r2dbcEntityTemplate);
        assertNotNull(userR2dbcRepository);
    }

    @Test
    void when_save_then_should_return_saved_user() {
        //given
        var name = "okdol";
        var userEntity = TestDataBuilder.createUnsavedUserEntity(name);

        //when
        var result = userR2dbcRepository.save(userEntity);

        //then
        StepVerifier.create(result)
                .assertNext(createdUser -> {
                    assertNotNull(createdUser.getId());
                    assertEquals(name, createdUser.getName());
                })
                .verifyComplete();
    }

    @Test
    void when_find_all_should_returns_empty() {
        //when
        var result = userR2dbcRepository.findAll();

        //then
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void when_find_all_by_names_starts_then_returns_list() {
        //given
        var users = List.of(
                TestDataBuilder.createUnsavedUserEntity("okdol"),
                TestDataBuilder.createUnsavedUserEntity("o"),
                TestDataBuilder.createUnsavedUserEntity("ok"),
                TestDataBuilder.createUnsavedUserEntity("dol"),
                TestDataBuilder.createUnsavedUserEntity("okdolllll")
        );

        for (var user : users) {
            r2dbcEntityTemplate.insert(user).block();
        }

        var prefix = "ok";

        //when
        var result = userR2dbcRepository.findAllByNameStartsWith(prefix);

        //then
        StepVerifier.create(result)
                .expectNextCount(3)
                .verifyComplete();
    }
}
