package controller;

import junit.framework.TestCase;

public class DeliveryAreaControllerTest extends TestCase {
    /*
########## USER Story DEA1 #####################
        As a Newsagent
        I want to create a delivery area
        So I can expand the business into other areas

        Acceptance Criteria:
        Verify area ID
        verify area Name
        Verify area size
        Verify area created

        This method create a delivery area object
        with value received by a GUI and
        then save it to a database

        valid partitions

        areaID int 0 to max_int
        areaName String 1 to 255
        areaSize int 0 to max_int

        invalid partitions

        areaID int less then 0
        areaName String 0
        areaSize int less than 0


 */
    public void testDeliveryArea001(){
/*
        test number: 001
        objective: verify all partitions
        Test type: JUint
        input: areaName = area1, areaSize = 230
        output: true


 */
    }


}