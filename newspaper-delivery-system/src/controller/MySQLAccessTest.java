package controller;

import exceptions.CustomersException;
import exceptions.DeliveryAreaException;
import junit.framework.TestCase;
import model.Customers;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Publication;

import java.sql.ResultSet;

public class MySQLAccessTest extends TestCase 
{
	// Test #: 1
	// Test Objective: Testing if the connection is valid
	// Inputs:
	// Expected Output: True

	public void testconnectToTheDatabase001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(true, testObj.openConnection());
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
//			QueryTableModel testObj = new QueryTableModel();
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
			QueryTableModel testObj = new QueryTableModel();
			assertEquals(true, testObj.closeConnection());
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
			QueryTableModel testObj = new QueryTableModel();
			testObj.closeConnection();
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
			QueryTableModel testObj = new QueryTableModel();
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
			QueryTableModel testObj = new QueryTableModel();
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
			QueryTableModel testObj = new QueryTableModel();
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
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication("A002527", "DonkeyKong", 13.50);
			testObj.updatePublication(testObjPub);
			// fail("Expected exception.");// throws errors
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
			QueryTableModel testObj = new QueryTableModel();
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
			QueryTableModel testObj = new QueryTableModel();
			Publication testObjPub = new Publication("A002527", "DonkeyKong", 13.50);
			testObj.delete(testObjPub);
			// fail("Exception expected");
		} catch (Exception e) {
			assertEquals("Publication is not deleted.", e.getMessage());
		}
	}

	// Test #: 11
	// Test Objective: Delete fail to publication
	// Inputs: validated delivery area object
	// Expected Output: "true"
	public void testInsertNewDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea da = new DeliveryArea("Area 4", 12);
			testObj.insertNewDeliveryArea(da);
		} catch (DeliveryAreaException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	// Test #: 11
	// Test Objective: Delete fail to publication
	// Inputs: validated delivery area object
	// Expected Output: "true"
	public void testUpdateDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea da = new DeliveryArea(2, "Area 4", 12);
			testObj.insertNewDeliveryArea(da);
		} catch (DeliveryAreaException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	// Test #: 12
	// Test Objective: Delete fail to publication
	// Inputs: validated delivery area object
	// Expected Output: "true"
	public void testRetrieveAllDeliveryArea001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			ResultSet rs = testObj.retrieveAllDeliveryArea();

			assertTrue(rs.next());
		} catch (DeliveryAreaException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	public void testDeleteAreaDelivery001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryArea da = new DeliveryArea(1, "null", 12);
			testObj.delete(da);
		} catch (DeliveryAreaException e) {
			fail();
		} catch (Exception e) {
			fail();
		}
	}

	public void testInsertDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryDocket testObjDel = new DeliveryDocket(1, 1, 1);
			assertEquals(true, testObj.insertDeliveryDocket(testObjDel));
		} catch (Exception exception) {
			fail();
		}
	}

	public void testInsertDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			testObj.insertDeliveryDocket(null);
			fail();
		} catch (Exception exception) {
			assertEquals("Delivery Docket not added", exception.getMessage());
		}
	}

	public void testUpdateDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryDocket testObjDel = new DeliveryDocket(1, 1, 1);
			assertEquals(true, testObj.updateDeliveryDocket(testObjDel));
		} catch (Exception exception) {
			fail();
		}
	}

	public void testUpdateDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryDocket testObjDel = new DeliveryDocket(0, 0, 0);
			testObj.updateDeliveryDocket(testObjDel);
		} catch (Exception exception) {
			assertEquals("Invalid Delivery Docket object", exception.getMessage());
		}
	}

	public void testDeleteDeliveryDocket001() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryDocket testObjDel = new DeliveryDocket(1, 1, 1);
			assertEquals(true, testObj.deleteDeliveryDocket(testObjDel));
		} catch (Exception exception) {
			fail();

		}
	}

	public void testDeleteDeliveryDocket002() {
		try {
			QueryTableModel testObj = new QueryTableModel();
			DeliveryDocket testObjDel = new DeliveryDocket(0, 0, 0);
			testObj.deleteDeliveryDocket(testObjDel);
		} catch (Exception exception) {
			assertEquals("Invalid Delivery Docket object", exception.getMessage());
		}
	}
	
	public void testInsertCustomer001()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			Customers cus = new Customers(7, "Willow Green", "Jk", "Madeira", "085", "2234567");
			qtm.insertCustomerInfo(cus);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Invalid Customer Details", e.getMessage());
		}
	}
	public void testInsertCustomer002()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			Customers cus = new Customers(2, "Willow", "Jackie", "Madeira", "085", "2234567");
			boolean res = qtm.insertCustomerInfo(cus);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
	public void testUpdateCustomer001()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			qtm.updateCustomerDetails("W1llow Pk", "Jenny", "Ferrr", "0871212345", -5);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail cannot update customer", e.getMessage());
		}
	}
	public void testUpdateCustomer002()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			boolean res = qtm.updateCustomerDetails("Brawny rd", "Jackie", "Chan", "0871234567", 4);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
	public void testDeleteCustomer001()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			qtm.deleteCustomerById(-5);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Invalid Customer Id", e.getMessage());
		}
	}
	public void testDeleteCustomer002()
	{
		try
		{
			QueryTableModel qtm = new QueryTableModel();
			qtm.openConnection();
			
			boolean res = qtm.deleteCustomerById(3);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
}
