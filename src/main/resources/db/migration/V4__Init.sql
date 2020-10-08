drop table if exists employee_owned_cars;
drop table if exists car;

create table car(
    carid bigserial not null primary key,
    color varchar not null,
    model varchar not null,
    year int not null,
    owner_id bigint
);

CREATE TABLE public.employee_owned_cars
(
    employee_id bigint NOT NULL,
    owned_cars_carid bigint NOT NULL,
    CONSTRAINT uk_kvvw56312j4ccjb0x9trmig54 UNIQUE (owned_cars_carid),
    CONSTRAINT fk3ttwrwhscn9xh8jngecwl9im FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkcxid1pctraogos4yike99dru3 FOREIGN KEY (owned_cars_carid)
        REFERENCES public.car (carid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

