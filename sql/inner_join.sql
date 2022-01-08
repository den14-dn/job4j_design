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
	item.name as item,
	category.name as category,
	status.name as status,
	comment.name as comment
from
	items as item
	inner join categories as category
		on item.category_id = category.id
	inner join statuses as status
		on item.status_id = status.id
	inner join comments as comment
		on item.id = comment.item_id