CREATE TABLE presets_standard
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR(30) NOT NULL,
    json TEXT        NOT NULL
);