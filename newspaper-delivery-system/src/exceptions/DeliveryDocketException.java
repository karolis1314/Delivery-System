package exceptions;

public class DeliveryDocketException extends Exception {
    String errorMessage;

    public DeliveryDocketException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}