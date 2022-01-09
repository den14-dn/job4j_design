create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploers(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Department 1');
insert into departments(name) values ('Department 2');
insert into departments(name) values ('Department 3');

insert into emploers(name, department_id) values ('Emploer 1', 1);
insert into emploers(name, department_id) values ('Emploer 2', 2);
insert into emploers(name, department_id) values ('Emploer 3', 3);
insert into emploers(name, department_id) values ('Emploer 4', null);
insert into emploers(name, department_id) values ('Emploer 5', null);
insert into emploers(name, department_id) values ('Emploer 6', 1);

select
	e.name emp,
	d.name dep
from 
	emploers e 
	left join departments d 
		on e.department_id = d.id;

select
	e.name emp,
	d.name dep
from 
	departments d
	right join emploers e
		on d.id = e.department_id;

select
	e.name emp,
	d.name dep
from 
	departments d
	full join emploers e
		on d.id = e.department_id;

select
	e.name emp,
	d.name dep
from 
	departments d
	cross join emploers e;

select
	e.name emp,
	d.name dep
from 
	departments d
	left join emploers e
		on d.id = e.department_id
where
	e.id is null;

select e.name emp, d.name dep from emploers e left join departments d on e.department_id = d.id;
select e.name emp, d.name dep from departments d right join emploers e on d.id = e.department_id;

create table teens(
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Иван', 'm');
insert into teens(name, gender) values ('Ольга', 'w');

select n1.name as a, n2.name as b from teens n1 cross join teens n2;