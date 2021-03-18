package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import exceptions.CustomersException;
import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.PublicationException;
import model.Customers;
import model.DeliveryArea;
import model.DeliveryDocket;
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
			//Change password, port and sql connector to run on your machine. 
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
	
	//Publication Access
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
			pstmt = con.prepareStatement("update publication set publicationName= ?, price_in_€ = ? where publication_order_id = ? ");
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
	public ResultSet retrieveAllPublications() throws PublicationException
	{
		try 
		{
			rs = stmt.executeQuery("select * from publication");
		} 
		catch (Exception e) 
		{
			rs = null;
			throw new PublicationException("Date is not retrieved.");
		}
		return rs;
	}
	
	//Customer Accesss
	public boolean insertCustomerInfo(Customers cus) throws CustomersException
	{
		boolean insertSucessfull = true;	
		try
		{
			pstmt = con.prepareStatement("INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?, ?)");
			pstmt.setInt(1, cus.getId());
			pstmt.setString(2, cus.getfName());
			pstmt.setString(3, cus.getlName());
			pstmt.setString(4, cus.getNumber());
			pstmt.executeUpdate();
		}
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new CustomersException("Failed to insert customers information into database");
		}
		return insertSucessfull;
	}
	public boolean deleteCustomerById(int cus_id) throws CustomersException
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
		catch (Exception e) 
		{
			deleteSucessfull = false;
			throw new CustomersException("Failed to delete customers information from database");
		}
		return deleteSucessfull;
	}
	public ResultSet displayCustomers() throws CustomersException
	{	
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM CUSTOMERS");
		}
		catch (Exception e) 
		{
			rs = null;
			throw new CustomersException("Failed to retrieve customers information from database");
		}
		return rs;
	}
	public boolean updateCustomerDetails(String add, String fn, String ln, String num, int id)
	{
		boolean updateSuccesful = false;
		try
		{
			String cmd = "update customers set" + " address='" +add+ "', firstName= '" +fn
					+ "', lastName= '" +ln+ "', mobileNumber= '" +num+ "' where customer_id=" + id;
			
			stmt.executeUpdate(cmd);
			updateSuccesful=true;
		}
		catch(Exception e)
		{
			updateSuccesful=false;
			System.out.println("Failed to update: " + e.getMessage());
		}
		return updateSuccesful;
	}
	
	//Delivery Area Access
	public boolean insertNewDeliveryArea(DeliveryArea da) throws DeliveryAreaException
	{
		boolean insertSucessfull = true;
		try 
		{

			pstmt = con.prepareStatement("insert into delivery_areas  values (default , ?, ?);");
			pstmt.setString(1, da.getName());
			pstmt.setInt(2, da.getSize());
			pstmt.execute();

		} 
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new DeliveryAreaException("DeliveryArea is not added.");
		}
		return insertSucessfull;
	}
	public boolean updateDeliveryArea(DeliveryArea da) throws DeliveryAreaException
	{
		boolean update = true;
		try 
		{
			pstmt = con.prepareStatement("update delivery_areas set AName= ?, size = ? where id = ? ");
			pstmt.setString(1, da.getName());
			pstmt.setInt(2, da.getSize());
			pstmt.setInt(3, da.getAreaId());
			pstmt.execute();

		} 
		catch (Exception e) 
		{
			update = false;
			throw new DeliveryAreaException("DeliveryAreaE is not updated.");
		}
		return update;
	}

	public boolean delete(DeliveryArea da) throws DeliveryAreaException 
	{
		boolean delete = true;
		try 
		{
			pstmt = con.prepareStatement("delete from delivery_areas where id = ?");
			pstmt.setInt(1, da.getAreaId());
			pstmt.execute();
		}
		catch (Exception e)
		{
			delete = false;
			throw new DeliveryAreaException("DeliveryArea is not deleted.");
		}
		return delete;
	}
	public ResultSet retrieveAllDeliveryArea() throws DeliveryAreaException 
	{
		try 
		{
			rs = stmt.executeQuery("select * from delivery_areas");
		}
		catch (Exception e)
		{
			rs = null;
			throw new DeliveryAreaException("DeliveryAreas is not retrieved.");
		}
		return rs;
	}
	
	//Delivery Docket Access
	public boolean updateDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException 
	{
		boolean updateSuccessful = true;

		try 
		{
			pstmt = con.prepareStatement(
					"UPDATE DELIVERY_DOCKETS SET publicationID = ?, deliveryAreaID = ?, customerID = ? WHERE deliveryDocketID = ? ");
			pstmt.setInt(1, deliveryDocket.getPublicationID());
			pstmt.setInt(2, deliveryDocket.getDeliveryAreaID());
			pstmt.setInt(3, deliveryDocket.getCustomerID());
			pstmt.setInt(4, deliveryDocket.getDeliveryDocketID());
			pstmt.execute();

		} 
		catch (Exception exception)
		{
			updateSuccessful = false;
			throw new DeliveryDocketException("Delivery Docket not updated");
		}
		return updateSuccessful;
	}
	public boolean deleteDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException 
	{
		boolean deleteSuccessful = true;
		try 
		{
			pstmt = con.prepareStatement("DELETE FROM DELIVERY_DOCKETS WHERE publicationID = ?");
			pstmt.setInt(1, deliveryDocket.getDeliveryDocketID());
			pstmt.execute();

		} 
		catch (Exception exception)
		{
			deleteSuccessful = false;
			throw new DeliveryDocketException("Delivery Docket not deleted");
		}
		return deleteSuccessful;
	}
	public ResultSet retrieveAllDeliveryDockets() throws DeliveryDocketException
	{
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM DELIVERY_DOCKETS");
		} 
		catch (Exception exception)
		{
			rs = null;
			throw new DeliveryDocketException("Delivery Dockets not retrieved");
		}
		return rs;
	}
}
