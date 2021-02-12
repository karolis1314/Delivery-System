package test;

import controller.DeliveryDocketController;
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

    public void testCreateDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify Delivery Docket created
            Test Type: JUnit
            Input: publicationID = 0, deliveryAreaID = 0, customerID = 0
            Output: true
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            assertEquals(true, deliveryDocketController.createDeliveryDocket(0, 0, 0));
        } catch (Exception exception) {
            fail();
        }
    }

    public void testCreateDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify Delivery Docket creation failed
            Test Type: JUnit
            Input: publicationID = -1, deliveryAreaID = -1, customerID = -1
            Output: false
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            deliveryDocketController.createDeliveryDocket(-1, -1, -1);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Delivery Docket creation failed", deliveryDocketException.getMessage());
        }
    }
}