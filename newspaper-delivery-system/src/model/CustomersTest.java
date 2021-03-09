package test;

import exceptions.CustomersException;
import junit.framework.TestCase;
import model.Address;
import model.Customers;

public class CustomersTest extends TestCase 
{
	/*
	 	#Test 1: Customer Name
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testCustomerName001()
	{
		/* 
		 	Object: Test name for invalid character
		 	Inputs: Name in String format
		 	Output: Test will fail, invalid character detected
		 	Envmnt: Junit3 
		*/	
		try
		{
			Customers.validateCustomerName("Laykon1(", "Frescro");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Name contains invalid character.", e.getMessage());
		}
	}
	public void testCustomerName002()
	{
		/* 
		 	Object: Test name for invalid character
		 	Inputs: Name in String format
		 	Output: Test will fail, invalid character detected
		 	Envmnt: Junit3 
		*/	
		try
		{
			Customers.validateCustomerName("Laykon", "Fr#e3scro");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Name contains invalid character.", e.getMessage());
		}
	}
	public void testCustomerName003()
	{
		/* 
		 	Object: Test name for invalid character
		 	Inputs: Name in String format
		 	Output: Test will fail, invalid character detected
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerName("Lay@1kon)", "Fr#escro");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Name contains invalid character.", e.getMessage());
		}
	}
	public void testCustomerName004()
	{
		/* 
		 	Object: Test name for invalid character
		 	Inputs: Name in String format
		 	Output: Test will pass, name in correct format
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Customers.validateCustomerName("Brent", "Ford");
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("No errors expected.");
		}
	}
	public void testCustomerName005()
	{
		/* 
		 	Object: Test name for length not too short
		 	Inputs: Length < 3
		 	Output: Test will fail, length too short
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerName("Du", "Ckling");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Name length greater than two.", e.getMessage() );
		}
	}
	public void testCustomerName006()
	{
		/* 
		 	Object: Test name for length not too short
		 	Inputs: Length > 25
		 	Output: Test will fail, length too short
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerName("Livinhow", "Itfeelsbutiknowitssurreeal");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Name length less than 26.", e.getMessage() );
		}
	}
	
	public void testCustomerId001()
	{
		/* 
		 	Object: Test id in range of 1-10000 inclusive
		 	Inputs: id < 1 
		 	Output: Test will fail, invalid id
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(-69);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected id less than 1.", e.getMessage());
		}
	}
	public void testCustomerId002()
	{
		/* 
		 	Object: Test id in range of 1-10000 inclusive
		 	Inputs: id > 10000 
		 	Output: Test will fail, invalid id too large
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(10069);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected id greater than 10000.", e.getMessage());
		}
	}
	public void testCustomerId003()
	{
		/* 
		 	Object: Test id in range of 1-10000 inclusive
		 	Inputs: id between 1 and 10000 
		 	Output: Test will pass, id in valid partition
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Customers.validateCustomerId(69);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Errors are not expected here.");
		}
	}
	public void testCustomerId004()
	{	
		/* 
		 	Object: Test boundary values
		 	Inputs: id < 1 
		 	Output: Test will fail, invalid id too large
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(0);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail id less than 1.", e.getMessage());
		}
	}
	public void testCustomerId005()
	{
		/* 
		 	Object: Test boundary values
		 	Inputs: id < 1 
		 	Output: Test will fail, invalid id too large
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(Integer.MIN_VALUE);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail id less than 1.", e.getMessage());
		}	
	}
	public void testCustomerId006()
	{
		/* 
		 	Object: Test boundary values
		 	Inputs: id > 10000 
		 	Output: Test will fail, invalid id too large
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(10001);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected", e.getMessage());
		}	
	}
	public void testCustomerId007()
	{
		/* 
		 	Object: Test boundary values
		 	Inputs: id > 10000
		 	Output: Test will fail, invalid id too large
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerId(Integer.MAX_VALUE);
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected.", e.getMessage());
		}	
	}
	public void testCustomerId008()
	{
		/* 
		 	Object: Test valid boundary value
		 	Inputs: id between 1 and 10000 
		 	Output: Test will pass, id in valid partition
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Customers.validateCustomerId(10000);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Errors are not expected here.");
		}
	}
	
	
	public void testCustomerNumber001()
	{
		/* 
		 	Object: Test mobile number in correct format
		 	Inputs:	Number in wrong format
		 	Output: Failure expected
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerNumber("0784", "33421212");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, prefix in wrong format.", e.getMessage());
		}	
	}
	public void testCustomerNumber002()
	{
		/* 
		 	Object: Test mobile number in correct format
		 	Inputs:	Number in wrong format
		 	Output: Failure expected
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerNumber("089", "3342443222");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, digtis in wrong format", e.getMessage());
		}	
	}
	public void testCustomerNumber003()
	{
		/* 
		 	Object: Test mobile number in correct format
		 	Inputs:	Number in wrong format
		 	Output: Failure expected
		 	Envmnt: Junit3 
		*/
		try
		{
			Customers.validateCustomerNumber("0K7", "778K543");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail, invalid character.", e.getMessage());
		}	
	}
	public void testCustomerNumber004()
	{
		/* 
		 	Object: Test mobile number in correct format
		 	Inputs:	Number in correct format
		 	Output: Pass expected
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Customers.validateCustomerNumber("085", "7742123");
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception not expected.");
		}	
	}
	
	public void testCustomerAddress001()
	{
		try
		{
			Address.validateCustomerAddress(1 ,-7, "Bacon St", "Gort", "Galway", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, invalid address number", e.getMessage());
		}
	}
	public void testCustomerAddress002()
	{
		try
		{
			Address.validateCustomerAddress(10000, 5, "Ble", "Celbridge", "Kildare", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, address too short.", e.getMessage());
		}
	}
	public void testCustomerAddress003()
	{
		try
		{
			Address.validateCustomerAddress(100, 27, "Pryor rd", "Jushhdaloskshinggirdlibereslasd", "Dublin", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected address too long.", e.getMessage());
		}
	}
	public void testCustomerAddress004()
	{
		try
		{
			Address.validateCustomerAddress(160, 100, "Valley Ct", "Athlone", "W3stmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected: invalid characted detected in input field.", e.getMessage());
		}
	}
	public void testCustomerAddress005()
	{
		try
		{
			Address.validateCustomerAddress(50, 100, "Valley Ct", "Athlone", "Westmeath", "Ir3land");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected-: Invalid character detected.", e.getMessage());
		}
	}
	public void testCustomerAddress006()
	{
		try
		{
			boolean res = Address.validateCustomerAddress(4, 18, "Willow park", "Athlone", "Westmeath", "Ireland");
			assertEquals(true,res);
		}
		catch(CustomersException e)
		{
			fail("Execptions not expected here.");
		}
	}	
	public void testCustomerAddress007()
	{
		try
		{
			Address.validateCustomerAddress(0 ,75, "Broad St", "Killucen", "Westmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, invalid id", e.getMessage());
		}
	}
	public void testCustomerAddress008()
	{
		try
		{
			Address.validateCustomerAddress(Integer.MIN_VALUE ,25, "Ballymahon rd", "Athlone", "Westmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, invalid id", e.getMessage());
		}
	}
	public void testCustomerAddress009()
	{
		try
		{
			Address.validateCustomerAddress(10001 ,50, "Rockbrook", "Monsksland", "Westmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, invalid id", e.getMessage());
		}
	}
	public void testCustomerAddress010()
	{
		try
		{
			Address.validateCustomerAddress(Integer.MAX_VALUE ,100, "WIllow Ave", "Athlone", "Westmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected, invalid id", e.getMessage());
		}
	}
	
	
	
	public void testCustomerObject001()
	{
		try
		{
			new Customers(5, "Ja", "Frost", "083", "4341212");
			fail("Incorrect Details Entered");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail Expected", e.getMessage());
		}
	}
	public void testCustomerObject002()
	{
		try
		{
			Customers cus = new Customers(5, "Jack", "Frost", "083", "4456767");
			assertEquals(5, cus.getId());
			assertEquals("Jack", cus.getfName());
			assertEquals("Frost", cus.getlName());
			assertEquals("0834456767", cus.getNumber());
		}
		catch(CustomersException e)
		{
			fail("No exceptions expected");
		}
	}
	
	public void testAddress001()
	{
		try
		{
			new Address(10, 22, "Dub", "Athlone", "Westmeath", "Ireland");
			fail("Exception expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail expected", e.getMessage());
		}
		
	}
	public void testAddress002()
	{
		try
		{
			Address add = new Address(10, 22, "Dub rd", "Athlone", "Westmeath", "Ireland");
			assertEquals(10, add.getAddress_id());
			assertEquals(22, add.getNumber());
			assertEquals("Dub rd", add.getStreet());
			assertEquals("Athlone",add.getTown());
			assertEquals("Westmeath", add.getCity());
			assertEquals("Ireland", add.getCountry());
			
		}
		catch(CustomersException e)
		{
			fail("No exception expected");
		}
	}
	
//	public void testDataBase001()
//	{
//		
//	}
//	
//	public void testDataBase002()
//	{
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
