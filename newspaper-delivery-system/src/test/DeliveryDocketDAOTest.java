package test;

import controller.DeliveryDocketDAO;
import exceptions.DeliveryDocketException;
import junit.framework.TestCase;

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

    public void testDeliveryDocketDAO001() {
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

    public void testDeliveryDocketDAO002() {
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

}