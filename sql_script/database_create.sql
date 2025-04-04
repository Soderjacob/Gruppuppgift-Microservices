-- Skapande av reviews_db
DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `rating` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `review` VALUES 
(1,'This movie was awesome!',1,5,1),
(2,'This movie was bad!',2,2,2),
(3,'This movie was okay.',2,3,3);

-- Skapande av user_service
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) DEFAULT NULL,
  `reviews` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` VALUES (1,NULL,NULL,NULL),
(2,'Comedy, action and thriller','','Jacob');
