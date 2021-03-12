create table if not exists item(
id serial primary key,
description text,
created text,
done boolean default false
);

insert into item(description, created) VALUES ('task1', '15:05:03  01.01.2021');
insert into item(description, created) VALUES ('task2', '15:05:03  02.01.2021');
insert into item(description, created) VALUES ('task3', '15:05:03  03.01.2021');
insert into item(description, created) VALUES ('task4', '15:05:03  04.01.2021');
insert into item(description, created) VALUES ('task5', '15:05:03  05.01.2021');
insert into item(description, created) VALUES ('task6', '15:05:03  06.01.2021');
insert into item(description, created) VALUES ('task7', '15:05:03  07.01.2021');
insert into item(description, created) VALUES ('task8', '15:05:03  08.01.2021');
insert into item(description, created) VALUES ('task9', '15:05:03  08.01.2021');
insert into item(description, created) VALUES ('task10', '15:05:03  08.01.2021');


