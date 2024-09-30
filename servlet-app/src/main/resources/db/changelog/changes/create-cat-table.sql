--liquibase formatted sql
--changeset tdeeva:create_cat_table

create table cat
(
    id      serial primary key,
    name    varchar,
    age     int,
    gender  varchar,
    color   varchar
);