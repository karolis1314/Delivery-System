DROP DATABASE IF EXISTS newsagent2021;
CREATE DATABASE IF NOT EXISTS newsagent2021;
use newsagent2021;

CREATE TABLE IF NOT EXISTS delivery_areas (
                                              id int AUTO_INCREMENT PRIMARY KEY,
                                              Aname VARCHAR(50),
    size int
    );

CREATE TABLE publication (
                             id INTEGER(50) AUTO_INCREMENT PRIMARY KEY,
                             publication_order_id varchar(7)NOT NULL,
                             publicationName VARCHAR(15) NOT NULL,
                             price_in_€ DOUBLE NOT NULL);


insert into delivery_areas VALUES (DEFAULT,'Area1',1523);

INSERT INTO publication VALUES(DEFAULT,"A000001","Fobes",10.00);

SELECT * from publication;

SELECT * FROM delivery_areas;
