CREATE TABLE IF NOT EXISTS users_default
(
  id       UUID PRIMARY KEY,
  name     VARCHAR NOT NULL,
  age      INT     NOT NULL,
  phone_id UUID
);

CREATE TABLE IF NOT EXISTS phones_default
(
  id     UUID PRIMARY KEY,
  number VARCHAR NOT NULL,
  type   VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_lombok
(
  id       UUID PRIMARY KEY,
  name     VARCHAR NOT NULL,
  age      INT     NOT NULL,
  phone_id UUID
);

CREATE TABLE IF NOT EXISTS phones_lombok
(
  id     UUID PRIMARY KEY,
  number VARCHAR NOT NULL,
  type   VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_lombok_adjusted
(
  id       UUID PRIMARY KEY,
  name     VARCHAR NOT NULL,
  age      INT     NOT NULL,
  phone_id UUID
);

CREATE TABLE IF NOT EXISTS phones_lombok_adjusted
(
  id     UUID PRIMARY KEY,
  number VARCHAR NOT NULL,
  type   VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_custom
(
  id       UUID PRIMARY KEY,
  name     VARCHAR NOT NULL,
  age      INT     NOT NULL,
  phone_id UUID
);

CREATE TABLE IF NOT EXISTS phones_custom
(
  id     UUID PRIMARY KEY,
  number VARCHAR NOT NULL,
  type   VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_proper
(
  id       UUID PRIMARY KEY,
  name     VARCHAR NOT NULL,
  age      INT     NOT NULL,
  phone_id UUID
);

CREATE TABLE IF NOT EXISTS phones_proper
(
  id     UUID PRIMARY KEY,
  number VARCHAR NOT NULL,
  type   VARCHAR NOT NULL
);

INSERT INTO users_default (id, name, age, phone_id)
VALUES ('871bc1300b3649aeb631b298b95e9478', 'Alice', 20, '0749be042188491bb62c83c717c69671'),
       ('dc6086523fb944a19556bfc425a58ea2', 'Bob', 42, null),
       ('adab0f6011b249c0bfdcd24361dbb021', 'Charlie', 37, null);

INSERT INTO phones_default (id, number, type)
VALUES ('0749be042188491bb62c83c717c69671', '+123456789', 'office'),
       ('3bc823dcd6f84544a612b07e84b7b88c', '+987654321', 'home');

INSERT INTO users_lombok (id, name, age, phone_id)
VALUES ('871bc1300b3649aeb631b298b95e9478', 'Alice', 20, '0749be042188491bb62c83c717c69671'),
       ('dc6086523fb944a19556bfc425a58ea2', 'Bob', 42, null),
       ('adab0f6011b249c0bfdcd24361dbb021', 'Charlie', 37, null);

INSERT INTO phones_lombok (id, number, type)
VALUES ('0749be042188491bb62c83c717c69671', '+123456789', 'office'),
       ('3bc823dcd6f84544a612b07e84b7b88c', '+987654321', 'home');

INSERT INTO users_lombok_adjusted (id, name, age, phone_id)
VALUES ('871bc1300b3649aeb631b298b95e9478', 'Alice', 20, '0749be042188491bb62c83c717c69671'),
       ('dc6086523fb944a19556bfc425a58ea2', 'Bob', 42, null),
       ('adab0f6011b249c0bfdcd24361dbb021', 'Charlie', 37, null);

INSERT INTO phones_lombok_adjusted (id, number, type)
VALUES ('0749be042188491bb62c83c717c69671', '+123456789', 'office'),
       ('3bc823dcd6f84544a612b07e84b7b88c', '+987654321', 'home');

INSERT INTO users_custom (id, name, age, phone_id)
VALUES ('871bc1300b3649aeb631b298b95e9478', 'Alice', 20, '0749be042188491bb62c83c717c69671'),
       ('dc6086523fb944a19556bfc425a58ea2', 'Bob', 42, null),
       ('adab0f6011b249c0bfdcd24361dbb021', 'Charlie', 37, null);

INSERT INTO phones_custom (id, number, type)
VALUES ('0749be042188491bb62c83c717c69671', '+123456789', 'office'),
       ('3bc823dcd6f84544a612b07e84b7b88c', '+987654321', 'home');

INSERT INTO users_proper (id, name, age, phone_id)
VALUES ('871bc1300b3649aeb631b298b95e9478', 'Alice', 20, '0749be042188491bb62c83c717c69671'),
       ('dc6086523fb944a19556bfc425a58ea2', 'Bob', 42, null),
       ('adab0f6011b249c0bfdcd24361dbb021', 'Charlie', 37, null);

INSERT INTO phones_proper (id, number, type)
VALUES ('0749be042188491bb62c83c717c69671', '+123456789', 'office'),
       ('3bc823dcd6f84544a612b07e84b7b88c', '+987654321', 'home');
