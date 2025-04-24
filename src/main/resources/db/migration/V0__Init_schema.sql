-- Create roles table
CREATE TABLE IF NOT EXISTS roles
(
    id   BINARY(16) PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- Create users table
CREATE TABLE IF NOT EXISTS users
(
    id       BINARY(16) PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL
);

-- Create Caves table
CREATE TABLE IF NOT EXISTS caves
(
    id   BINARY(16) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Create wines table
CREATE TABLE IF NOT EXISTS wines
(
    id             BINARY(16) PRIMARY KEY,
    cave_id        BINARY(16) REFERENCES caves (id),
    appellation    VARCHAR(50)  NULL UNIQUE,
    color          VARCHAR(50)  NULL,
    regions        VARCHAR(255) NULL,
    country        VARCHAR(50)  NULL,
    classification VARCHAR(50)  NULL,
    vintage        INT          NULL,
    date           DATE         NULL,
    is_primeur     BOOLEAN      NULL
);


-- Insert initial roles
INSERT INTO roles(id, name) VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ROLE_USER');
INSERT INTO roles(id, name) VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ROLE_MODERATOR');
INSERT INTO roles(id, name) VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ROLE_ADMIN');