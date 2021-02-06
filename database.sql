drop database if exists NEWSPAPER_DELIVERY_SYSTEM;
create database NEWSPAPER_DELIVERY_SYSTEM;
use NEWSPAPER_DELIVERY_SYSTEM;

drop table if exists DELIVERY_DOCKETS;
create table if not exists DELIVERY_DOCKETS (
	deliveryDocketID integer auto_increment not null primary key,
    publicationID integer not null,
    deliveryAreaID integer not null,
    customerID integer not null
);

insert into DELIVERY_DOCKETS values (default, 1, 1, 1);

select * from DELIVERY_DOCKETS;