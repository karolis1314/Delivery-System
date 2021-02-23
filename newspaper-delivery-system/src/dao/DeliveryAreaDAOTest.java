import controller.MySQLAccess;
import exceptions.DeliveryAreaException;
import exceptions.SQLConnectionException;
import junit.framework.TestCase;
import model.DeliveryArea;

import java.util.ArrayList;

//package dao;
//
//import exceptions.DeliveryAreaException;
//import junit.framework.TestCase;
//import model.DeliveryArea;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
public class DeliveryAreaDAOTest extends TestCase {
    //    private Object ResultSet;
//      /*
//########## USER Story DEA1 #####################
//        As a Newsagent
//        I want to create a delivery area
//        So I can expand the business into other areas
//
//        Acceptance Criteria:
//        Verify area ID
//        verify area Name
//        Verify area size
//        Verify area created
//
//        This method create a delivery area object
//        with value received by a GUI and
//        then save it to a database
//
//        valid partitions
//
//        areaID int 0 to max_int
//        areaName String 1 to 50
//        areaSize int 0 to max_int
//
//        invalid partitions
//
//        areaID int less then 0
//        areaName String 0
//        areaSize int less than 0
//
//
// */
//
//    public void testInsertDeliveryAreaDAO001() {
//
//        /*
//        test number: 001
//        objective: insert a Delivery Area object into DB
//        Test type: JUint
//        input: Delivery Area Object
//        output: true
// */
//
//        try {
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea("area2", 2345);
//            assertEquals(true, areaDAO.insertDeliveryArea(da));
//        } catch (DeliveryAreaException e) {
//            fail();
//        }
//    }
//
//    public void testInsertDeliveryAreaDAO002() {
//        /*
//        test number: 002
//        objective: insert a Delivery Area object into DB that off
//        Test type: JUint
//        input: Delivery Area Object
//        output: error Message "Delivery Area DB connection Failed"
// */
//
//        try {
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea("area2", 2345);
//            areaDAO.insertDeliveryArea(da);
//        } catch (DeliveryAreaException e) {
//            assertEquals("Delivery Area DB connection Failed", e.getErrorMessage());
//        }
//    }
//
//     /*
//        test number: 003
//        objective: insert a invalid Delivery Area object into DB
//        Test type: JUint
//        input: Delivery Area Object
//        output: error Message "Delivery Area DB connection Failed"
// */
//
//    public void testInsertDeliveryAreaDAO03() {
//
//        try {
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea("kkjdsfnasdj;hfasjdhf;jasdhf;jashdf;jhasdfjhuahfusrhfudfuasfuashfuasdfsjahfusneuascuashuashrcucasruasunciuasbrefuiasbpiucasriufhasidyfuasydgfuyasbcyuasecuiyacseiufgasuycbuysagcuosaybduiycgdsuyciudfguyaf", -516846);
//            areaDAO.insertDeliveryArea(da);
//        } catch (DeliveryAreaException e) {
//            assertEquals("Delivery Area DAO Insert failed", e.getErrorMessage());
//        }
//    }
//
    public void testReadAllDeliveryArea004() {
         /*
        test number: 004
        objective: read all Delivery Area object from DB
        Test type: JUint
        input:
        output: Arraylist size of 8
 */
        try {
            MySQLAccess areaDAO = new MySQLAccess();
            ArrayList<DeliveryArea> ary = areaDAO.readAllDeliveryArea();
            assertEquals(8, ary.size());
        } catch (DeliveryAreaException e) {
            fail();
        } catch (SQLConnectionException e) {
            e.printStackTrace();
        }
    }
}
//    public void testReadAllDeliveryArea005(){
//         /*
//        test number: 005
//        objective: read 1 Delivery Area object from DB
//        Test type: JUint
//        input: 4
//        output: delivery area object that not null
// */
//        try{
//
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = areaDAO.readDeliveryAreaById(2);
//            assertEquals(2, da.getAreaId());
//            assertEquals("area1", da.getName());
//            assertEquals(230, da.getSize());
//        } catch (DeliveryAreaException e) {
//            fail();
//        }
//    }
//
//    public void testReadAllDeliveryArea006(){
///*
//        test number: 006
//        objective: read 1 Delivery Area object from DB
//        Test type: JUint
//        input: 231
//        output: delivery area object is null
// */
//        try{
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            assertEquals(null, areaDAO.readDeliveryAreaById(231));
//        } catch (DeliveryAreaException e) {
//            fail();
//        }
//    }
//
//    public void testUpdateDeliveryArea007(){
//         /*
//        test number: 007
//        objective: update 1 Delivery Area object from DB
//        Test type: JUint
//        input: a delivery area object
//        output:true
// */
//        try{
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea(3, "area 5" ,21341);
//            assertEquals(true, areaDAO.updateDeliveryArea(da));
//        } catch (DeliveryAreaException e) {
//            fail();
//        }
//    }
//
//    public void testUpdateDeliveryArea008(){
//         /*
//        test number: 008
//        objective: update 1 Delivery Area object from DB
//        Test type: JUint
//        input: a delivery area object with wrong id
//        output: error message
// */
//        try{
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea(50, "area 5" ,21341);
//            areaDAO.updateDeliveryArea(da);
//        } catch (DeliveryAreaException e) {
//            assertEquals("delivery Area update failed", e.getErrorMessage());
//        }
//    }
//
//    public void testDeleteDeliveryArea009(){
//         /*
//        test number: 009
//        objective: delete 1 Delivery Area object from DB
//        Test type: JUint
//        input: a delivery area object
//        output: true
// */
//        try{
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea(4, "area 5" ,21341);
//            assertEquals(true ,areaDAO.deleteDeliveryArea(da));
//        } catch (DeliveryAreaException e) {
//            fail();
//        }
//
//    }
//
//
//    public void testDeleteDeliveryArea010() {
//         /*
//        test number: 010
//        objective: delete 1 Delivery Area object from DB than not there
//        Test type: JUint
//        input: a delivery area object with wrong id
//        output: error message
// */
//        try {
//            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
//            DeliveryArea da = new DeliveryArea(39, "area 5", 21341);
//            areaDAO.deleteDeliveryArea(da);
//        } catch (DeliveryAreaException e) {
//            assertEquals("delivery Area delete failed", e.getErrorMessage());
//        }
//    }
//
//}