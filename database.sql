CREATE DATABASE campusscholardb;

CREATE TABLE scholars (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    program VARCHAR(80) NOT NULL,
    year_level VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL
);
