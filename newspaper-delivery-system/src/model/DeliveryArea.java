package model;

import exceptions.DeliveryAreaException;

public class DeliveryArea {
        private int areaId;
        private String name;
    



        public DeliveryArea(String name) throws DeliveryAreaException {
            validateAreaName(name);
            this.name = name;
           
        }

        public DeliveryArea(int id, String name) throws DeliveryAreaException {
            try {
                validateAreaId(id);
                validateAreaName(name);
              
            } catch (DeliveryAreaException e){
                throw new DeliveryAreaException("Invalid Delivery Area");
            }
            this.areaId = id;
            this.name = name;
           
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

}
