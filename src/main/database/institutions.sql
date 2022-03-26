create database institutions;

create table school(
    id serial primary key,
    name varchar(100),
    description varchar(200)
);

create table director(
    id serial primary key,
    name varchar(30),
    school_id int,
    foreign key (school_id) references school
);

