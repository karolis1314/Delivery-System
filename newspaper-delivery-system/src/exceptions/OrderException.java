package exceptions;

@SuppressWarnings("serial")
public class OrderException extends Exception
{
	String msg;
	
	public OrderException(String msg)
	{
		this.msg = msg;
	}
	public String getMessage()
	{
		return msg;
	}
}
