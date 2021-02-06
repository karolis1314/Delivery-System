package controller;

import exceptions.DeliveryDocketException;
import junit.framework.TestCase;

public class DeliveryDocketControllerTest extends TestCase {

    /*
        ##### User Story DED1 #####

        # User Story:
        As Newsagent
        I want to add new delivery docket
        So I can make new delivery

        # Acceptance Criteria:
        verify ID is added
        verify publication is added
        verify delivery area id added
        verify customer id is added

        # Method Description:
        This method creates a delivery docket object
        with values received from the GUI

        # Valid Partitions:
        Delivery Docket ID int 0 to max int
        Publication ID int 0 to max int
        Delivery Area ID int 0 to max int
        Customer ID int 0 to max int

        # Invalid Partitions:
        Delivery Docket ID -max int to -1
        Publication ID -max int to -1
        Delivery Area ID int -max int to -1
        Customer ID int -max int to -1
    */

    public void testDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify all partitions
            Test Type: JUnit
            Input: publicationID = 1, deliveryAreaID = 10, customerID = 100
            Output: true
        */

        DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

        try {
            assertEquals(true, deliveryDocketController.createDeliveryDocket(1, 10, 100));
        } catch (Exception exception) {
            fail();
        }
    }

    public void testDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify all invalid partitions
            Test Type: JUnit
            Input: publicationID = -1, deliveryAreaID = -10, customerID = -100
            Output: false
        */

        DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

        try {
            deliveryDocketController.createDeliveryDocket(-1, -10, -100);
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid publication", deliveryDocketException.getErrorMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testDeliveryDocket003() {
        /*
            Test Number: 003
            Objective: Verify invalid delivery area ID partition
            Test Type: JUnit
            Input: publicationID = 7, deliveryAreaID = -10, customerID = -100
            Output: false
        */

        DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

        try {
            deliveryDocketController.createDeliveryDocket(7, -10, -100);
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid delivery area", deliveryDocketException.getErrorMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testDeliveryDocket004() {
        /*
            Test Number: 004
            Objective: Verify invalid customer ID partition
            Test Type: JUnit
            Input: publicationID = 7, deliveryAreaID = 11, customerID = -100
            Output: false
        */

        DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

        try {
            deliveryDocketController.createDeliveryDocket(7, 11, -100);
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid customer", deliveryDocketException.getErrorMessage());
        } catch (Exception exception) {
            fail();
        }
    }

}