CREATE TABLE shared
(
    id      SERIAL PRIMARY KEY,
    date    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id INT                                   NOT NULL,
    presets TEXT                                  NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);