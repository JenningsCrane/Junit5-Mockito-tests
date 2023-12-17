create schema if not exists Tests;

create table Tests.products (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
)