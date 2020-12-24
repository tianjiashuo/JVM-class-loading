create database jvm;


create table receive
(
    id        int auto_increment
        primary key,
    file_name varchar(20) not null,
    content   text        null,
    vclass    text        null comment 'javap -v',
    cclass    text        null comment 'javap -c'
);

