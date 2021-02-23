package controller;

import exceptions.DeliveryDocketException;
import model.DeliveryDocket;

public class DeliveryDocketController {

    public boolean createDeliveryDocket(int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException {
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(publicationID, deliveryAreaID, customerID);
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.insertDeliveryDocket(deliveryDocket);
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket creation failed");
        }
        return true;
    }

    public boolean readDeliveryDocket(int deliveryDocketID) throws DeliveryDocketException {
        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
            DeliveryDocket deliveryDocket = deliveryDocketDAO.getDeliveryDocketByID(deliveryDocketID);

            if (deliveryDocket == null) {
                throw new DeliveryDocketException("Invalid Delivery Docket object");
            }
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket read failed");
        }
        return true;
    }

    public boolean editDeliveryDocket(int deliveryDocketID, int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException {
        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.updateDeliveryDocketByID(deliveryDocketID, publicationID, deliveryAreaID, customerID);
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket edit failed");
        }
        return true;
    }

    public boolean removeDeliveryDocket(int deliveryDocketID) throws DeliveryDocketException {
        try {
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.deleteDeliveryDocketByID(deliveryDocketID);
        } catch (Exception exception) {
            throw new DeliveryDocketException("Delivery Docket removal failed");
        }
        return true;
    }
}
