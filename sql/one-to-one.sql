create table license(
	id serial primary key,
	seria int,
	number int
);

create table driver(
	id serial primary key,
	name varchar(255),
	license int references license(id)
);

insert into license(seria, number) values (6302, 321678);
insert into driver(name, license) values ('Иван', 1);

select * from driver;

select * from license;