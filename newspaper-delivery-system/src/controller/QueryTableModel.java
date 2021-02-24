package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Address;
import model.Customers;

public class QueryTableModel 
{
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	
	private String user = "root";
	private String password = "#Lekoso00";
		
	public void openConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:6969/CUSTOMERS";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Failed to connect: " + e.getMessage());
		}
	}
	public void closeConnection()
	{
		try 
		{
			stmt.close();
			con.close();
			System.out.println("Conn Closed\n");
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public boolean insertCustomerInfo(Customers cus) 
	{
		boolean insertSucessfull = true;	
		try
		{
			pstmt = con.prepareStatement("INSERT INTO CUSTOMERS VALUES (DEFAULT, ?, ?, ?, ?)");
			pstmt.setInt(1, cus.getId());
			pstmt.setString(2, cus.getfName());
			pstmt.setString(3, cus.getlName());
			pstmt.setString(4, cus.getNumber());
			pstmt.executeUpdate();
		}
		catch (Exception e) 
		{
			insertSucessfull = false;
			System.out.println("Failed to insert: " + e.getMessage());
		}
		return insertSucessfull;
	}
	public boolean deleteCustomerById(int cus_id)
	{
		boolean deleteSucessfull = true;
		try 
		{
			if (cus_id == -99)
				pstmt = con.prepareStatement("DELETE FROM CUSTOMERS");
			else
				pstmt = con.prepareStatement("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=" + cus_id);
			pstmt.executeUpdate();
		}
		catch (Exception e) {deleteSucessfull = false;}
		return deleteSucessfull;
	}
	
	public boolean insertAddressInfo(Address add)
	{
		boolean insertSucessfull = true;	
		try
		{
			pstmt = con.prepareStatement("INSERT INTO ADDRESS VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, add.getAddress_id());
			pstmt.setInt(2, add.getNumber());
			pstmt.setString(3, add.getStreet());
			pstmt.setString(4, add.getTown());
			pstmt.setString(5, add.getCity());
			pstmt.setString(6, add.getCountry());
			pstmt.executeUpdate();
		}
		catch (Exception e) 
		{
			insertSucessfull = false;
			System.out.println("Failed to insert: " + e.getMessage());
		}
		return insertSucessfull;
	}
	public boolean deleteAddressById(int id)
	{
		return false;
	}
	
	public ResultSet displayCustomers() 
	{	
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM CUSTOMERS");
		}
		catch (Exception e) 
		{
			rs = null;
		}
		return rs;
	}
	public ResultSet displayAddress() 
	{	
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM ADDRESS");
		}
		catch (Exception e) 
		{
			rs = null;
		}
		return rs;
	}
	public ResultSet joinCustomerAddress()
	{
		try 
		{
			rs = stmt.executeQuery("SELECT customer_id, concat(firstName, \"  \",  lastName) as Name, houseNumber, street, town, city, country\r\n"
					+ "FROM CUSTOMERS INNER JOIN ADDRESS ON CUSTOMERS.ADDRESS_ID = ADDRESS.ADDRESS_ID;");
		}
		catch (Exception e) 
		{
			rs = null;
		}
		return rs;
	}
}
