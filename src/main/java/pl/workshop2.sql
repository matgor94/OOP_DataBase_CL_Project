CREATE DATABASE IF NOT EXISTS workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE user(
    id INT (11) AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user (email, username, password) VALUES (?,?,?);
SELECT * FROM user;
SELECT * FROM user WHERE id = 1;
DELETE FROM user WHERE id = 1;
UPDATE user SET email = 'maly316', username = 'matgor94', password = 'coderslab123' WHERE id = 1;

USE workshop2;
DESCRIBE user;

DROP TABLE user;


