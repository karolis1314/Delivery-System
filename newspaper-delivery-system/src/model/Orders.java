package model;

import exceptions.OrdersException;

public class Orders 
{
	int customerId;
	int publicationId;
	boolean active;
	
	public Orders(int cus_id, int pub_id, boolean isActive) throws OrdersException
	{
		try
		{
			validateCustomerId(cus_id);
			validatePublicationId(pub_id);
			validateActiveOrders(isActive);
		}
		catch(OrdersException e)
		{
			System.out.println(e);
			throw e;
		}
		finally
		{
			customerId=cus_id;
			publicationId=pub_id;
			active=isActive;
		}
	}
	static boolean validateCustomerId(int cus_id) throws OrdersException
	{
		if(cus_id<0)
			throw new OrdersException("Id can't be lower than 0");
		else if(cus_id>200)
			throw new OrdersException("Id can't be greater than 200");
		else
			return true;
	}
	
	static boolean validatePublicationId(int pub_id) throws OrdersException
	{
		if(pub_id<0)
			throw new OrdersException("Id can't be lower than 0");
		else if(pub_id>200)
			throw new OrdersException("Id can't be greater than 200");
		else
			return true;
	}
	static boolean validateActiveOrders(boolean active) throws OrdersException
	{
		if(!active)
			return false;
		else
			return true;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public boolean isActive() {
		return active;
	}
	
}
