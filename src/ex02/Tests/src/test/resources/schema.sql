create schema if not exists Tests;

create table Tests.products (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    price INT NOT NULL
)