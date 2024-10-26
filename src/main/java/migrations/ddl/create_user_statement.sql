create table user
(
    id         bigint       not null primary key,
    age        int          not null,
    first_name varchar(255) not null,
    last_name  varchar(255),
    email      varchar(255) not null,
    password   varchar(800) not null
);