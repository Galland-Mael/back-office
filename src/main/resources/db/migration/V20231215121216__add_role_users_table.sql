ALTER table users add column role varchar(255);

UPDATE users
SET role = 'ADMIN'
WHERE admin = 1;

UPDATE users
SET role = 'USER'
WHERE admin = 0;

ALTER TABLE users
    DROP COLUMN admin;