drop table if exists manufacturers;
create table manufacturer (
id bigint not null auto_increment,
manufacturer_value bigint,
name varchar(255),
logo_id bigint default null,
primary key (id)
);

drop table if exists logos;
create table logos (
id bigint not null auto_increment,
data BLOB NOT NULL,
name varchar(255),
primary key (id)
);

drop table if exists models;
create table model (
id bigint not null auto_increment,
name varchar(255) default null
logo_id bigint default null,
manufacturer_id bigint default null,
primary key (id)
);
