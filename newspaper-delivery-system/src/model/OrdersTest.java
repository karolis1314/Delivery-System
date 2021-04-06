package model;

import exceptions.OrderException;
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
		catch(OrderException e)
		{
			assertEquals("", e.getMessage());
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("", e.getMessage());
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("", e.getMessage());
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("", e.getMessage());
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
			Orders.validateCustomerId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("", e.getMessage());
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
			boolean res =  Orders.validateCustomerId(0);
			assertEquals(res, true);
		}
		catch(OrderException e)
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
		catch(OrderException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail: Invalid Date Detected", e.getMessage());
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			Orders.validatePublicationId(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
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
			boolean res = Orders.validatePublicationId(0);
			assertEquals(res, true);
			
		}
		catch(OrderException e)
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
	public void testOrderAmout001()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout002()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout003()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout004()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout005()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout006()
	{
		/* 
		 	Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			Orders.validateOrderAmount(0);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testOrderAmout007()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validateOrderAmount(7);
			assertEquals(true, res);
		}
		catch(OrderException e)
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
		catch(OrderException e)
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
		catch(OrderException e)
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
		catch(OrderException e)
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
			Orders.validateActiveOrders(false);
			fail("Exception Expected");
		}
		catch(OrderException e)
		{
			assertEquals("Fail", e.getMessage());
		}
	}
	public void testActiveOrders005()
	{
		/* 
	 		Object: 
		 	Inputs: 
		 	Output: 
		 	Envmnt: Junit3 
		*/
		try
		{
			boolean res = Orders.validateActiveOrders(false);
			assertEquals(res, true);
		}
		catch(OrderException e)
		{
			fail("Exception Not Expected");
		}
	}
	
}
