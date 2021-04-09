package model;

import exceptions.DeliveryAreaException;
import junit.framework.TestCase;

import model.DeliveryArea;


public class DeliveryAreaTest extends TestCase {

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
        areaName String 1 to 49
        areaSize int 0 to 200

        invalid partitions

        areaID int less then 0
        areaName String 0 and greater than 50
        areaSize int less than 0 and greater than 200
     */
    public void testDeliveryArea001(){
          /*
        test number: 001
        objective: create a Delivery Area object
        Test type: JUint
        input: name = Area 1, size = 56
        output: true
 */
        try {
            DeliveryArea deliveryArea = new DeliveryArea("Area 1");
            assertEquals("Area 1", deliveryArea.getName());
        } catch (DeliveryAreaException e) {
            fail();
        }
    }
    public void testDeliveryArea002(){
          /*
        test number: 002
        objective: create a Delivery Area object with invalid name
        Test type: JUint
        input: name = , size = 56
        output: error
 */
        try {
            DeliveryArea deliveryArea = new DeliveryArea("Area 1");
        } catch (DeliveryAreaException e) {
            assertEquals("Invalid Delivery Area name", e.getErrorMessage());
        }
    }
    public void testDeliveryArea003(){
          /*
        test number: 003
        objective: create a Delivery Area object with invalid name
        Test type: JUint
        input: name = asdfhjklasdfhjlasdfhjjjuehdhhdiekdpppesdkakebbhckajschbasjdhbcadshbcjcadsbcjkasbdchjsdabckjhasbdkcbry , size = 56
        output: error
 */
        try {
            DeliveryArea deliveryArea = new DeliveryArea("asdfhjklasdfhjlasdfhjjjuehdhhdiekdpppesdkakebbhckajschbasjdhbcadshbcjcadsbcjkasbdchjsdabckjhasbdkcbryjsdkfjadskfadskjsdkjfljkfsajfsjaskdbfjsadbfrunfuasnfrusfnsjdnafljsdfnasdnfasdnflasdn");
        } catch (DeliveryAreaException e) {
            assertEquals("Invalid Delivery Area name", e.getErrorMessage());
        }
    }


}