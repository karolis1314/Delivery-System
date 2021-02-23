package exceptions;

public class DeliveryAreaException extends Exception{
    String message;

    public DeliveryAreaException(String errMessage) {
        this.message = errMessage;
    }

    public String getErrorMessage() {
        return message;
    }
}
