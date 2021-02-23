package model;

import exceptions.DeliveryDocketException;

public class DeliveryDocket {
    private int deliveryDocketID;
    private int publicationID;
    private int deliveryAreaID;
    private int customerID;

    public DeliveryDocket(int publicationID, int deliveryAreaID, int customerID) throws DeliveryDocketException {
        try {
            validatePublicationID(publicationID);
            validateDeliveryAreaID(deliveryAreaID);
            validateCustomerID(customerID);
        } catch (DeliveryDocketException deliveryDocketException) {
            throw new DeliveryDocketException("Invalid Delivery Docket object");
        }

        this.publicationID = publicationID;
        this.deliveryAreaID = deliveryAreaID;
        this.customerID = customerID;
    }

    public int getDeliveryDocketID() {
        return deliveryDocketID;
    }

    public void setDeliveryDocketID(int deliveryDocketID) {
        this.deliveryDocketID = deliveryDocketID;
    }

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    public int getDeliveryAreaID() {
        return deliveryAreaID;
    }

    public void setDeliveryAreaID(int deliveryAreaID) {
        this.deliveryAreaID = deliveryAreaID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public static void validatePublicationID(int publicationID) throws DeliveryDocketException {
        // publicationID must not be negative or greater than the maximum integer value

        if (publicationID <= 0 || publicationID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid Publication ID");
        }
    }

    public static void validateDeliveryAreaID(int deliveryAreaID) throws DeliveryDocketException {
        // deliveryAreaID must not be negative or greater than the maximum integer value
        if (deliveryAreaID <= 0 || deliveryAreaID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid Delivery Area ID");
        }
    }

    public static void validateCustomerID(int customerID) throws DeliveryDocketException {
        // customerID must not be negative or greater than the maximum integer value
        if (customerID <= 0 || customerID > Integer.MAX_VALUE) {
            throw new DeliveryDocketException("Invalid Customer ID");
        }
    }

    @Override
    public String toString() {
        return "DeliveryDocket{" +
                "deliveryDocketID=" + deliveryDocketID +
                ", publicationID=" + publicationID +
                ", deliveryAreaID=" + deliveryAreaID +
                ", customerID=" + customerID +
                '}';
    }
}
