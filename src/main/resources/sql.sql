/*
CREATE TABLE task
(
  start_time  TIMESTAMP NOT NULL,
  finish_time TIMESTAMP,
  status      VARCHAR(100),
  user_id     BIGINT,
  task_id     BIGSERIAL NOT NULL
    CONSTRAINT task_task_id_pk
    PRIMARY KEY,
  algorithm   VARCHAR(20),
  result      VARCHAR(255)
);

CREATE UNIQUE INDEX task_task_id_uindex
  ON task (task_id);

CREATE TABLE users
(
  uuid VARCHAR(255) NOT NULL,
  id   BIGSERIAL    NOT NULL
    CONSTRAINT user_id_pk
    PRIMARY KEY
);

CREATE UNIQUE INDEX user_uuid_uindex
  ON users (uuid);

CREATE UNIQUE INDEX user_id_uindex
  ON users (id);

ALTER TABLE task
  ADD CONSTRAINT task_users_id_fk
FOREIGN KEY (user_id) REFERENCES users;

*/
