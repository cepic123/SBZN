INSERT INTO users (username, password, name, lastname, role) VALUES
('user1', '$2a$12$CjNE/X6ZrhrUPwAZTF9heO/HblYD6FY1/m9C6wk1WADgg23yelG2m', 'Pera', 'Peric', 'USER'),
('user2', 'user2', 'Mika', 'Mikic', 'USER'),
('user3', 'user3', 'Nikola', 'Nikolic', 'USER'),
('user4', 'user4', 'Mare', 'Maric', 'USER'),
('user5', 'user5', 'Rade', 'Radovic', 'USER'),
('user6', 'user6', 'Mirko', 'Mirkovic', 'USER');

INSERT INTO developer_studio (name, address) VALUES
('Ubisoft', 'Mite Cenica 56'),
('EA sports', 'Mite Cenica 57'),
('Rockstar', 'Mite Cenica 58');

INSERT INTO game (name, price, lenght, is_multiplayer, is_online, studio_id) VALUES
('NBA 2k22', 50, 50, TRUE, TRUE, 2),
('FIFA 2022', 69.99, 14, TRUE, TRUE, 2),
('Mass effect', 50, 32.5, TRUE, FALSE, 1),
('GTA V', 75, 60, TRUE, TRUE, 3),
('Assasins creed', 40.5, 22.5, FALSE, FALSE, 1);

INSERT INTO genre (game_id, genre) VALUES
(1, 'SPORTS'),
(2, 'SPORTS'),
(3, 'FPS'),
(3, 'ADVENTURE'),
(3, 'RPG'),
(4, 'RPG'),
(4, 'FPS'),
(5, 'ADVENTURE'),
(5, 'MMO');


INSERT INTO review (score, created_at, game_id, user_id) VALUES
(5, '2021-1-3 12:43:33', 1, 1),
(3, '2021-1-3 12:43:33', 4, 1),
(4, '2021-1-3 12:43:33', 3, 1),
(2, '2021-1-3 12:43:33', 2, 2),
(4, '2021-1-3 12:43:33', 3, 2),
(5, '2021-1-3 12:43:33', 4, 2),
(5, '2021-1-3 12:43:33', 5, 2),
(1, '2021-1-3 12:43:33', 1, 3),
(2, '2021-1-3 12:43:33', 2, 3),
(3, '2021-1-3 12:43:33', 3, 3),
(4, '2021-1-3 12:43:33', 4, 3),
(5, '2021-1-3 12:43:33', 5, 3),
(5, '2021-1-3 12:43:33', 1, 4),
(5, '2021-1-3 12:43:33', 2, 4),
(3, '2021-1-3 12:43:33', 4, 4),
(5, '2021-1-3 12:43:33', 1, 5),
(5, '2021-1-3 12:43:33', 2, 5),
(4, '2021-1-3 12:43:33', 3, 5),
(3, '2021-1-3 12:43:33', 4, 5),
(3, '2021-1-3 12:43:33', 5, 5),
(5, '2021-1-3 12:43:33', 1, 6),
(4, '2021-1-3 12:43:33', 3, 6),
(5, '2021-1-3 12:43:33', 5, 6),
(4, '2021-1-3 12:43:33', 2, 6),
(3, '2021-1-3 12:43:33', 4, 6);