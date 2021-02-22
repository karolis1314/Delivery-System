package model;

import exceptions.PublicationException;

public class Publication {
	private String order_id;
	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Publication(String order_id, String name, double price) throws PublicationException {
		// Validate the inputs

		try {
			validateOrderId(order_id);
			validateName(name);
			validatePrice(price);
		} catch (PublicationException e) {
			throw e;
		}
		this.order_id = order_id;
		this.name = name;
		this.price = price;
	}

	
	// Validating that publication order id to make sure it meets the standarts
	public static void validateOrderId(String order_id) throws PublicationException {
		if(order_id.isEmpty() || order_id.length() != 7) {
			throw new PublicationException("Publication Order Id must be exactly 7 characters long, it can not be empty");
		}
		
	}
	
	// Validating the price range, so it must not exceed three digits
	public static void validatePrice(double price) throws PublicationException {
		if(price == 0){
			throw new PublicationException("Publication price, must be above 0");
		}
		else if(price >= 100) {
			throw new PublicationException("Publication price, must be below 100");
		}

	}
	
	// Validate length of the name, which can not be empty either
	public static void validateName(String name) throws PublicationException {
		if (name.isEmpty() || name.equals("")) {
			throw new PublicationException("Publication name, must be from 1 to 15 characters.");
		} 
		else if (name.length() > 15) {
			throw new PublicationException("Publication name, must be max of 15 characters");
		}

	}



}
