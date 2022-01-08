create table categories (
	id serial primary key,
	name varchar(255)
);

create table statuses (
	id serial primary key,
	name varchar(255)
);

create table items (
	id serial primary key,
	name varchar(255),
	category_id int references categories(id),
	status_id int references statuses(id)
);

create table comments (
	id serial primary key,
	name text,
	item_id int references items(id)
);

insert into categories(name) values ('Обычная');
insert into categories(name) values ('Важная');
insert into categories(name) values ('Срочная');

insert into statuses(name) values ('Ожидание');
insert into statuses(name) values ('В работе');
insert into statuses(name) values ('Выполнена');
insert into statuses(name) values ('Отклонена');

insert into items(name, category_id, status_id) values ('Заменить лампочку в подъезде', 1, 1);
insert into items(name, category_id, status_id) values ('Подготовка к отоплению', 2, 2);

insert into comments(name, item_id) values ('Узкий цоколь', 1);
insert into comments(name, item_id) values ('Расклеить объявления', 2);

select 
	it.name as item,
	cat.name as category
from
	items as it
	join categories as cat
		on it.category_id = cat.id;

select 
	it.name as item,
	st.name as status
from
	items as it
	join statuses as st
		on it.status_id = st.id;

select 
	it.name as item,
	com.name as comment
from
	items as it
	join comments as com
		on it.id = com.item_id;