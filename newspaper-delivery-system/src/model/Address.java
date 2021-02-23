package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.CustomersException;

public class Address 
{
	private int address_id, number;
	private String street, town, county, country;
	
	public Address(int add_id, int num, String street, String town, String county, String country) throws CustomersException
	{
		try
		{
			validateCustomerAddress(add_id, num, street, town, county, country);
		}
		catch (Exception e) 
		{
			System.out.println("\n"+e.getMessage());
			System.out.println();
			throw e;
		}
		address_id = add_id;
		number=num;
		this.street = street;
		this.town=town;
		this.county=county;
		this.country=country;
	}
	
	public int getAddress_id() {
		return address_id;
	}

	public int getNumber() {
		return number;
	}

	public String getStreet() {
		return street;
	}

	public String getTown() {
		return town;
	}

	public String getCity() {
		return county;
	}

	public String getCountry() {
		return country;
	}

	public static boolean validateCustomerAddress(int add_id, int number, String street, String town, String county, String country) throws CustomersException
	{
		Pattern p = Pattern.compile("[a-zA-Z ]");
		Matcher matcher;
		String join = street+town+county+country;
		String[] checkInvalid = join.split("");
		
		boolean c;
		boolean isValid = false;
		
		if (add_id<1 || add_id>10000)
			throw new CustomersException("Id not in range 1-10000");
		else if(number<1 || number>200)
			throw new CustomersException("Number not in range 1 - 200.");
		else if(street.length()<4 || town.length()<4 || county.length()<4 ||country.length()<4)
			throw new CustomersException("Field in address too short.");
		else if(street.length()>30 || town.length()>30 || county.length()>30 ||country.length()>7)
			throw new CustomersException("Field in address too long.");
		else
		{
			for(int a=0; a<checkInvalid.length; a++)
			{				
				matcher = p.matcher(checkInvalid[a]);
				c = matcher.matches();				
				if(c==false)
				{
					isValid = false;
					break;
				}
				else
					isValid = true;
			}
			if(isValid==false)
				throw new CustomersException("Invalid character detected in address field.");
			else
				return isValid;
		}
	}
	
	public static boolean validateAddressId(int add_id) throws CustomersException
	{
		throw new CustomersException("No tests");
	}
	
	public static boolean validateAddressNumber(int number) throws CustomersException
	{
		throw new CustomersException("No tests");
	}
}