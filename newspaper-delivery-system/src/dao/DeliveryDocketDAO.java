package dao;

import model.DeliveryDocket;

import java.sql.*;

public class DeliveryDocketDAO {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    final private String host ="127.0.0.1:3306";
    final private String user = "root";
    final private String password = "";


    public DeliveryDocketDAO() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://" + host + "/NEWSPAPER_DELIVERY_SYSTEM?" + "user=" + user + "&password=" + password);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) throws SQLException {
        boolean insertSucessfull = true;

        try {
            preparedStatement = connect.prepareStatement("insert into NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS values (default, ?, ?, ?)");
            preparedStatement.setInt(1, deliveryDocket.getDeliveryDocketID());
            preparedStatement.setInt(2, deliveryDocket.getDeliveryAreaID());
            preparedStatement.setInt(3, deliveryDocket.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Delivery Docket insert failed");
        }

        return insertSucessfull;
    }

}
