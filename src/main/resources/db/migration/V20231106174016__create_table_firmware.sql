CREATE TABLE firmware
(
    id      SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    version VARCHAR(10) NOT NULL,
    data BYTEA NOT NULL,
    CONSTRAINT uq_version UNIQUE (version),
    CONSTRAINT chk_version_length CHECK (LENGTH(version) <= 10)
);
