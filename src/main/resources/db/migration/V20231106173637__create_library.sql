CREATE TABLE library
(
    id      SERIAL PRIMARY KEY,
    user_id INT,
    json    TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);