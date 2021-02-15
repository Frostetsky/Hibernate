create table brand(
    id serial primary key,
    name text
);

create table model(
    id serial primary key,
    name text,
    brand_id int references brand(id)
);