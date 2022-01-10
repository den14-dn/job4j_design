create table body (
	id serial primary key,
	name text
);

create table engine (
	id serial primary key,
	name text
);

create table transmission (
	id serial primary key,
	name text
);

create table car (
	id serial primary key,
	name text,
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body (name) values ('седан');
insert into body (name) values ('купе');
insert into body (name) values ('хетчбек');
insert into body (name) values ('кроссовер');
insert into body (name) values ('кабриолет');

insert into engine (name) values ('рядная четвёрка');
insert into engine (name) values ('рядная шестёрка');
insert into engine (name) values ('V6');
insert into engine (name) values ('V8');
insert into engine (name) values ('рядная тройка');

insert into transmission (name) values ('механическая');
insert into transmission (name) values ('гидроавтомат');
insert into transmission (name) values ('вариатор');
insert into transmission (name) values ('роботизированная');

insert into car (name, body_id, engine_id, transmission_id) values ('Toyota Corolla', 1, 1, 1);
insert into car (name, body_id, engine_id, transmission_id) values ('AUDI TT', 2, 3, 2);
insert into car (name, body_id, engine_id, transmission_id) values ('KIA Ceed', 3, 1, 2);
insert into car (name, body_id, engine_id, transmission_id) values ('Toyota RAV4', 4, 1, 3);
insert into car (name, body_id, engine_id, transmission_id) values ('BMW', 1, 2, 2);
insert into car (name, body_id, engine_id, transmission_id) values ('Toyota Land Cruiser', 4, 4, 2);

select
	car.name car,
	b.name body,
	e.name engine,
	t.name transmission
from
	car car
		join body b on car.body_id = b.id
		join engine e on car.engine_id = e.id
		join transmission t on car.transmission_id = t.id;

select
	b.name body
from
	body b
		left join car car on b.id = car.body_id
where
	car.id is null;
	
select
	e.name engine
from
	engine e
		left join car car on e.id = car.engine_id
where
	car.id is null;
	
select
	t.name transmission
from
	transmission t
		left join car car on t.id = car.transmission_id
where
	car.id is null;