package model;

import exceptions.DeliveryDocketException;
import junit.framework.TestCase;

public class DeliveryDocketTest extends TestCase {

    public void testDeliveryDocket001() {
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 1, 1, 1);
            assertEquals(1, deliveryDocket.getDeliveryDocketID());
            assertEquals(1, deliveryDocket.getPublicationID());
            assertEquals(1, deliveryDocket.getDeliveryAreaID());
            assertEquals(1, deliveryDocket.getCustomerID());

        } catch (DeliveryDocketException deliveryDocketException) {
            fail();
        }
    }

    public void testDeliveryDocket002() {
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 0, 1, 1);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid Publication ID", deliveryDocketException.getMessage());
        }
    }

    public void testDeliveryDocket003() {
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 1, 0, 1);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid Delivery Area ID", deliveryDocketException.getMessage());
        }
    }

    public void testDeliveryDocket004() {
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 1, 1, 0);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid Customer ID", deliveryDocketException.getMessage());
        }
    }
}
