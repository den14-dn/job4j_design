CREATE DATABASE warehouses
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
create table warehouses(
	id serial primary key,
	enabled boolean,
	destination text,
	square integer
);
insert into warehouses(enabled, destination, square) values(true, 'На Московской', 3655);
select * from warehouses;
update warehouses set destination = 'На Московской, дом 22А';
select * from warehouses;
delete from warehouses;