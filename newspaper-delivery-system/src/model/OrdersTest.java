package model;

import exceptions.OrdersException;
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
	public void testCustomerId001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/			
		try
		{
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id cant't be zero", e.getMessage());
		}
	}
	public void testCustomerId002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validateCustomerId(Integer.MIN_VALUE);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id too low", e.getMessage());
		}
	}
	public void testCustomerId003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateCustomerId(-5);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("id Lower than 1", e.getMessage());
		}
	}
	public void testCustomerId004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateCustomerId(200);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("", e.getMessage());
		}
	}
	public void testCustomerId005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateCustomerId(Integer.MAX_VALUE);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("", e.getMessage());
		}
	}
	public void testCustomerId006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateCustomerId(250);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id greater than 200", e.getMessage());
		}
	}
	public void testCustomerId007()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validateCustomerId(-250);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id can't be lower than 1", e.getMessage());
		}
	}
	public void testCustomerId008()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			boolean res =  Orders.validateCustomerId(13);
			assertEquals(res, true);
		}
		catch(OrdersException e)
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
	public void testPublicationId001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id can't be zero", e.getMessage());
		}
	}
	public void testPublicationId002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/	
		try
		{
			Orders.validatePublicationId(Integer.MIN_VALUE);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Id can't lower than one", e.getMessage());
		}
	}
	public void testPublicationId003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/		
		try
		{
			Orders.validatePublicationId(-8);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testPublicationId004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validatePublicationId(200);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testPublicationId005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validatePublicationId(Integer.MAX_VALUE);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testPublicationId006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validatePublicationId(380);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testPublicationId007()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validatePublicationId(-670);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
		}
	}
	public void testPublicationId008()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validatePublicationId(45);
			assertEquals(res, true);
			
		}
		catch(OrdersException e)
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

	public void testActiveOrders001()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateActiveOrders(false);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testActiveOrders002()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateActiveOrders(false);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testActiveOrders003()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateActiveOrders(false);
			fail("Exception Expected");
		}
		catch(OrdersException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testActiveOrders004()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res =Orders.validateActiveOrders(true);
			assertEquals(res, true);
		}
		catch(OrdersException e)
		{
			fail("Exception Expected");
		}
	}
}
