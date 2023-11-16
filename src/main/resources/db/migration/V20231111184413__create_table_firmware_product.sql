CREATE TABLE firmware_product
(
    id      SERIAL      NOT NULL,
    date    DATE        NOT NULL,
    version VARCHAR(10) NOT NULL,
    data    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
)