package controller;

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
        with values received from the GUI and
        then saves it to the database

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

    public boolean testDeliveryDocket001() {
        /*
            Test Number: 001
            Objective: Verify all partitions
            Test Type: JUnit
            Input: deliveryDocketID = 1, publicationID = 10, deliveryAreaID = 100, customerID = 1000
            Output: true
        */

        return false;
    }

}