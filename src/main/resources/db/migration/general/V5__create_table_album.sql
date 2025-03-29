CREATE TABLE album
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL UNIQUE,
    release_date timestamp(6) WITH TIME ZONE
);