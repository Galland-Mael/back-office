ALTER table users add column library_id int;

ALTER TABLE users ADD CONSTRAINT fk_library_id FOREIGN KEY (library_id) REFERENCES library(id) ON DELETE SET NULL;

UPDATE users
SET library_id = library.id
FROM library
WHERE users.library_id IS NULL
  AND users.id = library.user_id;

