show databases;
use aspire_fitness;
create table users(
	fullName varchar(50) not null,
    phoneNo varchar(50) primary key,
    email varchar(35),
    username varchar(18),
    password varchar(18)
);

create table tasks(
    id int primary key auto_increment,
    task_title varchar(25),
    task_description text,
    deadline datetime not null,
    task_priority enum('high', 'medium', 'low') default 'low',
    task_category enum('school', 'work', 'medicine') default 'work',
    reminder_date datetime not null
);

show tables;
describe tasks;
describe users;
select * from users;
select * from tasks;