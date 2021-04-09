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
import exceptions.OrdersException;
import exceptions.PublicationException;
import model.Customers;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Orders;
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
			String url = "jdbc:mysql://localhost:6969/newsagent2021?serverTimezone=GMT";
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			success=true;
		}
		catch(Exception e)
		{
			success=false;
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
			success=false;
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
			pstmt = con.prepareStatement("insert into publication (publication_order_id, publicationName, price_in_euro)" + "values (?, ?, ?)");
			pstmt.setString(1, p.getOrder_id());
			pstmt.setString(2, p.getName());
			pstmt.setDouble(3, p.getPrice());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new PublicationException(e.getMessage());
		}
		return insertSucessfull;
	}
	public boolean updatePublication(Publication p) throws PublicationException 
	{
		boolean update = true;
		try 
		{
			pstmt = con.prepareStatement("update publication set publicationName= ?, price_in_� = ? where publication_order_id = ? ");
			pstmt.setString(1, p.getName());
			pstmt.setDouble(2, p.getPrice());
			pstmt.setString(3, p.getOrder_id());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			update = false;
			throw new PublicationException(e.getMessage());
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
			throw new PublicationException(e.getMessage());
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
			throw new PublicationException(e.getMessage());
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
			pstmt.setString(2, cus.getAddress());
			pstmt.setString(3, cus.getfName());
			pstmt.setString(4, cus.getlName());
			pstmt.setString(5, cus.getNumber());
			pstmt.executeUpdate();
		}
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new CustomersException("Failed to insert customer details: " + e.getMessage());
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
			throw new CustomersException("Failed to delete customer details: " + e.getMessage());
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
			throw new CustomersException("Failed to display customers: " + e.getMessage());
		}
		return rs;
	}
	public boolean updateCustomerDetails(String add, String fn, String ln, String num, int id) throws CustomersException
	{
		boolean updateSuccesful = false;
		try
		{			
			pstmt = con.prepareStatement("UPDATE CUSTOMERS SET ADDRESS=?, FIRSTNAME=?, LASTNAME=?, MOBILENUMBER=? WHERE CUSTOMER_ID = ?");
			pstmt.setString(1, add);
			pstmt.setString(2, fn);
			pstmt.setString(3, ln);
			pstmt.setString(4, num);
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
			
			updateSuccesful=true;
		}
		catch(Exception e)
		{
			updateSuccesful=false;
			throw new CustomersException("Failed to update customer details: " + e.getMessage());
		}
		return updateSuccesful;
	}
	
	//Orders DBAO
	public ResultSet displayOrders() throws OrdersException
	{
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM ORDERS");
		}
		catch (Exception e) 
		{
			rs = null;
			throw new OrdersException("Failed to display orders: " + e.getMessage());
		}
		return rs;
	}
	public boolean insertOrder(Orders order) throws OrdersException
	{
		boolean insertSucessfull = true;	
		try
		{
			pstmt = con.prepareStatement("INSERT INTO ORDERS VALUES (?, ?, ?)");
			pstmt.setInt(1, order.getCustomerId());
			pstmt.setInt(2, order.getPublicationId());
			pstmt.setBoolean(3, order.isActive());
			pstmt.executeUpdate();
		}
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new OrdersException("Failed to insert order details: " + e.getMessage());
		}
		return insertSucessfull;
	}
	public boolean deleteOrder(boolean active) throws OrdersException
	{
		boolean deleteSuccess=true;
		try 
		{
			pstmt = con.prepareStatement("DELETE FROM ORDERS WHERE isACTIVE=" + active);
			pstmt.executeUpdate();
		}
		catch (Exception e)
		{
			deleteSuccess=false;
			throw new OrdersException("Failed to delete order: " + e.getMessage());
		}
		return deleteSuccess;
	}
	public boolean updateOrder(Orders order) throws OrdersException
	{
		boolean updateSuccess=true;
		try 
		{
			pstmt = con.prepareStatement("UPDATE ORDERS SET isACTIVE=?, PUBLICATIONID=? WHERE CUSTOMERID=?");
			pstmt.setBoolean(1, order.isActive());
			pstmt.setInt(2, order.getPublicationId());
			pstmt.setInt(3, order.getCustomerId());
		}
		catch (Exception e)
		{
			updateSuccess=false;
			throw new OrdersException("Failed to update order: " + e.getMessage());
		}
		return updateSuccess;
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
			throw new DeliveryAreaException(e.getMessage());
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
	public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException 
	{
		boolean insertSuccessful = true;
		try 
		{
			pstmt = con.prepareStatement(
					"INSERT INTO DELIVERY_DOCKETS (publicationID, deliveryAreaID, customerID)" + "values (?, ?, ?)");
			pstmt.setInt(1, deliveryDocket.getPublicationID());
			pstmt.setInt(2, deliveryDocket.getDeliveryAreaID());
			pstmt.setInt(3, deliveryDocket.getCustomerID());
			pstmt.execute();
		} 
		catch (Exception e) 
		{
			insertSuccessful = false;
			throw new DeliveryDocketException(e.getMessage());
		}
		return insertSuccessful;
	}
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
		catch (Exception e)
		{
			updateSuccessful = false;
			throw new DeliveryDocketException(e.getMessage());
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
		catch (Exception e)
		{
			deleteSuccessful = false;
			throw new DeliveryDocketException(e.getMessage());
		}
		return deleteSuccessful;
	}
	public ResultSet retrieveAllDeliveryDockets() throws DeliveryDocketException
	{
		try 
		{
			rs = stmt.executeQuery("SELECT * FROM DELIVERY_DOCKETS");
		} 
		catch (Exception e)
		{
			rs = null;
			throw new DeliveryDocketException(e.getMessage());
		}
		return rs;
	}
}
