DROP DATABASE IF EXISTS newsagent2021;
CREATE DATABASE IF NOT EXISTS newsagent2021;
use newsagent2021;

CREATE TABLE IF NOT EXISTS delivery_areas (
                                              id int AUTO_INCREMENT PRIMARY KEY,
                                              Aname VARCHAR(50),
    size int
    );

DROP TABLE IF EXISTS PUBLICATION;
CREATE TABLE publication (
                             id INTEGER(50) AUTO_INCREMENT PRIMARY KEY,
                             publication_order_id varchar(7)NOT NULL,
                             publicationName VARCHAR(15) NOT NULL,
                             price_in_€ DOUBLE NOT NULL);

DROP TABLE IF EXISTS DELIVERY_DOCKETS;
CREATE TABLE IF NOT EXISTS DELIVERY_DOCKETS (
	deliveryDocketID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    publicationID INTEGER NOT NULL,
    deliveryAreaID INTEGER NOT NULL,
    customerID INTEGER NOT NULL
);


insert into delivery_areas VALUES (DEFAULT,'Area1',150);

INSERT INTO publication VALUES(DEFAULT,"A000001","Fobes",10.00);

INSERT INTO DELIVERY_DOCKETS VALUES (DEFAULT, 1, 1, 1);


SELECT * from publication;

SELECT * FROM delivery_areas;

SELECT
    *
FROM
    DELIVERY_DOCKETS;

    
# Customers Table
create table ADDRESS (
    address_id int PRIMARY KEY AUTO_INCREMENT,
    houseNumber int not null,
    street VARCHAR(15) not null,
    town varchar(20) not null,
    city varchar(20) not null,
    country varchar(20) not null
);

CREATE TABLE CUSTOMERS (
	customer_id INT AUTO_INCREMENT PRIMARY KEY,
	address_id INT NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    mobileNumber VARCHAR(10) NOT NULL, 
    FOREIGN KEY (address_id) REFERENCES ADDRESS (address_id)
);

INSERT INTO ADDRESS (address_id, houseNumber, street, town, city, country) VALUES
(1, 22, 'Parnell St', 'Monksland', 'Westmeath', 'Ireland'),
(2, 34, 'Church Rd', 'Roscommon', 'Westmeath', 'Ireland'),
(3, 11, 'Kilmacoo', 'Athlone', 'Westmeath', 'Ireland');

SELECT * FROM ADDRESS;

INSERT INTO CUSTOMERS (customer_id, address_id, firstName, lastName, mobileNumber) VALUES
(NULL, 2, 'Kevin', 'Jerome', '0834452321'),
(NULL, 1, 'Tanguy', 'Ndombele', '0875567932'),
(NULL, 3, 'Kieron', 'Pavloski', '0872945875');

SELECT * FROM CUSTOMERS;

CREATE VIEW orders5 AS
select customers.customer_id, customers.firstName, customers.lastName, publication.publicationName, publication.priceInEuro, Delivery_dockets.dateOfDelivery From Delivery_dockets
                                                                                                                                                                      INNER JOIN publication
                                                                                                                                                                                 on publication.id = Delivery_dockets.publicationID
                                                                                                                                                                      INNER JOIN customers
                                                                                                                                                                                 on customers.customer_id = Delivery_dockets.customerID;