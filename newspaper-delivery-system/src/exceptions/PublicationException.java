package exceptions;

public class PublicationException extends Exception{
    String message;

    public PublicationException(String errMessage) {
        this.message = errMessage;
    }

    public String getMessage() {
        return message;
    }
}
