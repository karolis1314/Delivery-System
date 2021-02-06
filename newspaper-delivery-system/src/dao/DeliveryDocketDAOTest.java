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

    public void testDeliveryDocketDAO001() throws Exception {
        DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
        DeliveryDocket deliveryDocket = new DeliveryDocket(1, 10, 100);

        try {
            assertEquals(true, deliveryDocketDAO.insertDeliveryDocket(deliveryDocket));
        } catch (Exception exception) {
            fail();
        }
    }

}