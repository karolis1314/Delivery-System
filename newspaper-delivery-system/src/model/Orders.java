package model;

import exceptions.OrderException;

public class Orders 
{
	int customerId;
	int publicationId;
	int orderAmount;
	boolean active;
	
	public Orders(int cus_id, int pub_id, double amt, boolean isActive) throws OrderException
	{
		throw new OrderException("No Tests Written");
	}
	
	static boolean validateCustomerId(int cus_id) throws OrderException
	{
		throw new OrderException("No Tests Written");
	}
	
	static boolean validatePublicationId(int pub_id) throws OrderException
	{
		throw new OrderException("No Tests Written");
	}
	
	static boolean validateOrderAmount(double amt) throws OrderException
	{
		throw new OrderException("No Tests Written");
	}
	
	static boolean validateActiveOrders(boolean active) throws OrderException
	{
		throw new OrderException("No Tests Written");
	}
	
}
