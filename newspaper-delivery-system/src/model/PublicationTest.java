package model;

import exceptions.PublicationException;
import junit.framework.TestCase;

public class PublicationTest extends TestCase 
{
//	Test number: 1
//  Objective: Create Publication Object.
//  Test type: JUint
//  Input: name = (1, "1", "Karolis", 12.50, 1)
//  Output: No Faults.

	public void testPublication001() {
		try {
			Publication test = new Publication(1, "1", "Karolis", 12.50, 1);
			assertEquals(1, test.getId());
			assertEquals("1", test.getFrequencyInDays());
			assertEquals("Karolis", test.getName());
			assertEquals(12.50, test.getPrice());
			assertEquals(1, test.getStock());

		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}

//	Test number: 2
//  Objective: Fail to create the object.
//  Test type: JUint
//  Input: name = (1, null, "Karolis", 12.50, 1)
//  Output: Failed to create the object.

	public void testPublication002() {
		try {
			Publication test = new Publication(1, null, "Karolis", 12.50, 1);
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Failed to create.", e.getMessage());
		}
	}

//	Test number: 3
//  Objective: Validate Frequency in days.
//  Test type: JUint
//  Input: name = "1", "7", "14", "30"
//  Output: Succesfull.

	public void testValidateFrequencyInDays001() {
		try {
			Publication.validateFrequencyInDays("1");
			Publication.validateFrequencyInDays("7");
			Publication.validateFrequencyInDays("14");
			Publication.validateFrequencyInDays("30");
		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}

//	Test number: 4
//  Objective: Fail the validation
//  Test type: JUint
//  Input: name = "2"
//  Output: "Frequency of the publication must be valid, 1-7-14-30.".

	public void testValidateFrequencyInDays002() {
		try {
			Publication.validateFrequencyInDays("2");
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Frequency of the publication must be valid, 1-7-14-30.", e.getMessage());
		}
	}

//	Test number: 5
//  Objective: Pass the name tests.
//  Test type: JUint
//  Input: name = "Karolis"
//  Output: No errors.

	public void testValidateName001() {
		try {
			Publication.validateName("Karolis");
			Publication.validateName("Q");
			Publication.validateName("Spar");
			Publication.validateName("Friend");
		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}

//	Test number: 6
//  Objective: Make sure it does not take in 0 or null.
//  Test type: JUint
//  Input: name = ""
//  Output:"Name of Publication can not be empty."

	public void testValidateName002() {
		try {
			Publication.validateName("");
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Name of Publication can not be empty.", e.getMessage());
		}
	}

//	Test number: 7
//  Objective: Make sure the name is not 25 characters long.
//  Test type: JUint
//  Input: name = KarolisKarolisKarolisKarolisKarolisKarolisKarolisKarolis
//  Output:"Name of Publication can not be larger than 25 characters."

	public void testValidateName003() {
		try {
			Publication.validateName("KarolisKarolisKarolisKarolisKarolisKarolisKarolisKarolis");
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Name of Publication can not be larger than 25 characters.", e.getMessage());
		}
	}

//	Test number: 8
//  Objective: I can pass a double number.
//  Test type: JUint
//  Input: name = 2.50
//  Output:No Errors

	public void testValidatePrice001() {
		try {
			Publication.validatePrice(2.50);
		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}

//	Test number: 9
//  Objective: Price is not 0.
//  Test type: JUint
//  Input: name = 0
//  Output:"Price of the Publication can be 0 or larger than ?20.00."

	public void testValidatePrice002() {
		try {
			Publication.validatePrice(0);
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Price of the Publication can be 0 or larger than ?20.00.", e.getMessage());
		}
	}
//	Test number: 10
//  Objective: Price is not a negative number.
//  Test type: JUint
//  Input: name = -1.10
//  Output:"Price of the Publication can be 0 or larger than ?20.00."

	public void testValidatePrice003() {
		try {
			Publication.validatePrice(-1.10);
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Price of the Publication can be 0 or larger than ?20.00.", e.getMessage());
		}
	}

//	Test number: 11
//  Objective: Price is not a larger than ?20.00.
//  Test type: JUint
//  Input: name = 20.01
//  Output:"Price of the Publication can be 0 or larger than ?20.00."

	public void testValidatePrice004() {
		try {
			Publication.validatePrice(20.01);
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Price of the Publication can be 0 or larger than ?20.00.", e.getMessage());
		}
	}

//	Test number: 12
//  Objective: Pass Stock.
//  Test type: JUint
//  Input: name = 0
//  Output: No errors.

	public void testValidateStock001() {
		try {
			Publication.validateStock(0);
		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}
//	Test number: 13
//  Objective: Pass Stock.
//  Test type: JUint
//  Input: name = 5
//  Output: No errors.

	public void testValidateStock002() {
		try {
			Publication.validateStock(5);
		} catch (PublicationException e) {
			fail("Exception not expected.");
		}
	}

//	Test number: 14
//  Objective: Stock can not be less than 0.
//  Test type: JUint
//  Input: name = -1
//  Output: "Stock can not be less than 0."

	public void testValidateStock003() {
		try {
			Publication.validateStock(-1);
			fail("Exception expected.");
		} catch (PublicationException e) {
			assertEquals("Stock can not be less than 0.", e.getMessage());
		}
	}
}