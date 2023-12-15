ALTER table users add column role varchar(255);

UPDATE users
SET role = 'admin'
WHERE admin = 1;

UPDATE users
SET role = 'user'
WHERE admin = 0;

ALTER TABLE users
    DROP COLUMN admin;