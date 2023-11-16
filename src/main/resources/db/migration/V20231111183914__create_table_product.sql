CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    firmware_id INT,
    CONSTRAINT fk_firmware FOREIGN KEY (firmware_id) REFERENCES firmware (id)
);