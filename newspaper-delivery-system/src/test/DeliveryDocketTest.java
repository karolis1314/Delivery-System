package test;

import controller.DeliveryDocketController;
import exceptions.DeliveryDocketException;
import junit.framework.TestCase;
import model.DeliveryDocket;

public class DeliveryDocketTest extends TestCase {
    public void testDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify Delivery Docket object created successfully
            Test Type: JUnit
            Input: publicationID = 0, deliveryAreaID = 0, customerID = 0
            Output: Delivery Docket object with publicationID = 0, deliveryAreaID = 0, and customerID = 0
        */

        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(0, 0, 0);

            assertEquals(1, deliveryDocket.getPublicationID());
            assertEquals(10, deliveryDocket.getDeliveryAreaID());
            assertEquals(100, deliveryDocket.getCustomerID());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify Publication invalid partition
            Test Type: JUnit
            Input: publicationID = -1, deliveryAreaID = 0, customerID = 0
            Output: false
        */

        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(-1, 0, 0);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid Publication ID", exception.getMessage());
        }
    }

    public void testDeliveryDocket003() {
        /*
            Test Number: 003
            Objective: Verify Delivery Area ID invalid partition
            Test Type: JUnit
            Input: publicationID = 0, deliveryAreaID = -1, customerID = 0
            Output: false
        */

        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(0, -1, 0);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid Delivery Area ID", exception.getMessage());
        }
    }

    public void testDeliveryDocket004() {
        /*
            Test Number: 004
            Objective: Verify invalid customer ID partition
            Test Type: JUnit
            Input: publicationID = 0, deliveryAreaID = 0, customerID = -1
            Output: false
        */

        DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

        try {
            deliveryDocketController.createDeliveryDocket(0, 0, -1);
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid Customer ID", deliveryDocketException.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }
}
