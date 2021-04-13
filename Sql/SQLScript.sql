DROP DATABASE IF EXISTS newsagent2021;
CREATE DATABASE IF NOT EXISTS newsagent2021;
use newsagent2021;

CREATE TABLE IF NOT EXISTS delivery_areas (
                                              id int AUTO_INCREMENT PRIMARY KEY,
                                              AreaName VARCHAR(50)
    );

insert into delivery_areas (id ,AreaName) VALUES
(DEFAULT,'Area1'),
(DEFAULT,'Area2'),
(DEFAULT,'Area3'),
(DEFAULT,'Area4'),
(DEFAULT,'Area5');

CREATE TABLE CUSTOMERS (
                           customer_id INT AUTO_INCREMENT PRIMARY KEY,
                           address VARCHAR(20) NOT NULL,
                           firstName VARCHAR(20) NOT NULL,
                           lastName VARCHAR(20) NOT NULL,
                           mobileNumber VARCHAR(10) NOT NULL,
                           areaID int,
                           FOREIGN KEY(areaID)
                               REFERENCES delivery_areas(id)
);

INSERT INTO CUSTOMERS (customer_id, address, firstName, lastName, mobileNumber, areaID) VALUES
(NULL, 'mick street', 'Kevin', 'Jerome', '0834452321',1),
(NULL, 'Mouse road', 'Tanguy', 'Ndombele', '0875567932',1),
(NULL, 'Duck lane', 'Kieron', 'Pavloski', '0872945875',1),
(NULL, 'Lane Road', 'Donald', 'Trumper', '0872945875',2);



CREATE TABLE IF NOT EXISTS STAFF_MEMBER (
                                            staffID INTEGER AUTO_INCREMENT PRIMARY KEY,
                                            firstName varchar(20) not null,
    lastName varchar(20) not null,
    staffPassword VARCHAR(20) not null,
    areaID INTEGER NOT NULL,
    FOREIGN KEY(areaID)
    REFERENCES delivery_areas(id)
    );

insert into STAFF_MEMBER (staffID,firstName,lastName,staffPassword,areaID) VALUES
(DEFAULT,'Karolis','Valatka', 'Karolis', 1),
(DEFAULT,'Sean','Calvey', 'Sean', 2),
(DEFAULT,'Criag','Cunningham', 'Craig', 3),
(DEFAULT,'Lekan','Adams', 'Lekan', 4);

CREATE TABLE IF NOT EXISTS publication (
    id INTEGER(50) AUTO_INCREMENT PRIMARY KEY,
    frequencyInDays varchar(7)NOT NULL,
    publicationName VARCHAR(15) NOT NULL,
    priceInEuro DOUBLE NOT NULL,
    stock INTEGER
    );

INSERT INTO publication (id, frequencyInDays , publicationName, priceInEuro, stock) VALUES
(DEFAULT,'30' ,"Fobes",10.00, 5),
(DEFAULT,'1',"The Sun",2.00, 20),
(DEFAULT,'1',"The Irish Times",2.50, 35),
(DEFAULT,'1',"The Mirror",1.20,15),
(DEFAULT,'14',"Q",5.00,14),
(DEFAULT,'7',"MAD",6.0,12),
(DEFAULT,'14',"HELLO",4.00,45);

CREATE TABLE IF NOT EXISTS DELIVERY_DOCKETS (
                                                deliveryDocketID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                                dateOfDelivery DATETIME DEFAULT CURRENT_TIMESTAMP,
                                                customerID INTEGER NOT NULL,
                                                publicationID INTEGER NOT NULL,
                                                delivered BOOLEAN DEFAULT false,
                                                FOREIGN KEY(publicationID)
    REFERENCES publication(id),
    FOREIGN KEY(customerID)
    REFERENCES CUSTOMERS(customer_id)
    );


CREATE TABLE IF NOT EXISTS orders (
                                      customerID INTEGER,
                                      publicationID INTEGER,
                                      isActive BOOLEAN DEFAULT TRUE,
                                      FOREIGN KEY(publicationID)
    REFERENCES publication(id),
    FOREIGN KEY(customerID)
    REFERENCES CUSTOMERS(customer_id)
    );

INSERT INTO orders (customerID,publicationID, isActive) values
(1,1,DEFAULT),
(1,2,DEFAULT),
(1,5,DEFAULT),
(2,1,DEFAULT),
(2,2,DEFAULT),
(2,5,DEFAULT),
(3,1,DEFAULT),
(3,2,DEFAULT),
(3,5,DEFAULT),
(2,7,DEFAULT),
(1,3,DEFAULT),
(4,6,DEFAULT);


CREATE TABLE IF NOT EXISTS invoice(
                                      dateOfInvoice DATE,
                                      publicationID INTEGER,
                                      customerID INTEGER,
                                      FOREIGN KEY(publicationID)
    REFERENCES publication(id),
    FOREIGN KEY(customerID)
    REFERENCES CUSTOMERS(customer_id)
    );


select customerID, publicationID from orders inner join publication on orders.publicationID=publication.id where isActive=true and frequencyInDays='1';

select * from Delivery_Dockets;

-- CREATE  VIEW DELIVERYDOCETS AS
-- 	select DELIVERY_DOCKETS.deliveryDocketID, publication.publicationName as Publication, delivery_areas.areaName as Area , concat(customers.firstName, " ", customers.lastName) as customer FROM DELIVERY_DOCKETS
-- 		INNER JOIN publication
-- 		on publication.id = DELIVERY_DOCKETS.deliveryDocketID
-- 		INNER JOIN delivery_areas
-- 		on delivery_areas.id = DELIVERY_DOCKETS.deliveryAreaID
-- 		INNER join customers
-- 		on customers.customer_id = DELIVERY_DOCKETS.customerID;

-- CREATE  VIEW STAFFMEMBER AS
-- 	select STAFF_MEMBER.staffID, STAFF_MEMBER.firstName,STAFF_MEMBER.lastName , delivery_areas.areaName as Area FROM STAFF_MEMBER
-- 		INNER JOIN delivery_areas
-- 		on STAFF_MEMBER.areaID = delivery_areas.id;


# deliveryDocketID,dateOfDelivery,staffMemberID,publicationID, deliveryAreaID, customerID, delivered
-- CREATE  VIEW ORDERVIEW AS
-- 	select STAFF_MEMBER.staffID, publication.id, delivery_areas.id, customers.customer_id  FROM ORDERS
-- 		INNER JOIN delivery_areas
#		on STAFF_MEMBER.areaID = delivery_areas.id;

CREATE VIEW orders5 AS
select customers.customer_id, customers.firstName, customers.lastName, publication.publicationName, publication.priceInEuro, Delivery_dockets.dateOfDelivery From Delivery_dockets
                                                                                                                                                                      INNER JOIN publication
                                                                                                                                                                                 on publication.id = Delivery_dockets.publicationID
                                                                                                                                                                      INNER JOIN customers
                                                                                                                                                                                 on customers.customer_id = Delivery_dockets.customerID;



select * from orders5;

select * from orders5 where customer_id = 1;

SELECT * from publication;

SELECT * FROM delivery_areas;

SELECT * FROM DELIVERY_DOCKETS;

SELECT * FROM CUSTOMERS;

SELECT * from orders;