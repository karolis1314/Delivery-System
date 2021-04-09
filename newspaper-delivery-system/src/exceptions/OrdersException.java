package exceptions;

@SuppressWarnings("serial")
public class OrdersException extends Exception
{
	String msg;
	
	public OrdersException(String msg)
	{
		this.msg = msg;
	}
	public String getMessage()
	{
		return msg;
	}
}
