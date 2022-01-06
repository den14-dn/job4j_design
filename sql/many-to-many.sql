create table subjects(
	id serial primary key,
	name varchar(255)
);
create table students(
	id serial primary key,
	name varchar(255)
);
create table students_subjects(
	id serial primary key,
	student int references students(id),
	subject int references subjects(id)
);

insert into subjects(name) values ('Базы данных');
insert into subjects(name) values ('Алгоритмы');
insert into students(name) values ('Иван');
insert into students(name) values ('Роман');
insert into students_subjects(student, subject) values (1, 1);
insert into students_subjects(student, subject) values (1, 2);
insert into students_subjects(student, subject) values (2, 1);
insert into students_subjects(student, subject) values (2, 2);

select * from subjects;
select * from students;
select * from students_subjects;