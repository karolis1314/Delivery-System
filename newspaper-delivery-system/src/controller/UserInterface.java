package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.CustomersException;
import model.Address;
import model.Customers;

public class UserInterface
{
	boolean running = true;
	private QueryTableModel custable = new QueryTableModel();

	public UserInterface() 
	{
		try
		{
			custable.openConnection();
			Scanner input = new Scanner(System.in);
			while(running) 
			{
				userMenuOptions();
				int choice = input.nextInt();
				
				if(choice==1)
					addAddress();
				else if(choice==2)
					addCustomer();
				else if(choice==3)
				{
					ResultSet rs = custable.displayCustomers();
					printCustomerTable(rs);
					Thread.sleep(5000);
				}
				else if(choice==4)
				{
					ResultSet rs = custable.displayAddress();
					printAddressTable(rs);
					Thread.sleep(5000);
				}
				else if(choice==5)
					deleteCustomer();
				else if(choice==6)
					deleteAddress();
				else if(choice==7)
				{
					ResultSet rs = custable.joinCustomerAddress();
					printCustomerAddress(rs);
					Thread.sleep(5000);
				}
				else
				{
					custable.closeConnection();
					System.exit(0);
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void addCustomer()
	{
		int cus_id;
		String firstName, lastName;
		String number;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		try
		{
			System.out.println();
			System.out.print("Enter address id and press enter: ");cus_id=kb.nextInt();
			System.out.print("Enter customer firstname and press enter: ");firstName=kb.next();
			System.out.print("Enter customer lastname and press enter: ");lastName=kb.next();
			System.out.print("Enter customer number and press enter: ");number=kb.next();
			System.out.println();
			try
			{
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				Customers cus = new Customers(cus_id, firstName, lastName, prefix, digits);
				boolean res = custable.insertCustomerInfo(cus);
				if(res==true)
					System.out.println("\nDetails Saved\n");
			}
			catch(CustomersException e) {System.out.println("ERROR: Customer Details NOT Saved: " + e.getMessage()+"\n");};
		}
		catch(InputMismatchException e)
		{
			addCustomer();
		}
	}
	public void deleteCustomer()
	{
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.print("\nEnter Customer Id to be deleted or -99 to remove all customers: ");
		int deleteCustId = kb.nextInt();
		boolean deleteResult = custable.deleteCustomerById(deleteCustId);
		if ((deleteResult == true) && (deleteCustId == -99))
			System.out.println("\nCustomer Table Emptied");
		else if (deleteResult == true)
			System.out.println("\nCustomer Deleted");
		else 
			System.out.println("\nERROR: Customer Details NOT Deleted or Do Not Exist");
	}
	
	
	public void addAddress()
	{
		int address_id, houseNumber;
		String street, town, city, country;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		try
		{
			System.out.println();
			System.out.print("Street: ");street=kb.nextLine();
			System.out.print("Town: ");town=kb.next();
			System.out.print("City: ");city=kb.next();
			System.out.print("Country: ");country=kb.next();
			System.out.print("House number: ");houseNumber=kb.nextInt();
			System.out.print("Address_id: ");address_id=kb.nextInt();
			try
			{
				Address address = new Address(address_id, houseNumber, street, town, city, country);
				boolean res = custable.insertAddressInfo(address);
				if(res==true)
					System.out.println("\nAddress Details Saved\n");
			}
			catch(CustomersException e) {System.out.println("ERROR: Address details NOT saved: " + e.getMessage()+"\n");};
		}
		catch(InputMismatchException e)
		{
			addAddress();
		}
	}
	public void deleteAddress()
	{
		
	}
	
	public boolean printCustomerTable(ResultSet rs)
	{
		try
		{
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount(); 
			
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
			for(int h=1; h<=colCount; h++)
				System.out.printf("%20s",meta.getColumnName(h));
			System.out.println("\n");
			while(rs.next())
			{	
				int id = rs.getInt("customer_id");
				String add_id = rs.getString("address_id");
				String fName = rs.getString("firstName");
				String lName = rs.getString("lastName");
				String number = rs.getString("mobileNumber");
				System.out.printf("%20s%20s%20s%20s%20s", id, add_id, fName, lName, number);
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------\n");
		}
		catch(Exception e) 
		{System.out.println(e.getMessage());} 
		return true;
	}
	
	public boolean printAddressTable(ResultSet rs)
	{
		try
		{
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount(); 
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(int h=1; h<=colCount; h++)
				System.out.printf("%20s",meta.getColumnName(h));
			System.out.println("\n");
			while(rs.next())
			{	
				int id = rs.getInt("address_id");
				int number = rs.getInt("houseNumber");
				String street = rs.getString("street");
				String town = rs.getString("town");
				String city = rs.getString("city");
				String country = rs.getString("country");
				System.out.printf("%20s%20s%20s%20s%20s%20s", id, number, street, town, city, country);
				System.out.println();
			}
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		}
		catch(Exception e) 
		{System.out.println(e.getMessage());} 
		return true;
	}
	
	public boolean printCustomerAddress(ResultSet rs)
	{
		try
		{
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount(); 
			
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(int h=1; h<=colCount; h++)
				System.out.printf("%20s",meta.getColumnName(h));
			System.out.println("\n");			
			while(rs.next())
			{	
				int id = rs.getInt("customer_id");
				String fname=rs.getString("Name");
				int number = rs.getInt("houseNumber");
				String street = rs.getString("street");
				String town = rs.getString("town");
				String city = rs.getString("city");
				String country = rs.getString("country");
				System.out.printf("%20s%20s%20s%20s%20s%20s%20s", id, fname, number, street, town, city, country);
				System.out.println();
			}
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		}
		catch(Exception e) 
		{System.out.println(e.getMessage());} 
		return true;
	}
	
	public void userMenuOptions()
	{	
		System.out.println("-Please select an option from one of the following:\n");
		System.out.println("-To add a new customer address to table enter 1");
		System.out.println("-To add a new customer to table enter 2");
		System.out.println("-To display list of customers from table enter 3");
		System.out.println("-To display list of addresses from table enter 4");
		System.out.println("-To delete a customer from table enter 5");
		System.out.println("-To delete a customer address from table enter 6");
		System.out.println("-To view customers and address table enter 7");
		System.out.println("-To exit this session enter -1");
		System.out.println("");
		System.out.print(">>>>>>>> ");
	}
	
	public static void main(String[] args)
	{
		new UserInterface();
	}
}
