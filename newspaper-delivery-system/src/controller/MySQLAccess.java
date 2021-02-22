package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import exceptions.PublicationException;
import model.Publication;

import java.sql.ResultSet;
import java.sql.SQLException;

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
			String url = "jdbc:mysql://localhost:3306/publication";
			connect = DriverManager.getConnection(url, "root", "a00252699");
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

	public boolean shutDownConnection() throws PublicationException {
		try {
			connect.close();
			System.out.println("Connection closed");
			return true;

		} catch (SQLException e) {
			throw new PublicationException("Connection not closed.");

		}

	}

}