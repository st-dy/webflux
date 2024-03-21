package com.r2dbc.repository;

import com.r2dbc.common.repository.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface UserR2dbcRepository extends R2dbcRepository<UserEntity, Long> {
    Flux<UserEntity> findAllByNameStartsWith(String prefix);
}
