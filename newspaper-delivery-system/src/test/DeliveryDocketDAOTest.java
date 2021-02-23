package test;

import controller.DeliveryDocketDAO;
import exceptions.DeliveryDocketException;
import junit.framework.TestCase;
import model.DeliveryDocket;

public class DeliveryDocketDAOTest extends TestCase {

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
        This method inserts Delivery Docket object values into the database
    */

    public void testConnectToDatabase001() {
        /*
            Test Number: 001
            Objective: Connection to the Database has passed
            Test Type: JUnit and UAT
            Input: Database is on
            Output: true
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            assertEquals(true, deliveryDocketDAO.connectToDatabase());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testConnectToDatabase002() {
        /*
            Test Number: 002
            Objective: Connection to the Database has failed
            Test Type: JUnit and UAT
            Input: Database is off
            Output: false
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            deliveryDocketDAO.connectToDatabase();
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Database connection failed", deliveryDocketException.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testInsertDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Insert Delivery Docket is successful
            Test Type: JUnit and UAT
            Input: deliveryDocket with publicationID = 1, deliveryAreaID = 1, customerID = 1
            Output: true
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 1, 1);

            deliveryDocketDAO.insertDeliveryDocket(deliveryDocket);
        } catch (Exception exception) {
            fail();
        }
    }

    public void testInsertDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Insert Delivery Docket is unsuccessful
            Test Type: JUnit and UAT
            Input: deliveryDocket with publicationID = 0, deliveryAreaID = 0, customerID = 0
            Output: false
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            DeliveryDocket deliveryDocket = new DeliveryDocket(0, 0, 0);

            deliveryDocketDAO.insertDeliveryDocket(deliveryDocket);
            fail();
        } catch (DeliveryDocketException deliveryDocketException) {
            assertEquals("Invalid Delivery Docket object", deliveryDocketException.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    /*
        ##### User Story DED2 #####

        # User Story:
        As Newsagent
        I want to add print delivery docket
        So I can make new delivery

        # Acceptance Criteria:
        verify ID is added
        verify publication is added
        verify delivery area id added
        verify customer id is added

        # Method Description:
        This method gets Delivery Docket object values into the database
    */

    public void testGetDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Get Delivery Docket is successful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 1
            Output: deliveryDocket with publicationID = 1, deliveryAreaID = 1, customerID = 1
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            DeliveryDocket deliveryDocket = deliveryDocketDAO.getDeliveryDocketByID(1);

            assertEquals(1, deliveryDocket.getPublicationID());
            assertEquals(1, deliveryDocket.getDeliveryAreaID());
            assertEquals(1, deliveryDocket.getCustomerID());
        } catch (Exception exception) {
            fail();
        }
    }

    public void testGetDeliveryDocket002() {
        /*
            Test Number: 002
            Objective: Insert Delivery Docket is unsuccessful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 0
            Output: null
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            assertEquals(null, deliveryDocketDAO.getDeliveryDocketByID(0));
        } catch (Exception exception) {
            fail();
        }
    }

    /*
        ##### User Story DED3 #####

        # User Story:
        As Newsagent
        I want to edit delivery docket
        So I can change delivery quested by customers

        # Acceptance Criteria:
        verify publication changed
        verify delivery area changed

        # Method Description:
        This method updates Delivery Docket object values into the database
    */

    public void testUpdateDeliveryDocketByID001() {
        /*
            Test Number: 001
            Objective: Update Delivery Docket is successful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 1, publicationID = 2, deliveryAreaID = 2, customerID = 2
            Output: Update to Delivery Docket values for deliveryDocketID = 1, with publicationID = 2, deliveryAreaID = 2, customerID = 2
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.updateDeliveryDocketByID(1, 2, 2, 2);
        } catch (Exception exception) {
            fail();
        }
    }

    public void testUpdateDeliveryDocketByID002() {
        /*
            Test Number: 002
            Objective: Update Delivery Docket is unsuccessful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 0, publicationID = 0, deliveryAreaID = 0, customerID = 0
            Output: "Invalid Delivery Docket parameters"
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.updateDeliveryDocketByID(0, 0, 0, 0);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid Delivery Docket parameters", exception.getMessage());
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
        This method deletes Delivery Docket object values into the database
    */

    public void testDeleteDeliveryDocketByID001() {
        /*
            Test Number: 001
            Objective: Delete Delivery Docket is successful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 1
            Output: Delivery Docket with deliveryDocketID = 1 is deleted
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.deleteDeliveryDocketByID(1);
        } catch (Exception exception) {
            fail();
        }
    }

    public void testDeleteDeliveryDocketByID002() {
        /*
            Test Number: 002
            Objective: Delete Delivery Docket is unsuccessful
            Test Type: JUnit and UAT
            Input: deliveryDocketID = 0
            Output: "Invalid Delivery Docket ID"
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.deleteDeliveryDocketByID(0);
            fail();
        } catch (Exception exception) {
            assertEquals("Invalid Delivery Docket ID", exception.getMessage());
        }
    }
}