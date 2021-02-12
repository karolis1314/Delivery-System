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

}
