drop table if exists employee;
create table employee(
    id bigserial not null primary key,
    name varchar,
    role varchar not null,
    first_name varchar not null,
    last_name varchar not null
);