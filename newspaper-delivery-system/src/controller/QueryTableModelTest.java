package controller;

import exceptions.*;
import junit.framework.TestCase;
import model.*;

import java.sql.ResultSet;

public class QueryTableModelTest extends TestCase {


//	customer obj line 524

	/*
	xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	x                                                                    x
	x        Test if the database is OFF                                 x
	x                                                                    x
	xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	 */


	// Test #: 2
	// Test Objective: Testing if the connection is not valid
	// Inputs:
	// Expected Output: false

	public void testOpenConnection002() {
		try {
			QueryTableModel testObj = new QueryTableModel("error");
			assertEquals(false, testObj.openConnection());
		} catch (Exception e) {
			fail("Exception is not expected!");
		}
	}

		// Test #: 4
	// Test Objective: Fail the connection
	// Inputs:
	// Expected Output: False

	public void testCloseConnection002() {
		try {
			QueryTableModel testObj = new QueryTableModel("error");
			assertEquals(false, testObj.closeConnection());
		} catch (Exception e) {
			fail();
		}
	}



	// Test #: 1
	// Test Objective: Testing if the connection is valid
	// Inputs:
	// Expected Output: True

	public void testOpenConnection001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(true, testObj.openConnection());
		} catch (Exception e) {
			fail("Exception is not expected!");
		}
	}

	// Test #: 3
	// Test Objective: Testing if the connection is valid
	// Inputs:
	// Expected Output: True

	public void testCloseConnection001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();// First open then close the connection.
			assertEquals(true, testObj.closeConnection());
		} catch (Exception e) {
			fail("Exception is not expected!");
		}
	}



	// Test #: 5
	// Test Objective: Create the Publication
	// Inputs:9, "14", "Test Object", 12.50, 2
	// Expected Output: True

	public void testInsertNewPublication001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(9, "14", "Test Object", 12.50, 2);
			testObj.openConnection();
			assertEquals(true, testObj.insertNewPublication(testObjPub));
			testObj.closeConnection();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	// Test #: 6
	// Test Objective: Fail to create publication
	// Inputs:9, "14", "Test Object", 12.50, 2
	// Expected Output: False

	public void testInsertNewPublication002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(9, "14", "Test Object", 12.50, 2);
			assertEquals(false, testObj.insertNewPublication(testObjPub));
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 7
	// Test Objective: Update Publication
	// Inputs:9, "14", "Test Object", 12.50, 2
	// Expected Output: True

	public void testUpdatePublication001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(9, "14", "Test oneone", 12.50, 2);
			testObj.openConnection();
			assertEquals(true, testObj.updatePublication(testObjPub));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected.");
		}
	}
	// Test #: 8
	// Test Objective: Fail to Update Publication
	// Inputs:99, "14", "Test Object", 12.50, 2
	// Expected Output: "Publication is not updated."

	public void testUpdatePublication002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(99, "14", "Test oneone", 12.50, 2);
			assertEquals(false, testObj.updatePublication(testObjPub));
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	// Test #: 9
	// Test Objective: Delete publication
	// Inputs:9, "14", "Test oneone", 12.50, 2
	// Expected Output: "Publication is deleted."

	public void testDeletePublication001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(9, "14", "Test oneone", 12.50, 2);
			testObj.openConnection();
			assertEquals(true, testObj.deletePublication(testObjPub));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}
	// Test #: 10
	// Test Objective: Delete publication
	// Inputs:99, "14", "Test oneone", 12.50, 2
	// Expected Output: "Publication is not deleted."

	public void testDeletePublication002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication(99, "14", "Test oneone", 12.50, 2);
			assertEquals(false, testObj.deletePublication(testObjPub));
		} catch (Exception e) {
			fail("Exception not expected");
		}
	}

	// Test #: 11
	// Test Objective: Create Staff Member
	// Inputs:6, "Karoliss", "Valatkaa", "password", 3
	// Expected Output: True

	public void testInsertNewStaff001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(6, "Karoliss", "Valatkaa", "password", 3);
			assertEquals(true, testObj.insertNewStaff(testObjStaff));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 11
	// Test Objective:Fail to Create Staff Member
	// Inputs:6, "Karoliss", "Valatkaa", "password", 3
	// Expected Output: True

	public void testInsertNewStaff002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			StaffMember testObjStaff = new StaffMember(6, "Karoliss", "Valatkaa", "password", 3);
			assertEquals(false, testObj.insertNewStaff(testObjStaff));
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 12
	// Test Objective:Update staff Member
	// Inputs:6, "Karoliss", "Update", "password", 3
	// Expected Output: True

	public void testUpdateStaff001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(6, "Karoliss", "Update", "password", 3);
			assertEquals(true, testObj.updateStaff(testObjStaff));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 13
	// Test Objective: Fail to Update staff Member
	// Inputs:89, "Karoliss", "Update", "password", 3
	// Expected Output: "Staff is not updated."

	public void testUpdateStaff002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			StaffMember testObjStaff = new StaffMember(89, "Karoliss", "Update", "password", 3);
			assertEquals(false, testObj.updateStaff(testObjStaff));
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 13
	// Test Objective:Delete Staff
	// Inputs:6, "Karoliss", "Update", "password", 3
	// Expected Output: True

	public void testDeleteStaff001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(6, "Karoliss", "Update", "password", 3);
			assertEquals(true, testObj.deleteStaff(testObjStaff));;
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}
	// Test #: 14
	// Test Objective:Fail to Delete Staff
	// Inputs:86, "Karoliss", "Update", "password", 3
	// Expected Output: false

	public void testDeleteStaff002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			StaffMember testObjStaff = new StaffMember(86, "Karoliss", "Update", "password", 3);
			assertEquals(false, testObj.deleteStaff(testObjStaff));;
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 15
	// Test Objective:get all Publications
	// Inputs: none
	// Expected Output: true

	public void testRetrieveAllPublications001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.retrieveAllPublications();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 16
	// Test Objective:get all Publications
	// Inputs: none
	// Expected Output: false

	public void testRetrieveAllPublications002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.retrieveAllPublications();
		} catch (PublicationException e) {
			assertEquals("Publication is not retrieved.", e.getMessage());
		}
	}

// Test #: 17
	// Test Objective:get all Publications
	// Inputs: new DeliveryArea("test");
	// Expected Output: true

	public void testInsertNewDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea da = new DeliveryArea("test");
			testObj.openConnection();
			assertEquals(true, testObj.insertNewDeliveryArea(da));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 18
	// Test Objective:get all Publications
	// Inputs: none
	// Expected Output: false

	public void testInsertNewDeliveryArea002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea da = new DeliveryArea("test");
			assertEquals(false, testObj.insertNewDeliveryArea(da));
		} catch (Exception e) {
			fail();
		}
	}


	// Test #: 19
	// Test Objective:get all Staff
	// Inputs: none
	// Expected Output: true

	public void testRetrieveAllStaff001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.retrieveAllStaff();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 20
	// Test Objective:get all Staff
	// Inputs: none
	// Expected Output: false

	public void testRetrieveAllStaff002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.retrieveAllStaff();
		} catch (StaffException e) {
			assertEquals("Staff is not retrieved.", e.getMessage());
		}
	}

	// Test #: 19
	// Test Objective:get all Delivery Area
	// Inputs: none
	// Expected Output: true

	public void testRetrieveAllDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.retrieveAllDeliveryArea();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 20
	// Test Objective:get all Delivery Area
	// Inputs: none
	// Expected Output: false

	public void testRetrieveAllDeliveryArea002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.retrieveAllDeliveryArea();
		} catch (DeliveryAreaException e) {
			assertEquals("DeliveryAreas is not retrieved.", e.getErrorMessage());
		}
	}

	// Test #: 21
	// Test Objective:get all Delivery Dockets
	// Inputs: none
	// Expected Output: true

	public void testRetrieveAllDeliveryDockets001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.retrieveAllDeliveryDockets();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 22
	// Test Objective:get all Delivery Dockets
	// Inputs: none
	// Expected Output: false

	public void testRetrieveAllDeliveryDockets002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.retrieveAllDeliveryDockets();
		} catch (DeliveryDocketException e) {
			assertEquals("Delivery Dockets not retrieved", e.getMessage());
		}
	}

	// Test #: 23
	// Test Objective:get all Orders
	// Inputs: none
	// Expected Output: true

	public void testDisplayOrders001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.displayOrders();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 24
	// Test Objective:get all Orders
	// Inputs: none
	// Expected Output: false

	public void testDisplayOrders002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.displayOrders();
		} catch (OrdersException e) {
			assertEquals("Failed to display orders: ", e.getMessage());
		}
	}

	// Test #: 25
	// Test Objective:get all Customers
	// Inputs: none
	// Expected Output: true

	public void testDisplayCustomers001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			ResultSet rs = testObj.displayCustomers();
			assertEquals(true, rs.next());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 26
	// Test Objective:get all Customers
	// Inputs: none
	// Expected Output: false

	public void testDisplayCustomers002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.displayCustomers();
		} catch (CustomersException e) {
			assertEquals("Failed to display Customers", e.getMessage());
		}
	}

	// Test #: 27
	// Test Objective:Update Delivery Area
	// Inputs:new DeliveryArea(1,"test");
	// Expected Output: True

	public void testUpdateDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			DeliveryArea test = new DeliveryArea(1,"test");
			assertEquals(true, testObj.updateDeliveryArea(test));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 28
	// Test Objective: Fail to Update Delivery Area
	// Inputs: new DeliveryArea(1,"test");
	// Expected Output: false

	public void testUpdateDeliveryArea002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea test = new DeliveryArea(1,"test");
			assertEquals(false, testObj.updateDeliveryArea(test));
		} catch (Exception e) {
			fail("Not Expected");
		}
	}

	// Test #: 29
	// Test Objective:Update Delivery Area
	// Inputs:new DeliveryArea(1,"test");
	// Expected Output: True

//	public void testUpdateCustomerDetails001() {
//		try {
//			QueryTableModel testObj = new QueryTableModel();
//			testObj.openConnection();
//			Customers test = new Customers();
//			assertEquals(true, testObj.updateCustomerDetails(test));
//			testObj.closeConnection();
//		} catch (Exception e) {
//			fail("Not Expected");
//		}
//	}
	// Test #: 30
	// Test Objective: Fail to Update Delivery Area
	// Inputs: new DeliveryArea(1,"test");
	// Expected Output: false

//	public void testUpdateCustomerDetails002() {
//		try {
//			QueryTableModel testObj = new QueryTableModel();
//			Customers test = new Customers(1,"test");
//			assertEquals(false, testObj.updateCustomerDetails(test));
//		} catch (Exception e) {
//			fail("Not Expected");
//		}
//	}

	// Test #: 31
	// Test Objective:Update Order
	// Inputs:new Orders(1,1,true);
	// Expected Output: True

	public void testInsertOrder001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			Orders test = new Orders(1,1,true);
			assertEquals(true, testObj.insertOrder(test));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not Expected");
		}
	}
	// Test #: 32
	// Test Objective: Fail to Update Order
	// Inputs: new Orders(1,1,true);
	// Expected Output: false

	public void testInsertOrder002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			Orders test = new Orders(1,1,true);
			assertEquals(false, testObj.insertOrder(test));
		} catch (Exception e) {
			fail("Not Expected");
		}
	}

	// Test #: 33
	// Test Objective:Create Daily Delivery Docket
	// Inputs: none
	// Expected Output: true

	public void testCreateDailyDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			assertEquals(true, testObj.createDailyDeliveryDocket());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 34
	// Test Objective:Create Daily Delivery Docket
	// Inputs: none
	// Expected Output: false

	public void testCreateDailyDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.createDailyDeliveryDocket());
		} catch (Exception e) {
			fail("Not expected");
		}
	}
// Test #: 33
	// Test Objective:Create Weekly Delivery Docket
	// Inputs: none
	// Expected Output: true

	public void testCreateWeeklyDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			assertEquals(true, testObj.createWeeklyDeliveryDocket());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 34
	// Test Objective:Create Weekly Delivery Docket
	// Inputs: none
	// Expected Output: false

	public void testCreateWeeklyDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.createWeeklyDeliveryDocket());
		} catch (Exception e) {
			fail("Not expected");
		}
	}
// Test #: 35
	// Test Objective:Create BiWeekly Delivery Docket
	// Inputs: none
	// Expected Output: true

	public void testCreateBiWeeklyDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			assertEquals(true, testObj.createBiWeeklyDeliveryDocket());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 36
	// Test Objective:Create BiWeekly Delivery Docket
	// Inputs: none
	// Expected Output: false

	public void testCreateBiWeeklyDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.createBiWeeklyDeliveryDocket());
		} catch (Exception e) {
			fail("Not expected");
		}
	}
// Test #: 35
	// Test Objective:Create Mountly Delivery Docket
	// Inputs: none
	// Expected Output: true

	public void testCreateMountlyDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			assertEquals(true, testObj.createMonthlyDeliveryDocket());
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 36
	// Test Objective:Create Mountly Delivery Docket
	// Inputs: none
	// Expected Output: false

	public void testCreateMountlyDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.createMonthlyDeliveryDocket());
		} catch (Exception e) {
			fail("Not expected");
		}
	}
// Test #: 37
	// Test Objective:delete Delivery Area
	// Inputs: none
	// Expected Output: true

	public void testDeleteDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.openConnection();
			assertEquals(true, testObj.deleteDeliveryArea(new DeliveryArea(5,"test")));
			testObj.closeConnection();
		} catch (Exception e) {
			fail("Not expected");
		}
	}

	// Test #: 38
	// Test Objective:Delete Delivery Area
	// Inputs: none
	// Expected Output: false

	public void testDeleteDeliveryArea002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.deleteDeliveryArea(new DeliveryArea(1,"test")));
		} catch (Exception e) {
			fail("Not expected");
		}
	}



























	public void testCreateInvoice001() {
		QueryTableModel testObj = new QueryTableModel();
		testObj.openConnection();
		try {
			testObj.createInvoice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		testObj.closeConnection();
	}
}




















