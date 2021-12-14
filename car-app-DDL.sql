-- create schema car_app;

/* 

 block comments

*/


create table car (
	id serial primary key,
	make varchar(100) not null,
	model varchar(100) not null,
	year integer not null,
	status varchar(30) not null,
	color varchar(30) not null
	-- name,type,constraint
);

create table person (
	id serial primary key,
	full_name varchar(100) not null,
	username varchar(30) unique not null,
	passwd varchar(45) not null,
	user_name varchar(30) not null 
	-- name,type,constraint
);

create table car_owner (
	car_id integer references car,
	owner_id integer references person
); 

alter table person drop column user_name;

commit;

select * from person;