use newsagent2021;
DROP TRIGGER IF EXISTS orders_after_insert;
DELIMITER //

CREATE  TRIGGER orders_after_insert
    BEFORE INSERT on DELIVERY_DOCKETS
    FOR EACH ROW
BEGIN
    update publication set stock = stock - 1 where stock > 0 and id = new.publicationID;
END//