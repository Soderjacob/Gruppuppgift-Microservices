
-- Skapa databas och populera med en testanvändare för user_service
CREATE DATABASE user_service;

USE user_service;

CREATE TABLE user_service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(255) DEFAULT NULL,
    reviews TEXT DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL
);

INSERT INTO user_service (id, genre, reviews, username) VALUES
(1, NULL, NULL, NULL),
(2, 'Comedy, action and thriller', NULL, 'Jacob');

SELECT * FROM user;

-- Skapa databas och populera med en testanvändare för reviews_db
CREATE DATABASE reviews_db;

USE reviews_db;

CREATE TABLE movie (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    release_date DATE
);

CREATE TABLE review (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    review_text TEXT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    review_date DATE,
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

INSERT INTO reviews (id, comment, movie_id, rating, user_id) VALUES
(1, 'This movie was awesome!', 1, 5, 1),
(2, 'This movie was bad!', 2, 2, 2),
(3, 'This movie was okay.', 2, 3, 3);

SELECT * FROM review;