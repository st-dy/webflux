package com.r2dbc.runner;

import com.r2dbc.common.repository.UserEntity;
import com.r2dbc.repository.UserR2dbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.r2dbc.runner
 * fileName       : PersonInsertRunner
 * author         : okdori
 * date           : 2/14/24
 * description    :
 */

@Slf4j
@RequiredArgsConstructor
//@Component
public class PersonInsertRunner implements CommandLineRunner {
    private final UserR2dbcRepository userR2dbcRepository;

    @Override
    public void run(String... args) throws Exception {
        var newUser = new UserEntity("okdori", 20, "1", "1q2w3e4r!");
        var savedUser = userR2dbcRepository.save(newUser).block();
        log.info("savedUser: {}", savedUser);
    }
}
