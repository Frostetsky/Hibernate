create database population;

create table country (
    id serial primary key,
    name varchar(50),
)

create table person (
    id serial primary key,
    firstname varchar(60),
    lastname varchar(60),
    int age,
    country_id int references country(id)
)