drop table if exists body;
create table body (
id bigint not null auto_increment,
boot_capacity varchar(255),
height varchar(255),
length varchar(255),
max_width varchar(255),
weight varchar(255),
wheel_base varchar(255),
width varchar(255),
primary key (id)
);

drop table if exists brand;

create table brand (
id bigint not null auto_increment,
name varchar(255),
primary key (id)
);

drop table if exists chasis;

create table chasis (
id bigint not null auto_increment,
abs varchar(255),
front_brakes varchar(255),
rear_brakes varchar(255),
tires varchar(255),
wheels varchar(255),
primary key (id)
);
drop table if exists engine;

create table engine (
id bigint not null auto_increment,
valves_in_cylinders varchar(255),
cylinder_diameter varchar(255),
cylinders varchar(255),
displacement varchar(255),
eco_standart varchar(255),
fuel varchar(255),
fuel_capacity varchar(255),
fuel_supply varchar(255),
gears varchar(255),
hp varchar(255),
kw varchar(255),
torque varchar(255),
primary key (id)
);
drop table if exists general_info;

create table general_info (
id bigint not null auto_increment,
body_type varchar(255),
brand varchar(255),
doors varchar(255),
engine varchar(255),
model varchar(255),
seats varchar(255),
year varchar(255),
primary key (id)
);
drop table if exists logos;

create table logos (
id bigint not null auto_increment,
data BLOB NOT NULL,
name varchar(255),
primary key (id)
);
drop table if exists model;

create table model (
id bigint not null auto_increment,
name varchar(255),
brand_id bigint,
primary key (id)
);
drop table if exists modification;

create table modification (
id bigint not null auto_increment,
name varchar(255),
body_id bigint,
chassis_id bigint,
engine_id bigint,
general_info_id bigint,
picture_id bigint,
running_feature_id bigint,
submodel_id bigint,
primary key (id)
);
drop table if exists picture;

create table picture (
id bigint not null auto_increment,
data BLOB NOT NULL,
is_main_picture bit not null,
name varchar(255),
primary key (id)
);
drop table if exists running_feature;

create table running_feature (
id bigint not null auto_increment,
acceleration varchar(255),
fuel_average varchar(255),
fuel_road varchar(255),
fuel_town varchar(255),
max_speed varchar(255),
primary key (id)
);
drop table if exists sub_model;

create table sub_model (
id bigint not null auto_increment,
name varchar(255),
model_id bigint,
primary key (id)
);

alter table model 
add constraint FKhbgv4j3vpt308sepyq9q79mhu 
foreign key (brand_id) 
references brand (id);

alter table modification 
add constraint FKkqmi0f5a34vx9brvmdqikg9d4 
foreign key (body_id) 
references body (id);

alter table modification 
add constraint FKbot7u4kq7kkhnxypipgj6j3af 
foreign key (chassis_id) 
references chasis (id);

alter table modification 
add constraint FKqocbq3mbqjq2xpybdiibbpkt6 
foreign key (engine_id) 
references engine (id);

alter table modification 
add constraint FKfuk89v1d6dceo0er3120tgusd 
foreign key (general_info_id) 
references general_info (id);

alter table modification 
add constraint FKgea0qccn32ok6d4457ctv8nrr 
foreign key (picture_id) 
references picture (id);

alter table modification 
add constraint FKix4mfjxc1ue5hr4ht5kmu3vjx 
foreign key (running_feature_id) 
references running_feature (id);

alter table modification 
add constraint FK3p0q2l4mihkrv56p6xb1v02bb 
foreign key (submodel_id) 
references sub_model (id);

alter table sub_model 
add constraint FKdtmvg6da0peasf1i06gdgb1e9 
foreign key (model_id) 
references model (id);