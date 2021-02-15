create database candidates;

create table candidate(
    id serial primary key,
    name varchar(30),
    experience varchar(30),
    salary int
);