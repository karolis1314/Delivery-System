package controller;

import exceptions.DeliveryDocketException;
import model.DeliveryDocket;

import java.sql.*;

public class DeliveryDocketDAO {

    private Connection connection;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    final private String host ="127.0.0.1:3306";
    final private String user = "root";
    final private String password = "";

    public boolean connectToDatabase() throws DeliveryDocketException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/NEWSPAPER_DELIVERY_SYSTEM?" + "user=" + user + "&password=" + password);
        } catch (Exception exception) {
            throw new DeliveryDocketException("Database connection failed");
        }
        return true;
    }

    public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException, SQLException {
        connectToDatabase();

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS VALUES (DEFAULT, ?, ?, ?)");
            preparedStatement.setInt(1, deliveryDocket.getDeliveryDocketID());
            preparedStatement.setInt(2, deliveryDocket.getDeliveryAreaID());
            preparedStatement.setInt(3, deliveryDocket.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket DAO insert failed");
        } finally {
            connection.close();
        }
        return true;
    }
}
