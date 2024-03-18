package com.r2dbc.service;

import com.r2dbc.common.repository.UserEntity;
import com.r2dbc.repository.ImageHttpClient;
import com.r2dbc.repository.UserR2dbcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.r2dbc.service
 * fileName       : UserServiceTest
 * author         : okdori
 * date           : 3/15/24
 * description    :
 */

@ExtendWith(MockitoExtension.class)
class UserServiceTest2 {
    @Mock
    ImageHttpClient mockImageHttpClient;

    @Mock
    UserR2dbcRepository mockUserR2dbcRepository;

    @Mock
    R2dbcEntityTemplate mockR2dbcEntityTemplate;

    @InjectMocks
    UserService2 userService;

    @Nested
    class FindById {
        Long userId;

        @BeforeEach
        void setup() {
            userId = 1L;
        }

        @Test
        void when_user_repository_returns_empty_then_returns_empty_mono() {
            //given
            when(mockUserR2dbcRepository.findById(eq(userId)))
                    .thenReturn(Mono.empty());

            //when
            var result = userService.findById(String.valueOf(userId));

            //then
            StepVerifier.create(result)
                    .verifyComplete();
        }

        @Nested
        class UserIsFound {
            UserEntity givenUser;

            @BeforeEach
            void setup() {
                givenUser = new UserEntity(
                        1L,
                        "okdol",
                        20,
                        "1234",
                        "123qwe!@#"
                );

                when(mockUserR2dbcRepository.findById(eq(userId)))
                        .thenReturn(Mono.just(givenUser));
            }

            @Test
            void when_image_is_empty_then_returns_user_with_empty_image() {
                //given
                when(mockImageHttpClient.getImageResponseByImageId(anyString()))
                        .thenReturn(Mono.empty());

                //when
                var result = userService.findById(String.valueOf(userId));

                //then
                StepVerifier.create(result)
                        .assertNext(foundUser -> {
                            assertEquals(String.valueOf(givenUser.getId()), foundUser.getId());
                            assertEquals(givenUser.getAge(), foundUser.getAge());
                            assertEquals(givenUser.getName(), foundUser.getName());
                            assertTrue(foundUser.getProfileImage().isEmpty());
                        })
                        .verifyComplete();
            }

            @Test
            void when_image_is_not_empty_then_returns_user_with_image() {
                //given
                var givenImageResponse = new ImageResponse();
                givenImageResponse.setId(givenUser.getProfileImageId());
                givenImageResponse.setName("profileImage");
                givenImageResponse.setUrl("http://okdol/image/1");

                when(mockImageHttpClient.getImageResponseByImageId(givenUser.getProfileImageId()))
                        .thenReturn(Mono.just(givenImageResponse));

                //when
                var result = userService.findById(String.valueOf(userId));

                //then
                StepVerifier.create(result)
                        .assertNext(foundUser -> {
                            assertEquals(String.valueOf(givenUser.getId()), foundUser.getId());
                            assertEquals(givenUser.getAge(), foundUser.getAge());
                            assertEquals(givenUser.getName(), foundUser.getName());
                            assertTrue(foundUser.getProfileImage().isPresent());

                            var foundImage = foundUser.getProfileImage().get();
                            assertEquals(givenImageResponse.getId(), foundImage.getId());
                            assertEquals(givenImageResponse.getName(), foundImage.getName());
                            assertEquals(givenImageResponse.getUrl(), foundImage.getUrl());
                        })
                        .verifyComplete();
            }
        }
    }
}
