CREATE TABLE quality
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT name_unique UNIQUE (name)
);