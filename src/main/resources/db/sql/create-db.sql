--DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  username VARCHAR(50),
  password varchar(50),
  email  VARCHAR(50)
);
