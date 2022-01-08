create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('cisco', 11245, '1864-01-01');
insert into fauna (name, avg_age, discovery_date) values ('catfish', 14432, '1758-01-01');
insert into fauna (name, avg_age, discovery_date) values ('cod', 1845, '1758-01-01');
insert into fauna (name, avg_age, discovery_date) values ('sole fish', 18445, '1758-01-01');
insert into fauna (name, avg_age, discovery_date) values ('herring', 1545, null);
insert into fauna (name, avg_age, discovery_date) values ('ide', 5475, '1961-01-01');
insert into fauna (name, avg_age, discovery_date) values ('eel', 1245, '1980-01-01');

select * from fauna where discovery_date < '1950-01-01';
select * from fauna where avg_age >= 10000 AND avg_age <= 21000;
select * from fauna as fauna where fauna.name LIKE '%fish%';
select * from fauna where discovery_date is null;