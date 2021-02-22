package model;

import exceptions.DeliveryAreaException;
import exceptions.PublicationException;

public class DeliveryArea {

    private int areaId;
    private String name;
    private int size;



    public DeliveryArea(String name, int size) throws DeliveryAreaException {
        validateAreaName(name);
        validateAreaSize(size);
        this.name = name;
        this.size = size;
    }

    public DeliveryArea(int id, String name, int size) throws DeliveryAreaException {
        try {
            validateAreaId(id);
            validateAreaName(name);
            validateAreaSize(size);
        } catch (DeliveryAreaException e){
            throw new DeliveryAreaException("Invalid Delivery Area");
        }
        this.areaId = id;
        this.name = name;
        this.size = size;
    }

    private void validateAreaSize(int size) throws DeliveryAreaException {
        if (size == 0) {
            throw new DeliveryAreaException("Area Delivery size below Zero");
        } else if (size >= 200) {
            throw new DeliveryAreaException("Area Delivery Size above 200");
        }
    }

    private void validateAreaName(String name) throws DeliveryAreaException {
        if (name.isEmpty() || name.length() > 50 ) {
            throw new DeliveryAreaException("Invalid Delivery Area name");
        }

    }

    private void validateAreaId(int id) throws DeliveryAreaException {
        if (id < 0){
            throw new DeliveryAreaException("Area ID less than Zero");
        }
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}


