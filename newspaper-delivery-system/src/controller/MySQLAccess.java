package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import exceptions.DeliveryAreaException;
import exceptions.PublicationException;
import exceptions.SQLConnectionException;
import model.DeliveryArea;
import model.Publication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLAccess {


    private Statement statement = null;
    private ResultSet resultSet = null;
    final private String host = "localhost:3306";
    final private String user = "root";
    final private String password = "admin";


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
                    "insert into publication (publication_order_id, publicationName, price_in_€)" + "values (?, ?, ?)");
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
                    "update publication set publicationName= ?, price_in_€ = ? where publication_order_id = ? ");
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
}
