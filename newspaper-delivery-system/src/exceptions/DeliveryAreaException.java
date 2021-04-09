package exceptions;

@SuppressWarnings("serial")
public class DeliveryAreaException extends Exception {
    String errorMessage;

    public DeliveryAreaException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}