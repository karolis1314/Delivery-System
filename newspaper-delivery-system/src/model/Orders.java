package model;

import exceptions.CustomersException;

public class Orders 
{
	String subscriptionStart;
	String subscriptionEnd;
	int limit;
	String dayDelivered;
	String publication;
	
	public Orders(String StartDate, String EndDate, String subItem, int limit, String day) throws CustomersException
	{
		throw new CustomersException("No Tests Written");
	}
	
	public String getStartDate()
	{
		return subscriptionStart;
	}
	
	public String getEndDate()
	{
		return subscriptionEnd;
	}
	
	public int getLimit()
	{
		return limit;
	}
	
	public String getSubscribtion()
	{
		return publication;
	}
	
	public String getDay()
	{
		return dayDelivered;
	}
	
	public static boolean validateStartDate(int date, String month, int year)throws CustomersException
	{
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		boolean res=false;
		
		if(date<0)
			throw new CustomersException("Date cannot be less than zero");
		else if(date > 31)
			throw new CustomersException("Date cannot be greater than 31");
		else if(year < 2010)
			throw new CustomersException("Year too Low");
		else if(year > 2021)
			throw new CustomersException("Year too High");
		else if(month.length()<3 || month.length()>9)
			throw new CustomersException("Not a valid month");
		else if(month.length()>2 || month.length()<9)
		{
			for(int i=0; i<months.length; i++)
				if(months[i].equals(month))
				{
					res=true;break;
				}
			if(res==false)
				throw new CustomersException("Month Does Not Exist");
		}
		return res;
	}
	
	public static boolean validateEndDate(int date, String month, int year)throws CustomersException
	{
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		boolean res=false;
		
		if(date<0)
			throw new CustomersException("Date cannot be less than zero");
		else if(date > 31)
			throw new CustomersException("Date cannot be greater than 31");
		else if(year < 2022)
			throw new CustomersException("Year too Low");
		else if(year > 2030)
			throw new CustomersException("Year too High");
		else if(month.length()<3 || month.length()>9)
			throw new CustomersException("Not a valid month");
		else if(month.length()>2 || month.length()<9)
		{
			for(int i=0; i<months.length; i++)
				if(months[i].equals(month))
				{
					res=true;break;
				}
			if(res==false)
				throw new CustomersException("Month Does Not Exist");
		}
		return res;
	}
	
	public static boolean validateLimit(int limit) throws CustomersException
	{
		if(limit<1)
			throw new CustomersException("Invalid Limit - Too Low");
		else if(limit>10)
			throw new CustomersException("Inavalid Limit - Too High");
		else
			return true;
	}
	
	public static boolean validateSubscription(String publication) throws CustomersException
	{
		throw new CustomersException("No Tests Written");
	}
	
	public static boolean validateDayDelivered(String day) throws CustomersException
	{
		throw new CustomersException("No Tests Written");
	}
}

/*
	As a newsagent I want to see when the subscription will expire so I can ask a customer if he/she would like to extend it
	
	a) Verify subscriptions start date
	b) Verify subscriptions end date
	
	As a newsagent I want to see what my customers are ordering so I can suggest a new item or update it on request
	
	a) Verify items subscribed
	b) Verify changes made
	c) Verify limit
	d) Verify what item delivered which day"
*/