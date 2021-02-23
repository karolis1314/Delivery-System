package exceptions;

@SuppressWarnings("serial")
public class CustomersException extends Exception
{
	String message;
	
	public CustomersException(String msg)
	{
		message = msg;
	}

	public String getMessage()
	{
		return message;
	}
}
