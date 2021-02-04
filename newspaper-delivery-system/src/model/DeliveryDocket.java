package model;

public class DeliveryDocket {
    private int deliveryDocketID;
    private int publicationID;
    private int deliveryAreaID;
    private int customerID;

    public DeliveryDocket(int deliveryDocketID, int publicationID, int deliveryAreaID, int customerID) {
        this.deliveryDocketID = deliveryDocketID;
        this.publicationID = publicationID;
        this.deliveryAreaID = deliveryAreaID;
        this.customerID = customerID;
    }
}
