CREATE TABLE shared
(
    id      SERIAL PRIMARY KEY,
    date    DATE DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    presets TEXT ,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);