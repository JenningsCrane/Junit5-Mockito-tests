create schema if not exists Tests;

create table Tests.products (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    price INT NOT NULL
);

create table Tests.users (
    id INTEGER IDENTITY PRIMARY KEY,
    login varchar(25),
    password varchar(25),
    authentication boolean not null
);