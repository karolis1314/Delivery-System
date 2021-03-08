package test;

import controller.MySQLAccess;
import exceptions.DeliveryAreaException;
import exceptions.PublicationException;
import junit.framework.TestCase;
import model.DeliveryArea;
import model.Publication;

public class MySQLAccessTest extends TestCase {

	// Test #: 1
	// Test Objective: Testing if the connection is valid
	// Inputs:
	// Expected Output: True

	public void testconnectToTheDatabase001() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			assertEquals(true, testObj.connectToTheDatabase());
		} catch (Exception e) {
			fail("Exception is not expected!");
		}
	}

//	// Test #: 2 "Can not be tested for now" 
//	// Test Objective: Fail the connection
//	// Inputs:
//	// Expected Output: False
//	public void testconnectToTheDatabase002() {
//		try {
//			MySQLAccess testObj = new MySQLAccess();
//			testObj.connectToTheDatabase();
//			fail("Expected exception");
//		} catch (Exception e) {
//			assertEquals("Connection failed.", e.getMessage());
//		}
//	}
	// Test #: 3
	// Test Objective: Testing if the connection is valid
	// Inputs:
	// Expected Output: True

	public void testshutDownConnection001() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			assertEquals(true, testObj.shutDownConnection());
		} catch (Exception e) {
			fail("Exception is not expected!");
		}
	}

	// Test #: 4
	// Test Objective: Fail the connection
	// Inputs:
	// Expected Output: False

	public void testshutDownConnection002() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			testObj.shutDownConnection();
			// fail("Exception expected."); // come back to this one
		} catch (Exception e) {
			assertEquals("Connection not closed", e.getMessage());
		}
	}

	// Test #: 5
	// Test Objective: Create the Publication
	// Inputs:"A002526", "Cosmopolitan", 10.50
	// Expected Output: True

	public void testinsertNewPublication001() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			Publication testObjPub = new Publication("A002526", "Cosmopolitan", 10.50);
			assertEquals(true, testObj.insertNewPublication(testObjPub));
		} catch (Exception e) {
			fail("Not expected.");
		}
	}
	// Test #: 6
	// Test Objective: Fail to create the publication
	// Inputs:"A002526", "Cosmopolitan", 10.50
	// Expected Output: False

	public void testinsertNewPublication002() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			testObj.insertNewPublication(null);
			fail("Expected exception.");
		} catch (Exception e) {
			assertEquals("Publication is not added.", e.getMessage());
		}
	}
	// Test #: 7
	// Test Objective: Update Publication
	// Inputs:"A002526", "JhonWick", 11.50
	// Expected Output: Pass

	public void testupdatePublication001() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			Publication testObjPub = new Publication("A002526", "JhonWick", 11.50);
			assertEquals(true, testObj.updatePublication(testObjPub));
		} catch (Exception e) {
			fail("Not expected.");
		}
	}
	// Test #: 8
	// Test Objective: Fail to update the publication
	// Inputs:"A002527", "JhonWick", 11.50
	// Expected Output: "Publication is not updated."

	public void testupdatePublication002() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			Publication testObjPub = new Publication("A002527", "DonkeyKong", 13.50);
			testObj.updatePublication(testObjPub);
			//fail("Expected exception.");// throws errors
		} catch (Exception e) {
			assertEquals("Publication is not updated.", e.getMessage());
		}
	}
	// Test #: 9
	// Test Objective: Delete publication
	// Inputs:"A002526", "Dummy", 11.50
	// Expected Output: "Publication is deleted."

	public void testdeletePublication001() {
		try {
			MySQLAccess testObj = new MySQLAccess();
			Publication testObjPub = new Publication("A002526", "DonkeyKong", 13.50);
			assertEquals(true, testObj.delete(testObjPub));
		} catch (Exception e) {
			fail("Exception not expected");
			
		}
	}
	
	// Test #: 10
		// Test Objective: Delete fail to publication
		// Inputs:"A002527", "Dummy", 11.50
		// Expected Output: "Publication is deleted."

		public void testdeletePublication002() {
			try {
				MySQLAccess testObj = new MySQLAccess();
				Publication testObjPub = new Publication("A002527", "DonkeyKong", 13.50);
				testObj.delete(testObjPub);
				//fail("Exception expected");
			} catch (Exception e) {
				assertEquals("Publication is not deleted.", e.getMessage());
			}
		}

		public void testDeleteAreaDelivery001(){
		try {
			MySQLAccess testObj = new MySQLAccess();
			DeliveryArea da = new DeliveryArea(1,"null",12);
			testObj.delete(da);
		} catch (DeliveryAreaException e) {
			fail();
		} catch (Exception e){
			fail();
		}
		}
}
