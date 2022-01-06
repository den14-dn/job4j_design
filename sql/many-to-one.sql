create table school(
	id serial primary key,
	name varchar(255)
);

create table students(
	id serial primary key,
	name varchar(255),
	school int references school(id)
);

insert into school(name) values ('СОШ №4');
insert into students(name, school) values ('Иван', 1);
insert into students(name, school) values ('Роман', 1);

select * from school;

select * from students;