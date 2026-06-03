CREATE TABLE user_entity (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_name VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             password VARCHAR(255) NOT NULL,
                             role VARCHAR(50) NOT NULL
);