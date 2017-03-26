DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
-- VALUES ('User', 'user@yandex.ru', 'password');
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- admin
INSERT INTO users (name, email, password)
-- VALUES ('Admin', 'admin@gmail.com', 'admin');
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name)
VALUES ('Рога и копыта');

INSERT INTO restaurants (name)
VALUES ('Розовые пятачки');

INSERT INTO dishes (description, price, date, restaurant_id)
VALUES
  ('Рога под острым соусом', 5000, '2016-11-13', 100002),
  ('Копыта запеченные с яблоками', 6515, '2016-11-13',  100002),
  ('Компот из толченых копыт', 8005, '2016-11-12',  100002),
  ('Пятачки трех поросят маринованные', 7000,  '2016-11-13', 100003),
  ('Пятачки под майонезом', 5700, '2016-11-13',  100003),
  ('Настойка из пятачков', 6000,  '2016-11-12', 100003);

INSERT INTO votes (date, user_id, restaurant_id)
VALUES
  ('2016-11-13', 100000, 100002),
  ('2016-11-13', 100001, 100003),
  ('2016-11-12', 100000, 100002),
  ('2016-11-11', 100000, 100002),
  ('2016-11-12', 100001, 100003);
