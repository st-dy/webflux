CREATE TABLE IF NOT EXISTS USERS
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    age              INT          NOT NULL,
    profile_image_id VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    created_at       DATETIME     NOT NULL,
    updated_at       DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS AUTH
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT       NOT NULL,
    token      VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL
);
