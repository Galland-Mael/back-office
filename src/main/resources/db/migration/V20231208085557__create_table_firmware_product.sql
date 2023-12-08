CREATE TABLE firmware_product
(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    version varchar(10),
    path VARCHAR(50) NOT NULL
);