create table employee_owned_cars(
    employee_id bigint NOT NULL PRIMARY KEY,
    owned_cars_carid bigint NOT NULL UNIQUE
);
