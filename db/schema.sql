create table if not exists users(
    idU serial primary key ,
    name text,
    email text unique,
    password text
);

create table if not exists item(
id serial primary key,
description text,
created timestamp default now(),
author_id int not null references users(idU) default 1,
categories_id int not null references categories(id) default 3,
done boolean default false
);

insert into users(name, email, password) VALUES ('Admin', 'abc@gmail.com', '123');
insert into users(name, email, password) VALUES ('zxc', 'zxc', 'zxc');

insert into item(description) VALUES ('task1');
insert into item(description) VALUES ('task2');
insert into item(description) VALUES ('task3');


SELECT item.id, item.description, item.created, u.name, item.done from item
left join users u on u.idU = item.author_id;



