package exceptions;

public class SQLConnectionException extends Exception {
    String message;

    public SQLConnectionException(String errMessage) {
        this.message = errMessage;
    }

    public String getMessage() {
        return message;
    }
}
