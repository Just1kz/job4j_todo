create table if not exists item(
id serial primary key,
description text,
created timestamp default now(),
done boolean default false
);

insert into item(description) VALUES ('task1');
insert into item(description) VALUES ('task2');
insert into item(description) VALUES ('task3');
insert into item(description) VALUES ('task4');
insert into item(description) VALUES ('task5');
insert into item(description) VALUES ('task6');
insert into item(description) VALUES ('task7');
insert into item(description) VALUES ('task8');
insert into item(description) VALUES ('task9');
insert into item(description) VALUES ('task10');


