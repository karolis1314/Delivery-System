package dao;

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

    public void testDeliveryDocketDAO001() {
        /*
            Test Number: 001
            Objective: Insert a Delivery Docket object into database
            Test Type: JUnit
            Input: Delivery Docket object
            Output: true
        */

        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            DeliveryDocket deliveryDocket = new DeliveryDocket(1, 10, 100);
            assertEquals(true, deliveryDocketDAO.insertDeliveryDocket(deliveryDocket));
        } catch (Exception exception) {
            fail();
        }
    }

}