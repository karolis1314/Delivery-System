package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.PublicationException;
import model.Customers;
import model.Publication;

public class QueryTableModel 
{
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs;
	
	private String user = "root";
	private String password = "#Lekoso00";
		
	public boolean openConnection()
	{
		boolean success = false;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:6969/newsagent2021";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			success=true;
		}
		catch(Exception e)
		{
			System.out.println("Failed to connect: " + e.getMessage());
		}
		return success;
	}
	public boolean closeConnection()
	{
		boolean success = false;
		try 
		{
			stmt.close();
			con.close();
			System.out.println("Conn Closed\n");
			success=true;
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return success;
	}
	
	//Insert the publication
	public boolean insertNewPublication(Publication p) throws PublicationException 
	{
		boolean insertSucessfull = true;
		try
		{
			pstmt = con.prepareStatement("insert into publication (publication_order_id, publicationName, price_in_€)" + "values (?, ?, ?)");
			pstmt.setString(1, p.getOrder_id());
			pstmt.setString(2, p.getName());
			pstmt.setDouble(3, p.getPrice());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new PublicationException("Publication is not added.");
		}
		return insertSucessfull;
	}
	public boolean updatePublication(Publication p) throws PublicationException 
	{
		boolean update = true;
		try 
		{
			pstmt = con.prepareStatement("update publication set publicationName= ?, price_in_ï¿½ = ? where publication_order_id = ? ");
			pstmt.setString(1, p.getName());
			pstmt.setDouble(2, p.getPrice());
			pstmt.setString(3, p.getOrder_id());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			update = false;
			throw new PublicationException("Publication is not updated.");
		}
		return update;
	}
	public boolean delete(Publication p) throws PublicationException 
	{
		boolean delete = true;
		try 
		{
			pstmt = con.prepareStatement("delete from publication where publication_order_id= ?");
			pstmt.setString(1, p.getOrder_id());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			delete = false;
			throw new PublicationException("Publication is not deleted.");
		}
		return delete;
	}
	
	//Customer Accesss
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
}
