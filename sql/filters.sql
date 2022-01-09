create table type (
	id serial primary key,
	name varchar(255)
);

create table product (
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('МОРОЖЕНОЕ');
insert into type (name) values ('КЕФИР');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022-01-07', 113.90);
insert into product (name, type_id, expired_date, price) values ('Сыр Гауда', 1, '2022-01-11', 345.10);
insert into product (name, type_id, expired_date, price) values ('Сыр Российский', 1, '2022-01-19', 167.40);
insert into product (name, type_id, expired_date, price) values ('Сыр Пармезан', 1, '2022-01-09', 234.90);

insert into product (name, type_id, expired_date, price) values ('Молоко Домик в деревне', 2, '2022-01-09', 84.90);
insert into product (name, type_id, expired_date, price) values ('Молоко Простоквашино', 2, '2022-01-09', 88.10);
insert into product (name, type_id, expired_date, price) values ('Молоко Фермерский двор', 2, '2022-01-15', 115.60);

insert into product (name, type_id, expired_date, price) values ('Мороженое Шоколадное', 3, '2022-02-09', 34.70);
insert into product (name, type_id, expired_date, price) values ('Мороженое Пломбир', 3, '2022-02-10', 27.90);
insert into product (name, type_id, expired_date, price) values ('Мороженое Ёжик', 3, '2022-01-30', 45.80);
insert into product (name, type_id, expired_date, price) values ('Мороженое Чистая линия Брикет', 3, '2022-01-15', 234.90);

insert into product (name, type_id, expired_date, price) values ('Кефир Домик в деревне', 4, '2022-01-09', 112.20);
insert into product (name, type_id, expired_date, price) values ('Кефир Простоквашино', 4, '2022-01-08', 108.30);

select * 
from 
	product as prod
	join type as tp
	 on prod.type_id = tp.id
	 	and tp.name = 'СЫР';

select * 
from 
	product as prod
where
	prod.name like '%Мороженое%';

select * 
from 
	product as prod
where
	prod.expired_date < current_date;

select *
from
	product as prod
where
	prod.price = (select max(price) from product);

select 
	tp.name as type,
	count(prod.name) as count
from
	product as prod
		join type as tp
		on prod.type_id = tp.id
group by
	tp.name;

select * 
from 
	product as prod
	join type as tp
	 on prod.type_id = tp.id
	 	and (tp.name = 'СЫР' or tp.name = 'МОЛОКО');

select 
	tp.name as type,
	count(prod.name) as count
from
	product as prod
		join type as tp
		on prod.type_id = tp.id
group by
	tp.name
having
	count(prod.name) < 10;

select 
	tp.name as type,
	prod.name as product
from
	product as prod
		join type as tp
		on prod.type_id = tp.id;