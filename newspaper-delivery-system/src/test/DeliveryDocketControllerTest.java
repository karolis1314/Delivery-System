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
        Delivery Docket ID int 1 to max int
        Publication ID int 1 to max int
        Delivery Area ID int 1 to max int
        Customer ID int 1 to max int

        # Invalid Partitions:
        Delivery Docket ID -max int to 0
        Publication ID -max int to 0
        Delivery Area ID int -max int to 0
        Customer ID int -max int to 0
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

            assertEquals(true, deliveryDocketController.createDeliveryDocket(1, 1, 1));
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
        } catch (Exception exception) {
            fail();
        }
    }

    /*
        ##### User Story DED2 #####

        # User Story:
        As Newsagent
        I want to print delivery docket
        So I can handout delivery docket to staff

        # Acceptance Criteria:
        verify I can view publication
        verify I can view delivery area

        # Method Description:
        This method reads a delivery docket object
        with delivery docket ID

        # Valid Partitions:
        Delivery Docket ID int 1 to max int
        Publication ID int 1 to max int
        Delivery Area ID int 1 to max int
        Customer ID int 1 to max int

        # Invalid Partitions:
        Delivery Docket ID -max int to 0
        Publication ID -max int to 0
        Delivery Area ID int -max int to 0
        Customer ID int -max int to 0
     */

    public void testReadDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify Delivery Docket read
            Test Type: JUnit
            Input: deliveryDocketID = 1
            Output: true
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            assertEquals(true, deliveryDocketController.readDeliveryDocket(1));
        } catch (Exception exception) {
            fail();
        }
    }

    public void testReadDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify Delivery Docket read failed
            Test Type: JUnit
            Input: deliveryDocketID = 0
            Output: "Delivery Docket read failed"
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            deliveryDocketController.readDeliveryDocket(0);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Delivery Docket read failed", deliveryDocketException.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    /*
        ##### User Story DED3 #####

        # User Story:
        As Newsagent
        I want to edit delivery docket
        So I can change delivery requested by customers

        # Acceptance Criteria:
        verify publication changed
        verify delivery changed

        # Method Description:
        This method edits a delivery docket object
        with delivery docket ID

        # Valid Partitions:
        Delivery Docket ID int 1 to max int
        Publication ID int 1 to max int
        Delivery Area ID int 1 to max int
        Customer ID int 1 to max int

        # Invalid Partitions:
        Delivery Docket ID -max int to 0
        Publication ID -max int to 0
        Delivery Area ID int -max int to 0
        Customer ID int -max int to 0
     */

    public void testEditDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify Delivery Docket updated
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 1, publicationID = 2, deliveryAreaID = 2, customerID = 2
            Output: true
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            assertEquals(true, deliveryDocketController.editDeliveryDocket(1, 2, 2, 2));
        } catch (Exception exception) {
            fail();
        }
    }

    public void testEditDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify Delivery Docket update failed
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 0, publicationID = 0, deliveryAreaID = 2, customerID = 2
            Output: "Delivery Docket update failed"
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            deliveryDocketController.editDeliveryDocket(0, 1, 1, 1);
            fail();
        } catch (Exception exception) {
            assertEquals("Delivery Docket edit failed", exception.getMessage());
        }
    }

    /*
        ##### User Story DED4 #####

        # User Story:
        As Newsagent
        I want to delete delivery docket
        So I can remove old dockets of the system

        # Acceptance Criteria:
        verify publication removed
        verify delivery removed

        # Method Description:
        This method deletes a delivery docket object
        with delivery docket ID

        # Valid Partitions:
        Delivery Docket ID int 1 to max int
        Publication ID int 1 to max int
        Delivery Area ID int 1 to max int
        Customer ID int 1 to max int

        # Invalid Partitions:
        Delivery Docket ID -max int to 0
        Publication ID -max int to 0
        Delivery Area ID int -max int to 0
        Customer ID int -max int to 0
     */

    public void testRemoveDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify Delivery Docket removed
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 1
            Output: true
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            assertEquals(true, deliveryDocketController.removeDeliveryDocket(1));
        } catch (Exception exception) {
            fail();
        }
    }

    public void testRemoveDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Verify Delivery Docket removal failed
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 0
            Output: "Delivery Docket removal failed"
        */

        try {
            DeliveryDocketController deliveryDocketController = new DeliveryDocketController();

            deliveryDocketController.removeDeliveryDocket(0);
            fail();
        } catch (Exception exception) {
            assertEquals("Delivery Docket removal failed", exception.getMessage());
        }
    }
}