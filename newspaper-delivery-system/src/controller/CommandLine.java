package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import model.Publication;

public class CommandLine {

	private static void listCmdGui() {

		// Present options to the user
		System.out.println("|------------------------|");
		System.out.println("Select desired option:");
		System.out.println("1. Create new publication");
		System.out.println("0. Close the application");
		System.out.println("|------------------------|");
	}

	public static void main(String[] args) {

		// Create the Database Object

		try {

			MySQLAccess mysql = new MySQLAccess();

			// Configure System for Running
			Scanner in = new Scanner(System.in);
			boolean running = true;

			while (running == true) {

				// Present list of functionality and get selection
				listCmdGui();
				String choice = in.next();
				if (choice.equals("1")) {
					// Get Publication Details
					System.out.printf("Enter Publications Name: \n");
					String publicationName = in.next();
					System.out.printf("Enter Publication Price(€): \n");
					double pubcliationPrice = in.nextDouble();
					//Rounding off the price to two decimals just in case the mistake was made
					

					Publication pub = new Publication(publicationName, pubcliationPrice);
					// Insert Customer Details into the database
					boolean insertResult = mysql.insertNewPublication(pub);
					if (insertResult == true)
						System.out.println("New Publication Added");
					else
						System.out.println("ERROR: Publication not added");
				}
				else if(choice.equals("0")) {
					running = false;
					mysql.shutDownConnection();
					System.out.println("Program will now close.");
				}

			}
			in.close();

		}

		catch (

		Exception e) {
			System.out.println("ERROR OCCURED:" + e.getMessage());
		} // end try-catch

	} // end main

}