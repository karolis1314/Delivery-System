package model;

import exceptions.CustomersException;
import junit.framework.TestCase;

public class OrdersTest extends TestCase 
{
	/*
	 	#Test 1: 
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testStartDate001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/			
		try
		{
			Orders.validateStartDate(-20, "January", 2019);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testStartDate002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validateStartDate(38, "August", 2018);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testStartDate003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(12, "Marcjh", 2020);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Month Detected", e.getMessage());
		}
	}
	public void testStartDate004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(22, "Sepetemeber", 2021);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Month Detected", e.getMessage());
		}
	}
	public void testStartDate005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(17, "Ma", 2017);
			fail("Exception Expected");	
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Month Detected", e.getMessage());
		}
	}
	public void testStartDate006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(13, "June", 1990);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testStartDate007()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(16, "December", 2025);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testStartDate008()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateStartDate(27, "May", -2121);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testStartDate009()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			boolean res = Orders.validateStartDate(23, "May", 2018);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
	
	/*
	 	#Test 2: 
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testEndDate001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validateEndDate(-20, "January", 2022);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testEndDate002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateEndDate(99, "February", 2024);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testEndDate003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validateEndDate(29, "Augustinet", 2027);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Month Not Valid", e.getMessage());
		}
	}
	public void testEndDate004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateEndDate(20, "Ja", 2025);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Month Not Valid", e.getMessage());
		}
	}
	public void testEndDate005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateEndDate(1, "Jury", 2029);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Month Not Valid", e.getMessage());
		}
	}
	public void testEndDate006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateEndDate(11, "April", 2020);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testEndDate007()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateEndDate(7, "October", 2035);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testEndDate008()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateEndDate(14, "January", -2020);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail: Invalid Year Detected", e.getMessage());
		}
	}
	public void testEndDate009()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validateEndDate(11, "September", 2023);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
	
	/*
	 	#Test 3: 
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testLimit001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(0);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(Integer.MIN_VALUE);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(11);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(Integer.MAX_VALUE);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(-10);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateLimit(120);
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testLimit007()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validateLimit(7);
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}

	
	
	/*
	 	#Test 4: 
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testSubscription001()
	{
		
	}
	
	
	/*
	 	#Test 5: 
	 	- an object under test
		- a test objective
		- a definition of the inputs
		- a definition of expected outputs
		- a definition of the test environment
	*/
	public void testDayDelivered001()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateDayDelivered("Fryday");
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testDayDelivered002()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateDayDelivered("");
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testDayDelivered003()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateDayDelivered("");
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testDayDelivered004()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateDayDelivered("");
			fail("Exception Expected");
		}
		catch(CustomersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testDayDelivered005()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validateDayDelivered("");
			assertEquals(true, res);
		}
		catch(CustomersException e)
		{
			fail("Exception Not Expected");
		}
	}
	
}
