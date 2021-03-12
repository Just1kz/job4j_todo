create table if not exists item(
id serial primary key,
description text,
created text,
done boolean
);

insert into item(description, created, done) VALUES ('task1', '15:05:03  01.01.2021', false);
insert into item(description, created, done) VALUES ('task2', '15:05:03  02.01.2021', false);
insert into item(description, created, done) VALUES ('task3', '15:05:03  03.01.2021', false);
insert into item(description, created, done) VALUES ('task4', '15:05:03  04.01.2021', false);
insert into item(description, created, done) VALUES ('task5', '15:05:03  05.01.2021', false);
insert into item(description, created, done) VALUES ('task6', '15:05:03  06.01.2021', false);
insert into item(description, created, done) VALUES ('task7', '15:05:03  07.01.2021', false);
insert into item(description, created, done) VALUES ('task8', '15:05:03  08.01.2021', false);


