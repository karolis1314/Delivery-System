package model;

import exceptions.PublicationException;

public class Publication {
	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Publication(String name, double price) throws PublicationException {
		// Validate the inputs

		try {
			validateName(name);
			validatePrice(price);
		} catch (PublicationException e) {
			throw e;
		}

		this.name = name;
		this.price = price;
	}

	public static void validatePrice(double price) throws PublicationException {
		if(price == 0){
			throw new PublicationException("Publication price, must be above 0");
		}
		else if(price >= 100) {
			throw new PublicationException("Publication price, must be below 100");
		}

	}

	public static void validateName(String name) throws PublicationException {
		if (name.isEmpty() || name.equals("")) {
			throw new PublicationException("Publication name, must be from 1 to 15 characters.");
		} 
		else if (name.length() > 15) {
			throw new PublicationException("Publication name, must be max of 15 characters");
		}

	}

}
