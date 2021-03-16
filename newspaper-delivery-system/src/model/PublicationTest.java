package model;

import exceptions.PublicationException;
import junit.framework.TestCase;
import model.Publication;

public class PublicationTest extends TestCase {

	// Test #: 1
	// Test Objective: Creating a publication.
	// Inputs: pubName = "A0025, Cosmopolitan", "€10.50"
	// Expected Output: Publication Object created."

	public void testPublication001() {
		try {
			Publication testObj = new Publication("A002526", "Cosmopolitan", 10.50);

			assertEquals("Cosmopolitan", testObj.getName());
			assertEquals(10.50, testObj.getPrice());
		} catch (PublicationException e) {
			fail("Exception is not expected!");
		}
	}

	// Test #: 2
	// Test Objective: Prevent empty string.
	// Inputs: "null"
	// Expected Output: "Publication name, must be from 1 to 15 characters."

	public void testValidateName001() {
		try {
			Publication.validateName("");
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication name, must be from 1 to 15 characters.", e.getMessage());
		}
	}

	// Test #: 3
	// Test Objective: Preventing string over 15characters.
	// Inputs: "Cosmopolitan is Made By Some"
	// Expected Output: "Publication name, must be max of 15 characters"

	public void testValidateName002() {
		try {
			Publication.validateName("Cosmopolitan is Made By Some");
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication name, must be max of 15 characters", e.getMessage());
		}
	}
	// Test #: 4
	// Test Objective: Price can not be 0.
	// Inputs: 0
	// Expected Output: "Publication price, must be above 0"

	public void testValidatePrice001() {
		try {
			Publication.validatePrice(0);
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication price, must be above 0", e.getMessage());
		}
	}
	// Test #: 5
	// Test Objective: Price can not be above more then 99.99.
	// Inputs: 100
	// Expected Output: "Publication price, must be below 100"

	public void testValidatePrice002() {
		try {
			Publication.validatePrice(100);
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication price, must be below 100", e.getMessage());
		}
	}

	// Test #: 6
	// Test Objective: Publication Order id must be exactly 7 char.
	// Inputs: "A00252"
	// Expected Output: "Publication Order Id must be exactly 7 characters long, it can not be empty"

	public void testValidateOrderId001() {
		try {
			Publication.validateOrderId("A00252");
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication Order Id must be exactly 7 characters long, it can not be empty", e.getMessage());
		}
	}
	// Test #: 7
	// Test Objective: Publication Order id can not be empty.
	// Inputs: " "
	// Expected Output: "Publication Order Id must be exactly 7 characters long, it can not be empty"

	public void testValidateOrderId002() {
		try {
			Publication.validateOrderId("A00252");
			fail("Expected exception.");

		} catch (PublicationException e) {
			assertEquals("Publication Order Id must be exactly 7 characters long, it can not be empty", e.getMessage());
		}
	}

}
