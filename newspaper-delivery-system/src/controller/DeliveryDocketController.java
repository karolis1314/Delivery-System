package controller;

import dao.DeliveryDocketDAO;
import exceptions.DeliveryDocketException;
import model.DeliveryDocket;

public class DeliveryDocketController {

    public boolean createDeliveryDocket(int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException {
        if (publicationID < 0 || publicationID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid publication");
        } else if (deliveryAreaID < 0 || deliveryAreaID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid delivery area");
        } else if (customerID < 0 || customerID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid customer");
        }
        try {
            DeliveryDocket deliveryDocket = new DeliveryDocket(publicationID, deliveryAreaID, customerID);
            DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

            deliveryDocketDAO.insertDeliveryDocket(deliveryDocket);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return true;
    }

}
