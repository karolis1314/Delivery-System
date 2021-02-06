package model;

public class DeliveryDocket {
    private int deliveryDocketID;
    private int publicationID;
    private int deliveryAreaID;
    private int customerID;

    public DeliveryDocket(int publicationID, int deliveryAreaID, int customerID) {
        this.deliveryDocketID = deliveryDocketID;
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
}
