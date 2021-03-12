package exceptions;

public class StaffException extends Exception
{
    String message;

    public StaffException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}