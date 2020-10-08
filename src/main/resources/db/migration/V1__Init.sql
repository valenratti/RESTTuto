create table employee(
    id bigserial not null primary key,
    name varchar not null,
    role varchar not null,
    first_name varchar not null,
    last_name varchar not null
);

create table car(
    id bigserial not null primary key,
    color varchar not null,
    model varchar not null,
    year int not null,
    owner_id bigint
);