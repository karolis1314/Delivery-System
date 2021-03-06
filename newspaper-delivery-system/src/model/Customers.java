package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.CustomersException;

public class Customers 
{
	/*
		The objective of this tests is to verify customer details before they can be added to database
	*/
	private int cus_id,areaId;
	private String fName,lName,number, address;

	public Customers(int cus_id, String add, String firstName, String lastName, String prefix, String digits, int area_id) throws CustomersException
	{
		try
		{
			validateCustomerId(cus_id);
			validateCustomerAddress(add);
			validateCustomerName(firstName, lastName);
			validateCustomerNumber(prefix, digits);
			validateAreaId(area_id);
		}
		catch(CustomersException e)
		{
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
		finally 
		{
			this.cus_id=cus_id;
			address=add;
			fName=firstName; lName=lastName;
			number = prefix+digits;
			areaId=area_id;
		}
	}
	
	public int getId() 
	{
		return cus_id;
	}
	public String getfName() 
	{
		return fName;
	}
	public String getlName() 
	{
		return lName;
	}
	public String getNumber()
	{
		return number;
	}
	public String getAddress()
	{
		return address;
	}
	public int getAreaId() 
	{
		return areaId;
	}

	public static boolean validateCustomerName(String firstName, String lastName) throws CustomersException
	{
		/*  
		 	Check input for invalid character sequences.
		 	A customers name should only contain letters from A-Z.
		*/
		Matcher matcher;
		String joinName = firstName+lastName;
		String[] checkName;
		checkName = joinName.split("");
		Pattern p = Pattern.compile("[a-zA-Z]");
		boolean b = false;
		boolean isValid = true;
		
		for(int j=0; j<checkName.length; j++)
		{				
			matcher = p.matcher(checkName[j]);
			b = matcher.matches();				
			if(b==false)
			{
				isValid = false;
				break;
			}
			else
				isValid = true;
		}
		if(isValid==false)
			throw new CustomersException("Invalid Character Detected");
		else if(firstName.length()<3)
			throw new CustomersException("FirstName length too short.");
		else if(firstName.length()>25)
			throw new CustomersException("FirstName Length too long.");
		else if(lastName.length()<3)
			throw new CustomersException("LastName length too short.");
		else if(lastName.length()>25)
			throw new CustomersException("LastName length too long.");
		else
			return isValid;
	}
	
	public static boolean validateCustomerId(int id) throws CustomersException
	{
		if(id < 1)
			throw new CustomersException("Id less than 1.");
		else if(id>10000)
			throw new CustomersException("Id greater than 10000");
		else
			return true;
	}
	public static boolean validateCustomerNumber(String prefix, String digits) throws CustomersException
	{	
		boolean pass = false;
		boolean res = false;
		String[] networks = {"083", "085", "087", "089"};
		String[] invalidChars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "-", "+", ",", "."};
		String[] digs = digits.split("");
		
		if(prefix.length()>3||prefix.length()<3)
			throw new CustomersException("Prefix in wrong format.");
		else if(digits.length()>7||digits.length()<7)
			throw new CustomersException("Digits in incorrect format.");
		else if(prefix.length()==3&&digits.length()==7)
		{		
			for(int i=0; i<invalidChars.length; i++)	
				for(int j=0; j<digs.length; j++)
					if(invalidChars[i].equalsIgnoreCase(digs[j]))
						throw new CustomersException("Inavlid Character Detected.");
				
			for(int i=0; i<networks.length; i++)
				if(prefix.equals(networks[i]))
					pass=true;
	
			if(pass)
				res=true;	
			else
				throw new CustomersException("Invalid or unknown network provider.");		
		}
		return res;
	}
	public static boolean validateCustomerAddress(String address) throws CustomersException
	{
		if(address.length()<4)
		{
			throw new CustomersException("Address too short");
		}
		else if(address.length()>30)
		{
			throw new CustomersException("Address too long");
		}
		else
		{
			return true;
		}
	}
	public static boolean validateAreaId(int id) throws CustomersException
	{
		if(id<1)
			throw new CustomersException("Area Id can't be less than 1");
		else if(id>200)
			throw new CustomersException("Area Id can't be greather than 200");
		else
			return true;
	}
}



