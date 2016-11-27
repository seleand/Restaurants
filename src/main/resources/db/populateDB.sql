DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name)
VALUES ('restaurant 1');

INSERT INTO restaurants (name)
VALUES ('restaurant 2');

INSERT INTO dishes (description, price, date, restaurant_id)
VALUES
  ('dish 1 rest1', 5000, '2016-11-13', 100002),
  ('dish 2 rest1', 6515, '2016-11-13',  100002),
  ('dish 3 rest1', 8005, '2016-11-12',  100002),
  ('dish 1 rest2', 7000,  '2016-11-13', 100003),
  ('dish 2 rest2', 5700, '2016-11-13',  100003),
  ('dish 3 rest3', 6000,  '2016-11-12', 100003);
