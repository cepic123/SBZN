INSERT INTO users (username, password, name, lastname) VALUES
('user1', 'user1', 'Pera', 'Peric'),
('user2', 'user2', 'Mika', 'Mikic'),
('user3', 'user3', 'Nikola', 'Nikolic'),
('user4', 'user4', 'Nikola', 'Nikolic'),
('user5', 'user5', 'Nikola', 'Nikolic');

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
(4, '2021-1-3 12:43:33', 1, 4),
(4, '2021-1-3 12:43:33', 2, 4),
(4, '2021-1-3 12:43:33', 3, 4);