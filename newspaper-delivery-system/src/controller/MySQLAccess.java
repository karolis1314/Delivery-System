package controller;

import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.PublicationException;
import exceptions.SQLConnectionException;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Publication;

import java.sql.*;
import java.util.ArrayList;

public class MySQLAccess {


    private Statement statement = null;
    private ResultSet resultSet = null;
    final private String host = "localhost:3306";
    final private String user = "root";
    final private String password = "";


    public MySQLAccess() throws SQLConnectionException {
        Connection conn = null;
        try {
            openConnection();
        } catch (Exception e) {
            throw new SQLConnectionException("Database Connection failed");
        } finally {
            closeConnection(conn);
        }

    }

    //Create the connection
    public Connection openConnection() throws SQLConnectionException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + host + "/newsagent2021?" + "user=" + user + "&password=" + password);
        } catch (Exception e) {
            throw new SQLConnectionException("database not connected");
        }
    }


    private void closeConnection(Connection connection) throws SQLConnectionException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SQLConnectionException("Delivery Area not closed!");
            }
        }
    }

    //Insert the publication
    public boolean insertNewPublication(Publication p) throws PublicationException, SQLConnectionException {
        Connection conn = null;
        boolean insertSucessfull = true;

        try {
            conn = openConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into publication (publication_order_id, publicationName, price_in_�)" + "values (?, ?, ?)");
            ps.setString(1, p.getOrder_id());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.execute();
        } catch (Exception e) {
            insertSucessfull = false;
            throw new PublicationException("Publication is not added.");

        } finally {
            closeConnection(conn);
        }

        return insertSucessfull;

    }

    public boolean updatePublication(Publication p) throws PublicationException, SQLConnectionException {
        Connection conn = null;
        boolean update = true;

        try {
            conn = openConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "update publication set publicationName= ?, price_in_� = ? where publication_order_id = ? ");
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getOrder_id());
            ps.execute();

        } catch (Exception e) {
            update = false;
            throw new PublicationException("Publication is not updated.");
        }finally {
            closeConnection(conn);
        }

        return update;

    }

    public boolean delete(Publication p) throws PublicationException, SQLConnectionException {
Connection conn = null;
        boolean delete = true;

        try {
            conn = openConnection();
            PreparedStatement ps = conn.prepareStatement("delete from publication where publication_order_id= ?");
            ps.setString(1, p.getOrder_id());
            ps.execute();

        } catch (Exception e) {
            delete = false;
            throw new PublicationException("Publication is not deleted.");
        } finally {
            closeConnection(conn);
        }

        return delete;

    }

    public ArrayList<Publication> retrieveAllPublications() throws PublicationException, SQLConnectionException {
        ArrayList<Publication> publications = new ArrayList<>();
        Connection conn = null;
        try {
            conn = openConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from publication");
            while (resultSet.next()){
                String id = resultSet.getString(2);
                String name = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                Publication publication = new Publication(id,name,price);
                publications.add(publication);
            }
        } catch (Exception e) {

            throw new PublicationException("Date is not retrieved.");
        }finally {
            closeConnection(conn);
        }
        return publications;
    }

    public boolean insertDeliveryArea(DeliveryArea dea) throws DeliveryAreaException, SQLConnectionException {
        Connection conn = null;
        try {
            conn = openConnection();
            PreparedStatement  ps = conn.prepareStatement("insert into newsagent2021.delivery_areas values (default, ?, ?)");
            ps.setString(1, dea.getName());
            ps.setInt(2, dea.getSize());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DeliveryAreaException("Delivery Area DAO Insert failed");
        } finally {
            closeConnection(conn);
        }
        return true;
    }

    public ArrayList<DeliveryArea> readAllDeliveryArea() throws DeliveryAreaException, SQLConnectionException {
        ArrayList<DeliveryArea> arr = new ArrayList<DeliveryArea>();
        Connection con = null;
        try {
            con = openConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("Select * from newsagent2021.delivery_areas");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int size = resultSet.getInt(3);
                DeliveryArea deliveryArea = new DeliveryArea(id, name, size);
                arr.add(deliveryArea);
            }
        } catch (Exception e) {
            throw new DeliveryAreaException("Read all SQL Failed");
        } finally {
            closeConnection(con);
        }
        return arr;
    }

    public DeliveryArea readDeliveryAreaById(int id) throws DeliveryAreaException, SQLConnectionException {
        DeliveryArea da;
        Connection con = null;
        try {
            con = openConnection();
            PreparedStatement ps = con.prepareStatement("select * from newsagent2021.delivery_areas where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Aname");
                int size = rs.getInt("size");
                da = new DeliveryArea(id, name, size);
                return da;
            }
        } catch (Exception e) {
            throw new DeliveryAreaException("Delivery Area read by Id failed");
        } finally {
            closeConnection(con);
        }
        return null;
    }

    public boolean updateDeliveryArea(DeliveryArea da) throws DeliveryAreaException, SQLConnectionException {
        Connection con = null;
        try {
            con = openConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE newsagent2021.delivery_areas set  Aname = ? , size = ? where id = ?; ");
            ps.setString(1, da.getName());
            ps.setInt(2, da.getSize());
            ps.setInt(3, da.getAreaId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DeliveryAreaException("delivery Area update failed");
        } finally {
            closeConnection(con);
        }
        return true;
    }

    public boolean deleteDeliveryArea(DeliveryArea da) throws DeliveryAreaException {
        Connection conn = null;
        try {
            conn = openConnection();
            PreparedStatement ps =  conn.prepareStatement("DELETE FROM newsagent2021.delivery_areas WHERE id = ?");
            ps.setInt(1, da.getAreaId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DeliveryAreaException("delivery Area delete failed");
        } finally {
            try {
                closeConnection(conn);
            } catch (SQLConnectionException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) throws DeliveryDocketException, SQLConnectionException {
        Connection connection = null;
        try {
            connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO newsagent2021.DELIVERY_DOCKETS VALUES (DEFAULT, ?, ?, ?);");
            preparedStatement.setInt(1, deliveryDocket.getPublicationID());
            preparedStatement.setInt(2, deliveryDocket.getDeliveryAreaID());
            preparedStatement.setInt(3, deliveryDocket.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket DAO insert failed");
        } finally {
            closeConnection(connection);
        }
        return true;
    }

    public DeliveryDocket getDeliveryDocketByID(int deliveryDocketID) throws DeliveryDocketException, SQLConnectionException {
        Connection connection = null;
        DeliveryDocket deliveryDocket;
        try {
            connection = openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM newsagent2021.DELIVERY_DOCKETS WHERE DELIVERYDOCKETID = ?;");
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
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<DeliveryDocket> readAllDeliveryDockets() throws DeliveryDocketException, SQLConnectionException {
        ArrayList<DeliveryDocket> arr = new ArrayList<>();
        Connection con = null;
        try {
            con = openConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("Select * from newsagent2021.DELIVERY_DOCKETS");
            while (resultSet.next()) {
                int deliveryDocketID = resultSet.getInt(1);
                int publicationID = resultSet.getInt(2);
                int deliveryAreaID = resultSet.getInt(3);
                int customerID = resultSet.getInt(4);
                DeliveryDocket deliveryDocket = new DeliveryDocket(deliveryDocketID, publicationID, deliveryAreaID, customerID);
                arr.add(deliveryDocket);
            }
        } catch (Exception e) {
            throw new DeliveryDocketException("Read all SQL Failed");
        } finally {
            closeConnection(con);
        }
        return arr;
    }

    public void updateDeliveryDocketByID(int deliveryDocketID, int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException, SQLConnectionException {
        Connection connection = null;
        if (deliveryDocketID <= 0 || publicationID <= 0 || deliveryAreaID <= 0 || customerID <= 0) {
            throw new DeliveryDocketException("Invalid Delivery Docket parameters");
        } else {
            try {
                connection = openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE newsagent2021.DELIVERY_DOCKETS SET PUBLICATIONID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, publicationID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();


                preparedStatement = connection.prepareStatement("UPDATE newsagent2021.DELIVERY_DOCKETS SET DELIVERYAREAID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, deliveryAreaID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("UPDATE newsagent2021.DELIVERY_DOCKETS SET CUSTOMERID = ? WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, customerID);
                preparedStatement.setInt(2, deliveryDocketID);
                preparedStatement.executeUpdate();
            } catch (Exception exception) {
                throw new DeliveryDocketException("Delivery Docket DAO update failed");
            } finally {
                closeConnection(connection);
            }
        }
    }

    public void deleteDeliveryDocketByID(int deliveryDocketID) throws DeliveryDocketException, SQLConnectionException {
        Connection connection = null;
        if (deliveryDocketID <= 0) {
            throw new DeliveryDocketException("Invalid Delivery Docket ID");
        } else {
            try {
                connection = openConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM newsagent2021.DELIVERY_DOCKETS WHERE DELIVERYDOCKETID = ?;");
                preparedStatement.setInt(1, deliveryDocketID);
                preparedStatement.executeUpdate();
            } catch (Exception exception) {
                throw new DeliveryDocketException("Delivery Docket DAO delete failed");
            } finally {
                closeConnection(connection);
            }
        }
    }
}
