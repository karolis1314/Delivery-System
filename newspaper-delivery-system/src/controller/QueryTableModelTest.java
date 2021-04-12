package controller;

import junit.framework.TestCase;
import model.Publication;
import model.StaffMember;

public class QueryTableModelTest extends TestCase {

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
	// Test #: 2
	// Test Objective: Testing if the connection is not valid
	// Inputs:
	// Expected Output: false

	public void testOpenConnection002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(false, testObj.openConnection());
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

	// Test #: 4
	// Test Objective: Fail the connection
	// Inputs:
	// Expected Output: False

	public void testCloseConnection002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.closeConnection();
			fail("Exception expected."); // Can not fake a fail connection close.
		} catch (Exception e) {
			assertEquals("Connection not closed", e.getMessage());
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
			testObj.openConnection();
			assertEquals(false, testObj.insertNewPublication(testObjPub));
			testObj.closeConnection();
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
			testObj.openConnection();
			testObj.updatePublication(testObjPub);
			testObj.closeConnection();
		} catch (Exception e) {
			assertEquals("Publication is not updated.", e.getMessage());
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
			testObj.openConnection();
			testObj.deletePublication(testObjPub);
			testObj.closeConnection();
		} catch (Exception e) {
			assertEquals("Publication is not deleted.", e.getMessage());
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
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(6, "Karoliss", "Valatkaa", "password", 3);
			assertEquals(false, testObj.insertNewStaff(testObjStaff));
			testObj.closeConnection();
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
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(89, "Karoliss", "Update", "password", 3);
			testObj.updateStaff(testObjStaff);
			testObj.closeConnection();
		} catch (Exception e) {
			assertEquals("Staff is not updated.", e.getMessage());
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
			testObj.openConnection();
			StaffMember testObjStaff = new StaffMember(86, "Karoliss", "Update", "password", 3);
			testObj.deleteStaff(testObjStaff);
			testObj.closeConnection();
		} catch (Exception e) {
			assertEquals("Staff is not deleted.", e.getMessage());
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
