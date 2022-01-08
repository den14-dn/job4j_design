create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('часы', 945.90);
insert into devices (name, price) values ('утюг', 7350.20);
insert into devices (name, price) values ('чайник', 1590.50);

insert into people (name) values ('Иван');
insert into people (name) values ('Роман');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 2);

select avg(price) as avg_price from devices;

select 
	people.name as people,
	avg(devices.price) as avg_price
from 
	devices_people as dp
	inner join people as people
		on dp.people_id = people.id
	inner join devices as devices
		on dp.device_id = devices.id
group by
	people.name;

select 
	people.name as people,
	avg(devices.price) as avg_price
from 
	devices_people as dp
	inner join people as people
		on dp.people_id = people.id
	inner join devices as devices
		on dp.device_id = devices.id
group by
	people.name
having 
	avg(devices.price) > 5000;