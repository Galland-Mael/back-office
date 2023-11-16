CREATE TABLE library
(
    id      SERIAL PRIMARY KEY,
    user_id INT  NOT NULL,
    json    TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);