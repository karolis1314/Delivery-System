package controller;

import dao.DeliveryAreaDAO;
import exceptions.DeliveryAreaException;
import model.DeliveryArea;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DeliveryAreaController {


        public boolean createDeliveryArea (String name,int size) throws DeliveryAreaException {

            if (size < 0 || size > Integer.MAX_VALUE) {
                throw new DeliveryAreaException("Invalid Size");
            }
            if (name.length() < 1 || name.length() > 255) {
                throw new DeliveryAreaException("Invalid Name");
            }
            try {
                DeliveryArea dea = new DeliveryArea(name, size);
                DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
                areaDAO.insertDeliveryArea(dea);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }


        public ArrayList<DeliveryArea> readAllDeliveryAreas () throws DeliveryAreaException {
            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
            ArrayList arr = areaDAO.readAllDeliveryArea();
            if (arr.size() < 0) {
                throw new DeliveryAreaException("Read all did not work");
            }
            return arr;
        }

        public boolean updateDeliveryArea ( int id, String name,int size) throws DeliveryAreaException {
            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
            DeliveryArea da = new DeliveryArea(id, name, size);
            if (!areaDAO.updateDeliveryArea(da)) {
                throw new DeliveryAreaException("Update is invalid");
            }
            areaDAO.updateDeliveryArea(da);
            return true;
        }

        public boolean deleteDeliveryArea ( int id) throws DeliveryAreaException {
            DeliveryAreaDAO areaDAO = new DeliveryAreaDAO();
            if (areaDAO.readDeliveryAreaById(id) == null) {
                throw new DeliveryAreaException("delivery Area Id is not in the Database");
            }
            DeliveryArea deliveryArea = areaDAO.readDeliveryAreaById(id);
            areaDAO.deleteDeliveryArea(deliveryArea);
            return true;
        }
    }
