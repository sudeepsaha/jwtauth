create database opencourse;
use opencourse;
create table user_role(
    id bigint(10) auto_increment primary key,
    username varchar(100) not null unique,
    password varchar(100) not null,
    first_name varchar(100) not null,
    last_name varchar(100),
    email varchar(100) not null unique,
    phno varchar(20) not null,
    role varchar(100) not null);

insert into user_role 
    (username, password, first_name, email, phno, role) 
values
    ('admin', 'admin', 'Admin', 'admin@test.mail', '1234567890', 'Admin');

