package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/publication";
			connect = DriverManager.getConnection(url, "root", "a00252699");
			statement = connect.createStatement();
		} catch (Exception e) {
			System.out.print("Failed to initialise DB Connection");
		}

	}

// insertPublicationDetails
	public boolean insertNewPublication(Publication p) {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			
			// String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
			       // + " values (?, ?, ?, ?, ?)";

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into publication (publicationName, price_in_€)"+"values (?, ?)");
			preparedStatement.setString(1, p.getName());
			preparedStatement.setDouble(2, p.getPrice());
			preparedStatement.execute();
			
			//connect.close();
			
		} catch (Exception e) {
			insertSucessfull = false;
		}

		return insertSucessfull;

	}//end insert
	public void shutDownConnection() {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}// end Class
