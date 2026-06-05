ALTER TABLE client
DROP COLUMN email,
    MODIFY COLUMN id BIGINT NOT NULL;

ALTER TABLE chauffeur
    MODIFY COLUMN id BIGINT NOT NULL;

ALTER TABLE client
    ADD CONSTRAINT fk_user_client
        FOREIGN KEY (id) REFERENCES user_entity(id);

ALTER TABLE chauffeur
    ADD CONSTRAINT fk_user_chauffeur
        FOREIGN KEY (id) REFERENCES user_entity(id);

CREATE TABLE admin (
                        id BIGINT PRIMARY KEY,
                        CONSTRAINT fk_user_admin
                            FOREIGN KEY (id) REFERENCES user_entity(id)
);

CREATE TABLE manager (
                         id BIGINT PRIMARY KEY,
                         CONSTRAINT fk_user_manager
                             FOREIGN KEY (id) REFERENCES user_entity(id)
);