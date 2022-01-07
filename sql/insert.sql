insert into role(name) values ('Только чтение');
insert into role(name) values ('Полные права');

insert into users(name, role_id) values ('Иван', 1);
insert into users(name, role_id) values ('Петр', 1);
insert into users(name, role_id) values ('Администратор', 2);

insert into rules(name) values ('Чтение');
insert into rules(name) values ('Изменение');
insert into rules(name) values ('Просмотр');
insert into rules(name) values ('Редактирование');
insert into rules(name) values ('Удаление');

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 3);
insert into role_rules(role_id, rules_id) values (2, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (2, 3);
insert into role_rules(role_id, rules_id) values (2, 4);
insert into role_rules(role_id, rules_id) values (2, 5);

insert into category(name) values ('Обычная');
insert into category(name) values ('Важная');
insert into category(name) values ('Срочная');

insert into state(name) values ('В ожидании');
insert into state(name) values ('В работе');
insert into state(name) values ('Выполнена');
insert into state(name) values ('Отклонена');

insert into item(name, user_id, category_id, state_id) values ('Закупить лампочки', 1, 1, 2);
insert into item(name, user_id, category_id, state_id) values ('Разблокировать лифт', 2, 3, 1);

insert into comments(name, item_id) values ('Плановая закупка расходников', 1);
insert into comments(name, item_id) values ('Срочно в работу, в лифте люди', 2);

insert into attachs(name, item_id) values ('Фото цоколя', 1);