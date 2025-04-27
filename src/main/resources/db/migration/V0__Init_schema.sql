-- Create users table
CREATE TABLE IF NOT EXISTS users
(
    id       BINARY(16) PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    role     VARCHAR(50)  NOT NULL
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
    count          INT          NULL,
    name           VARCHAR(50)  NULL UNIQUE,
    vintage        INT          NULL,
    country        VARCHAR(50)  NULL,
    color          VARCHAR(50)  NULL,
    regions        VARCHAR(255) NULL,
    classification VARCHAR(50)  NULL,
    primeur        BOOLEAN      NULL
);

-- Create catalog_wines table
CREATE TABLE IF NOT EXISTS catalog_wines
(
    id                      BINARY(16) PRIMARY KEY,
    points                  INT             NULL,
    title                   VARCHAR(255)    NULL,
    description             LONGTEXT        NULL,
    taster_name             VARCHAR(50)     NULL,
    taster_twitter_handle   VARCHAR(50)     NULL,
    price                   DECIMAL(10, 2)  NULL,
    designation             VARCHAR(50)     NULL,
    variety                 VARCHAR(50)     NULL,
    region_1                VARCHAR(50)     NULL,
    region_2                VARCHAR(50)     NULL,
    province                VARCHAR(50)     NULL,
    country                 VARCHAR(50)     NULL,
    winery                  VARCHAR(50)     NULL
);