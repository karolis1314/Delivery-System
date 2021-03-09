package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import exceptions.CustomersException;
import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.PublicationException;
import model.Customers;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Publication;

import java.sql.*;

public class MySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public MySQLAccess() throws Exception {

		try {
			connectToTheDatabase();
		} catch (Exception e) {
			System.out.print("Failed to connect DB Connection");

		}

	}

//Create the connection
	public boolean connectToTheDatabase() throws PublicationException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newsagent2021";
			connect = DriverManager.getConnection(url, "root", "admin");
			statement = connect.createStatement();
			System.out.println("Connection Made.");
			return true;

		} catch (Exception e) {
			throw new PublicationException("Connection failed.");
		}
	}

//Insert the publication
	public boolean insertNewPublication(Publication p) throws PublicationException {

		boolean insertSucessfull = true;

		try {

			preparedStatement = connect.prepareStatement(
					"insert into publication (publication_order_id, publicationName, price_in_�)" + "values (?, ?, ?)");
			preparedStatement.setString(1, p.getOrder_id());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setDouble(3, p.getPrice());
			preparedStatement.execute();

		} catch (Exception e) {
			insertSucessfull = false;
			throw new PublicationException("Publication is not added.");

		}

		return insertSucessfull;

	}

	public boolean updatePublication(Publication p) throws PublicationException {

		boolean update = true;

		try {

			preparedStatement = connect.prepareStatement(
					"update publication set publicationName= ?, price_in_� = ? where publication_order_id = ? ");
			preparedStatement.setString(1, p.getName());
			preparedStatement.setDouble(2, p.getPrice());
			preparedStatement.setString(3, p.getOrder_id());
			preparedStatement.execute();

		} catch (Exception e) {
			update = false;
			throw new PublicationException("Publication is not updated.");
		}

		return update;

	}

	public boolean delete(Publication p) throws PublicationException {

		boolean delete = true;

		try {

			preparedStatement = connect.prepareStatement("delete from publication where publication_order_id= ?");
			preparedStatement.setString(1, p.getOrder_id());
			preparedStatement.execute();

		} catch (Exception e) {
			delete = false;
			throw new PublicationException("Publication is not deleted.");
		}

		return delete;

	}

	public ResultSet retrieveAllPublications() throws PublicationException {

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from publication");

		} catch (Exception e) {
			resultSet = null;
			throw new PublicationException("Date is not retrieved.");
		}
		return resultSet;
	}

	public boolean insertNewDeliveryArea(DeliveryArea da) throws DeliveryAreaException {

		boolean insertSucessfull = true;

		try {

			preparedStatement = connect.prepareStatement("insert into delivery_areas  values (default , ?, ?);");
			preparedStatement.setString(1, da.getName());
			preparedStatement.setInt(2, da.getSize());
			preparedStatement.execute();

		} catch (Exception e) {
			insertSucessfull = false;
			throw new DeliveryAreaException("DeliveryArea is not added.");

		}
		return insertSucessfull;
	}

	public boolean updateDeliveryArea(DeliveryArea da) throws DeliveryAreaException {

		boolean update = true;

		try {

			preparedStatement = connect.prepareStatement("update delivery_areas set AName= ?, size = ? where id = ? ");
			preparedStatement.setString(1, da.getName());
			preparedStatement.setInt(2, da.getSize());
			preparedStatement.setInt(3, da.getAreaId());
			preparedStatement.execute();

		} catch (Exception e) {
			update = false;
			throw new DeliveryAreaException("DeliveryAreaE is not updated.");
		}

		return update;

	}

	public boolean delete(DeliveryArea da) throws DeliveryAreaException {

		boolean delete = true;

		try {

			preparedStatement = connect.prepareStatement("delete from delivery_areas where id = ?");
			preparedStatement.setInt(1, da.getAreaId());
			preparedStatement.execute();

		} catch (Exception e) {
			delete = false;
			throw new DeliveryAreaException("DeliveryArea is not deleted.");
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
	public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException {

		boolean insertSuccessful = true;

		try {
			preparedStatement = connect.prepareStatement(
					"INSERT INTO DELIVERY_DOCKETS (publicationID, deliveryAreaID, customerID)" + "values (?, ?, ?)");
			preparedStatement.setInt(1, deliveryDocket.getPublicationID());
			preparedStatement.setInt(2, deliveryDocket.getDeliveryAreaID());
			preparedStatement.setInt(3, deliveryDocket.getCustomerID());
			preparedStatement.execute();

		} catch (Exception exception) {
			insertSuccessful = false;
			throw new DeliveryDocketException("Delivery Docket not added");

		}

		return insertSuccessful;
	}

	public boolean updateDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException {

		boolean updateSuccessful = true;

		try {
			preparedStatement = connect.prepareStatement(
					"UPDATE DELIVERY_DOCKETS SET publicationID = ?, deliveryAreaID = ?, customerID = ? WHERE deliveryDocketID = ? ");
			preparedStatement.setInt(1, deliveryDocket.getPublicationID());
			preparedStatement.setInt(2, deliveryDocket.getDeliveryAreaID());
			preparedStatement.setInt(3, deliveryDocket.getCustomerID());
			preparedStatement.setInt(4, deliveryDocket.getDeliveryDocketID());
			preparedStatement.execute();

		} catch (Exception exception) {
			updateSuccessful = false;
			throw new DeliveryDocketException("Delivery Docket not updated");
		}

		return updateSuccessful;
	}

	public boolean deleteDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException {

		boolean deleteSuccessful = true;

		try {
			preparedStatement = connect.prepareStatement("DELETE FROM DELIVERY_DOCKETS WHERE publicationID = ?");
			preparedStatement.setInt(1, deliveryDocket.getDeliveryDocketID());
			preparedStatement.execute();

		} catch (Exception exception) {
			deleteSuccessful = false;
			throw new DeliveryDocketException("Delivery Docket not deleted");
		}

		return deleteSuccessful;
	}

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

	public boolean shutDownConnection() throws PublicationException {
		try {
			connect.close();
			System.out.println("Connection closed");
			return true;

		} catch (SQLException e) {
			throw new PublicationException("Connection not closed.");

		}

	}

	// Customers DBAO
	public boolean insertCustomerInfo(Customers cus) throws CustomersException {
		boolean insertSucessfull = true;
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO CUSTOMERS VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, cus.getId());
			preparedStatement.setString(2, cus.getAddress());
			preparedStatement.setString(3, cus.getfName());
			preparedStatement.setString(4, cus.getlName());
			preparedStatement.setString(5, cus.getNumber());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			insertSucessfull = false;
            throw new CustomersException("Customer not inserted");
		}
		return insertSucessfull;
	}

	public boolean deleteCustomerById(Customers cus) throws CustomersException {
        boolean deleteSuccessful = true;

        try {
            preparedStatement = connect.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?");
            preparedStatement.setInt(1, cus.getId());
            preparedStatement.execute();

        } catch (Exception exception) {
            deleteSuccessful = false;
            throw new CustomersException("Customer not deleted");
        }

        return deleteSuccessful;
	}

	public ResultSet displayCustomers() throws CustomersException {
		try {
            statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM CUSTOMERS");
		} catch (Exception e) {
			resultSet = null;
			throw new CustomersException("Cant get all customers");
		}
		return resultSet;
	}

	public boolean updateCustomerDetails(Customers cus) throws CustomersException {
		boolean updateSuccesful = false;
		try {
            preparedStatement = connect.prepareStatement(
                    "UPDATE CUSTOMRES SET ADDRESS = ?, FIRSTNAME = ?, LASTNAME = ?, MOBILENUMBER = ?  WHERE deliveryDocketID = ? ");
            preparedStatement.setString(1, cus.getAddress());
            preparedStatement.setString(2, cus.getfName());
            preparedStatement.setString(3, cus.getlName());
            preparedStatement.setString(4, cus.getNumber());
            preparedStatement.setInt(5,cus.getId());
            preparedStatement.execute();
			updateSuccesful = true;
		} catch (Exception e) {
			throw new CustomersException("Customer not Updated");
		}
		return updateSuccesful;
	}

}