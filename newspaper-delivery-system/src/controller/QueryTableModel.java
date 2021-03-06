package controller;

import exceptions.*;
import model.*;

import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QueryTableModel
{
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet;

	private String user = "root";
	private String password = "";

	public QueryTableModel() {
	}

	public QueryTableModel(String password) {
		this.password = password;
	}

	public boolean openConnection() {
		boolean success = false;
		try 
		{
			// Change password, port and sql connector to run on your machine.
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newsagent2021?serverTimezone=GMT";
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
			success = true;
		} catch (Exception e) {
			success = false;
			System.out.println("Failed to connect: " + e.getMessage());
		}
		return success;
	}

	public boolean closeConnection() {
		boolean success = false;
		try
		{
			statement.close();
			connect.close();
			System.out.println("Conn Closed\n");
			success = true;
		} catch (Exception e) {
			return false;
		}
		return success;
	}

	// Insert the publication
	public boolean insertNewPublication(Publication p) throws PublicationException {
		boolean insertSucessfull = true;
		try {
			preparedStatement = connect
					.prepareStatement("insert into publication (id, frequencyInDays, publicationName, priceInEuro, stock)"
							+ "values (DEFAULT, ?, ?, ?, ?)");
			preparedStatement.setString(1, p.getFrequencyInDays());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setDouble(3, p.getPrice());
			preparedStatement.setInt(4, p.getStock());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}
		return insertSucessfull;
	}

	// Insert Staff
	public boolean insertNewStaff(StaffMember s) throws StaffException {
		boolean insertSucessfull = true;
		try {
			preparedStatement = connect.prepareStatement(
					"insert into STAFF_MEMBER (staffID, firstName, lastName, staffPassword, areaID)" + "values (DEFAULT,?, ?, ?, ?)");
			preparedStatement.setString(1, s.getfName());
			preparedStatement.setString(2, s.getlName());
			preparedStatement.setString(3, s.getPassword());
			preparedStatement.setInt(4, s.getAreaId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}
		return insertSucessfull;
	}

	public boolean updateStaff(StaffMember s) throws StaffException {
		boolean update = true;
		try {
			preparedStatement = connect.prepareStatement(
					"update STAFF_MEMBER set firstName= ?, lastName = ?, staffPassword = ?, areaID = ? where staffID = ? ");
			preparedStatement.setString(1, s.getfName());
			preparedStatement.setString(2, s.getlName());
			preparedStatement.setString(3, s.getPassword());
			preparedStatement.setInt(4, s.getAreaId());
			preparedStatement.setInt(5, s.getStaffId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}
		return update;
	}

	public boolean updatePublication(Publication p) throws PublicationException {

		boolean update = true;

		try {

			preparedStatement = connect.prepareStatement(
					"update publication set frequencyInDays= ?, publicationName= ?, priceInEuro = ?, stock = ? where id = ? ");
			preparedStatement.setString(1, p.getFrequencyInDays());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setDouble(3, p.getPrice());
			preparedStatement.setInt(4, p.getStock());
			preparedStatement.setInt(5, p.getId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}

		return update;

	}

	public boolean deletePublication(Publication p) throws PublicationException {
		boolean delete = true;
		try {

			preparedStatement = connect.prepareStatement("delete from publication where id= ?");
			preparedStatement.setInt(1, p.getId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}

		return delete;

	}

	public boolean deleteStaff(StaffMember s) throws StaffException {

		boolean delete = true;

		try {

			preparedStatement = connect.prepareStatement("delete from STAFF_MEMBER where staffID= ?");
			preparedStatement.setInt(1, s.getStaffId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}

		return delete;

	}

	public ResultSet retrieveAllPublications() throws PublicationException {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from publication");

		} catch (Exception e) {
			resultSet = null;
			throw new PublicationException("Publication is not retrieved.");
		}
		return resultSet;
	}

	public ResultSet retrieveAllStaff() throws StaffException {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from STAFF_MEMBER");

		} catch (Exception e) {
			resultSet = null;
			throw new StaffException("Staff is not retrieved.");
		}
		return resultSet;
	}

	public boolean insertNewDeliveryArea(DeliveryArea da) throws DeliveryAreaException {

		boolean insertSucessfull = true;

		try {

			preparedStatement = connect.prepareStatement("insert into delivery_areas  values (default , ?);");
			preparedStatement.setString(1, da.getName());
			preparedStatement.execute();

		} catch (Exception e) {
			return  false;
		}
		return insertSucessfull;
	}

	public boolean updateDeliveryArea(DeliveryArea da) throws DeliveryAreaException {

		boolean update = true;

		try {

			preparedStatement = connect.prepareStatement("update delivery_areas set AreaName= ? where id = ? ");
			preparedStatement.setString(1, da.getName());
			preparedStatement.setInt(2, da.getAreaId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}

		return update;

	}

	public boolean deleteDeliveryArea(DeliveryArea da) throws DeliveryAreaException {

		boolean delete = true;

		try {

			preparedStatement = connect.prepareStatement("delete from delivery_areas where id = ?");
			preparedStatement.setInt(1, da.getAreaId());
			preparedStatement.execute();

		} catch (Exception e) {
			return false;
		}

		return delete;

	}

	public ResultSet retrieveAllDeliveryArea() throws DeliveryAreaException {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from delivery_areas");

		} catch (Exception e) {
			resultSet = null;
			throw new DeliveryAreaException("DeliveryAreas is not retrieved.");
		}
		return resultSet;
	}

	// Delivery Docket

	public boolean createDailyDeliveryDocket() {
		boolean gotDailyPublication = false;

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(
					"select customerID, publicationID from orders inner join publication on orders.publicationID = publication.id where isActive = true and frequencyInDays = '1'");
			while (resultSet.next()) {
				int customerID = resultSet.getInt(1);
				int publicationID = resultSet.getInt(2);
				statement = connect.createStatement();
				statement.executeUpdate("insert into Delivery_Dockets values (default, default, " + customerID + ","
						+ publicationID + ", default );");
			}
			gotDailyPublication = true;
		} catch (Exception e) {
			return false;
		}
		return gotDailyPublication;
	}

	public boolean createWeeklyDeliveryDocket() {
		boolean gotWeekly=false;
		try
		{
			statement = connect.createStatement();
			resultSet = statement.executeQuery(
					"select customerID, publicationID from orders inner join publication on orders.publicationID = publication.id where isActive = true and frequencyInDays = '7'");
			
			while (resultSet.next()) 
			{
				int customerID = resultSet.getInt(1);
				int publicationID = resultSet.getInt(2);
				statement = connect.createStatement();
				statement.executeUpdate("insert into Delivery_Dockets values (default, default, " + customerID + ","
						+ publicationID + ", default );");
			
			}
			gotWeekly=true;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return gotWeekly;
	}

	public boolean createBiWeeklyDeliveryDocket() {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(
					"select customerID, publicationID from orders inner join publication on orders.publicationID = publication.id where isActive = true and frequencyInDays = '14'");
			while (resultSet.next()) {
				int customerID = resultSet.getInt(1);
				int publicationID = resultSet.getInt(2);
				statement = connect.createStatement();
				statement.executeUpdate("insert into Delivery_Dockets values (default, default, " + customerID + ","
						+ publicationID + ", default );");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean createMonthlyDeliveryDocket() {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(
					"select customerID, publicationID from orders inner join publication on orders.publicationID = publication.id where isActive = true and frequencyInDays = '30'");

			while (resultSet.next()) {
				int customerID = resultSet.getInt(1);
				int publicationID = resultSet.getInt(2);
				statement = connect.createStatement();
				statement.executeUpdate("insert into Delivery_Dockets values (default, default, " + customerID + ","
						+ publicationID + ", default );");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

//	public boolean deleteDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException {
//
//		boolean deleteSuccessful = true;
//
//		try {
//			preparedStatement = connect.prepareStatement("DELETE FROM DELIVERY_DOCKETS WHERE deliveryDocketID = ?");
//			preparedStatement.setInt(1, deliveryDocket.getDeliveryDocketID());
//			preparedStatement.execute();
//
//		} catch (Exception exception) {
//			deleteSuccessful = false;
//			throw new DeliveryDocketException("Delivery Docket not deleted");
//		}
//
//		return deleteSuccessful;
//	}

	public ResultSet retrieveAllDeliveryDockets() throws DeliveryDocketException {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM DELIVERY_DOCKETS");

		} catch (Exception exception) {
			resultSet = null;
			throw new DeliveryDocketException("Delivery Dockets not retrieved");
		}
		return resultSet;
	}

	//Orders DBAO
	public ResultSet displayOrders() throws OrdersException
	{
		try 
		{
			resultSet = statement.executeQuery("SELECT * FROM ORDERS");
		}
		catch (Exception e) 
		{
			resultSet = null;
			throw new OrdersException("Failed to display orders: ");
		}
		return resultSet;
	}
	public boolean insertOrder(Orders order) throws OrdersException
	{
		boolean insertSucessfull = true;	
		try
		{
			preparedStatement = connect.prepareStatement("INSERT INTO ORDERS VALUES (?, ?, ?)");
			preparedStatement.setInt(1, order.getCustomerId());
			preparedStatement.setInt(2, order.getPublicationId());
			preparedStatement.setBoolean(3, order.isActive());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			return false;
		}
		return insertSucessfull;
	}

//	public boolean deleteOrder(int id) throws OrdersException
//	{
//		boolean deleteSuccess=true;
//		try
//		{
//			preparedStatement = connect.prepareStatement("DELETE FROM ORDERS WHERE isACTIVE=" + active);
//			preparedStatement.executeUpdate();
//		}
//		catch (Exception e)
//		{
//			return false;
//		}
//		return deleteSuccess;
//	}
//	public boolean updateOrder(Orders order) throws OrdersException
//	{
//		boolean updateSuccess=true;
//		try
//		{
//			preparedStatement =  connect.prepareStatement("UPDATE ORDERS SET isACTIVE=?, PUBLICATIONID=? WHERE CUSTOMERID=?");
//			preparedStatement.setBoolean(1, order.isActive());
//			preparedStatement.setInt(2, order.getPublicationId());
//			preparedStatement.setInt(3, order.getCustomerId());
//		}
//		catch (Exception e)
//		{
//			updateSuccess=false;
//			throw new OrdersException("Failed to update order: " + e.getMessage());
//		}
//		return updateSuccess;
//	}
//
	// Customers DBAO
	public boolean insertCustomerInfo(Customers cus) throws CustomersException {
		boolean insertSucessfull = true;
		try 
		{
			preparedStatement = connect.prepareStatement("INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, cus.getId());
			preparedStatement.setString(2, cus.getAddress());
			preparedStatement.setString(3, cus.getfName());
			preparedStatement.setString(4, cus.getlName());
			preparedStatement.setString(5, cus.getNumber());
			preparedStatement.setInt(6, cus.getAreaId());
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			insertSucessfull = false;
			throw new CustomersException(e.getMessage());
		}
		return insertSucessfull;
	}

	public boolean deleteCustomerById(int id) throws CustomersException 
	{
		boolean deleteSuccessful = true;
		try 
		{
			preparedStatement = connect.prepareStatement("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} 
		catch (Exception e) 
		{
			deleteSuccessful = false;
			throw new CustomersException(e.getMessage());
		}
		return deleteSuccessful;
	}
	public ResultSet displayCustomers() throws CustomersException 
	{
		try 
		{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM CUSTOMERS");
		} 
		catch (Exception e) 
		{
			resultSet = null;
			throw new CustomersException("Failed to display Customers");
		}
		return resultSet;
	}
	public boolean updateCustomerDetails(Customers cus) throws CustomersException 
	{
		boolean updateSuccesful = false;
		try 
		{
			preparedStatement = connect.prepareStatement(
					"UPDATE CUSTOMERS SET ADDRESS=?, FIRSTNAME=?, LASTNAME=?, MOBILENUMBER=?, areaID=?  WHERE customer_id =?");
			preparedStatement.setString(1, cus.getAddress());
			preparedStatement.setString(2, cus.getfName());
			preparedStatement.setString(3, cus.getlName());
			preparedStatement.setString(4, cus.getNumber());
			preparedStatement.setInt(5, cus.getAreaId());
			preparedStatement.setInt(6, cus.getId());
			preparedStatement.execute();
			updateSuccesful = true;
		}
		catch (Exception e) 
		{
			return false;
		}
		return updateSuccesful;
	}

	public void createInvoice() throws Exception{
		List<Integer> IDs =  new ArrayList<>();
		String row =  "";
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM ");
		String dateAsString = (String) ft.format(date);



		statement = connect.createStatement();
		resultSet = statement.executeQuery(
				"select customer_id from customers");

		while (resultSet.next()) {
			int customerID = resultSet.getInt(1);
			IDs.add(customerID);
			}

		for (Integer custID: IDs) {
			resultSet = statement.executeQuery("select * from orders5 where customer_id = " + custID);
			while(resultSet.next()){
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String pubName = resultSet.getString(4);
				String price = resultSet.getString(5);
				String dateDelivered = resultSet.getString(6);
				row = pubName + "  " + dateDelivered + "   " + price + "\n";
				FileOutputStream file = new FileOutputStream("invoice/" + firstName + " " + lastName + " " + dateAsString +".txt " ,true);
				byte rowAsBytes[] = row.getBytes();
				file.write(rowAsBytes);
				file.close();
			}
		}
	}
}