CREATE TABLE firmware_product
(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    version varchar(10),
    dataFilePath VARCHAR(50) NOT NULL
);