package controller;

import exceptions.DeliveryDocketException;
import model.DeliveryDocket;

import java.sql.*;

public class DeliveryDocketDAO {

    private Connection connection;
    private PreparedStatement preparedStatement = null;

    final private String host = "127.0.0.1:3306";
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
            preparedStatement = connection.prepareStatement("INSERT INTO NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS VALUES (DEFAULT, ?, ?, ?);");
            preparedStatement.setInt(1, deliveryDocket.getPublicationID());
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

    public DeliveryDocket getDeliveryDocketByID(int deliveryDocketID) throws DeliveryDocketException, SQLException {
        connectToDatabase();

        DeliveryDocket deliveryDocket;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS WHERE DELIVERYDOCKETID = ?;");
            preparedStatement.setInt(1, deliveryDocketID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int publicationID = resultSet.getInt("publicationID");
                int deliveryAreaID = resultSet.getInt("deliveryAreaID");
                int customerID = resultSet.getInt("customerID");

                deliveryDocket = new DeliveryDocket(publicationID, deliveryAreaID, customerID);

                return deliveryDocket;
            }
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket DAO retrieval failed");
        } finally {
            connection.close();
        }
        return null;
    }

    public void updateDeliveryDocketByID(int deliveryDocketID, int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException, SQLException {
        connectToDatabase();

        if (deliveryDocketID <= 0 || publicationID <= 0 || deliveryAreaID <= 0 || customerID <= 0) {
            throw new DeliveryDocketException("Invalid Delivery Docket parameters");
        } else {
            try {
                preparedStatement = connection.prepareStatement("UPDATE NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS SET PUBLICATIONID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, publicationID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();


                preparedStatement = connection.prepareStatement("UPDATE NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS SET DELIVERYAREAID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, deliveryAreaID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("UPDATE NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS SET CUSTOMERID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, customerID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();
            } catch (Exception exception) {
                throw new DeliveryDocketException("Delivery Docket DAO update failed");
            } finally {
                connection.close();
            }
        }
    }

    public void deleteDeliveryDocketByID(int deliveryDocketID) throws DeliveryDocketException, SQLException {
        connectToDatabase();

        if (deliveryDocketID <= 0) {
            throw new DeliveryDocketException("Invalid Delivery Docket ID");
        } else {
            try {
                preparedStatement = connection.prepareStatement("DELETE FROM NEWSPAPER_DELIVERY_SYSTEM.DELIVERY_DOCKETS WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, deliveryDocketID);
                preparedStatement.executeUpdate();
            } catch (Exception exception) {
                throw new DeliveryDocketException("Delivery Docket DAO delete failed");
            } finally {
                connection.close();
            }
        }
    }
}
