CREATE TABLE firmware
(
    id      SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    version VARCHAR(10) NOT NULL,
    dataFilePath varchar(50) NOT NULL
);
