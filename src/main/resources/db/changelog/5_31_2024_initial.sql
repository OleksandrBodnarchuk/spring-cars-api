--liquibase formatted sql
--changeset alex:5_31_2024_initial
create sequence body_sequence start with 1 increment by 10;
create sequence brand_sequence start with 1 increment by 10;
create sequence chasis_sequence start with 1 increment by 10;
create sequence engine_sequence start with 1 increment by 10;
create sequence model_sequence start with 1 increment by 10;
create sequence modification_sequence start with 1 increment by 10;
create sequence running_feature_sequence start with 1 increment by 10;

create table body
(
    id            bigint not null,
    boot_capacity varchar(255),
    height        integer,
    length        integer,
    max_width     integer,
    weight        integer,
    wheel_base    varchar(255),
    width         integer,
    primary key (id)
);

create table brand
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table chasis
(
    id           bigint not null,
    abs          varchar(255),
    front_brakes varchar(255),
    rear_brakes  varchar(255),
    tires        varchar(255),
    wheels       varchar(255),
    primary key (id)
);

create table engine
(
    id                  bigint not null,
    cylinder_diameter   float(53),
    cylinders           integer,
    displacement        varchar(255),
    eco_standart        varchar(255),
    fuel                varchar(255),
    fuel_capacity       integer,
    fuel_supply         varchar(255),
    gears               integer,
    hp                  integer,
    kw                  integer,
    torque              varchar(255),
    valves_in_cylinders integer,
    primary key (id)
);

create table general_info
(
    id               bigserial not null,
    body_type        varchar(255),
    brand            varchar(255),
    doors            varchar(255),
    engine           varchar(255),
    model            varchar(255),
    seats            varchar(255),
    year_of_creation varchar(255),
    primary key (id)
);

create table model
(
    id       bigint not null,
    name     varchar(255),
    brand_id bigint,
    primary key (id)
);

create table modification
(
    id                 bigint not null,
    name               varchar(255),
    body_id            bigint,
    chassis_id         bigint,
    engine_id          bigint,
    general_info_id    bigint,
    model_id           bigint,
    running_feature_id bigint,
    primary key (id)
);

create table running_feature
(
    id           bigint not null,
    acceleration float(53),
    fuel_average float(53),
    fuel_road    float(53),
    fuel_town    float(53),
    max_speed    integer,
    primary key (id)
);

alter table if exists model
    add constraint FKhbgv4j3vpt308sepyq9q79mhu
        foreign key (brand_id)
            references brand;

alter table if exists modification
    add constraint FKkqmi0f5a34vx9brvmdqikg9d4
        foreign key (body_id)
            references body;

alter table if exists modification
    add constraint FKbot7u4kq7kkhnxypipgj6j3af
        foreign key (chassis_id)
            references chasis;

alter table if exists modification
    add constraint FKqocbq3mbqjq2xpybdiibbpkt6
        foreign key (engine_id)
            references engine;

alter table if exists modification
    add constraint FKfuk89v1d6dceo0er3120tgusd
        foreign key (general_info_id)
            references general_info;

alter table if exists modification
    add constraint FK9uff0co2pqf3nqynpf9eq2d8l
        foreign key (model_id)
            references model;

alter table if exists modification
    add constraint FKix4mfjxc1ue5hr4ht5kmu3vjx
        foreign key (running_feature_id)
            references running_feature;
