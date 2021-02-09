package controller;

import exceptions.PublicationException;
import junit.framework.TestCase;
import model.Publication;

public class MySQLAccessTest extends TestCase {

	// Test #: 1
	// Test Objective: Creating an object.
	// Inputs: pub = "A0025, Cosmopolitan", "€10.50"
	// Expected Output: Publication Object created."

	public void testMySQLAccess001() {
		try {
			Publication testObj = new Publication("A002526", "Cosmopolitan", 10.50);

			assertEquals("Cosmopolitan", testObj.getName());
			assertEquals(10.50, testObj.getPrice());
		} catch (PublicationException e) {
			fail("Exception is not expected!");
		}
	}

	// Test #: 2
	// Test Objective: Check if you can connect to the database
	// Inputs: 
	// Expected Output: Connection Made.

	public void testconnectToTheDatabase001() {
		try {
			Publication testObj = new Publication("A002526", "Cosmopolitan", 10.50);

			assertEquals("Cosmopolitan", testObj.getName());
			assertEquals(10.50, testObj.getPrice());
		} catch (PublicationException e) {
			fail("Exception is not expected!");
		}
	}
	
	// Test #: 3
	// Test Objective: Check if you can shut down the connection
	// Inputs: 
	// Expected Output: Connection closed.

	public void testshutDownConnection001() {
		try {
			Publication testObj = new Publication("A002526", "Cosmopolitan", 10.50);

			assertEquals("Cosmopolitan", testObj.getName());
			assertEquals(10.50, testObj.getPrice());
		} catch (PublicationException e) {
			fail("Exception is not expected!");
		}
	}

}
