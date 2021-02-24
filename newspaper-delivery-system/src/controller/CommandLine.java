package controller;

import exceptions.CustomersException;
import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.SQLConnectionException;
import model.Address;
import model.Customers;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Publication;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {
	
	private static MySQLAccess custable;

	public CommandLine()
	{
		runProgram();
	}
	private static void listCmdGui() {
		// Present options to the user
		System.out.println("|------------------------|");
		System.out.println("Select desired option:");
		System.out.println("|------------------------|");
		System.out.println("1. Create new publication");
		System.out.println("2. Update existing publication");
		System.out.println("3. Print publication table");
		System.out.println("4. Delete publication");
		System.out.println("|------------------------|");
		System.out.println("5. Create new Delivery Area");
		System.out.println("6. Update Delivery Area");
		System.out.println("7. Print Delivery Area Table");
		System.out.println("8. Delete Delivery Area");
		System.out.println("|------------------------|");
		System.out.println("9. Print Delivery Docket Table");
		System.out.println("10. Create Delivery Docket");
		System.out.println("11. Update Delivery Docket");
		System.out.println("12. Delete Delivery Docket");
		System.out.println("|------------------------|");
		System.out.println("\nTo display customer table enter 13 and press enter");
		System.out.println("\n|------------------------|");
		System.out.println("0. Close the application");
		System.out.println("|------------------------|");
		System.out.print("\n\n>>>>>>>>>>>>>>>>> ");
	}

	private static boolean printPublicationTable(List<Publication> publications) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: Publication");
		System.out.printf("%30s", "Order Id");
		System.out.printf("%30s", "Publication Name");
		System.out.printf("%30s", "Price");
		System.out.println();
		for (Publication publication : publications) {
			System.out.printf("%30s", publication.getOrder_id());
			System.out.printf("%30s", publication.getName());
			System.out.printf("%30s", publication.getPrice());
			System.out.println();
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	private static boolean printDeliveryAreaTable(List<DeliveryArea> deliveryAreas) {

		// Print The Contents of the Full Publication Table

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: Delivery Area");
		System.out.printf("%30s", " Id");
		System.out.printf("%30s", "Area");
		System.out.printf("%30s", "Size");
		System.out.println();

		for (DeliveryArea da : deliveryAreas) {
			System.out.printf("%30s", da.getAreaId());
			System.out.printf("%30s", da.getName());
			System.out.printf("%30s", da.getSize());
			System.out.println();
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		return true;
	}

	private static boolean printDeliveryDocketTable(List<DeliveryDocket> deliveryDockets) {

		// Print The Contents of the Full Delivery Docket Table

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: Delivery Docket");
		System.out.printf("%30s", " deliveryDocketID");
		System.out.printf("%30s", " publicationID");
		System.out.printf("%30s", "deliveryAreaID");
		System.out.printf("%30s", "customerID");
		System.out.println();

		for (DeliveryDocket deliveryDocket : deliveryDockets) {
			System.out.printf("%30s", deliveryDocket.getDeliveryDocketID());
			System.out.printf("%30s", deliveryDocket.getPublicationID());
			System.out.printf("%30s", deliveryDocket.getDeliveryAreaID());
			System.out.printf("%30s", deliveryDocket.getCustomerID());
			System.out.println();
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Customer Methods
	static void userMenuOptions() {
		System.out.println("-Please select an option from one of the following:\n");
		System.out.println("-To add a new customer address to table enter 1");
		System.out.println("-To add a new customer to table enter 2");
		System.out.println("-To display list of customers from table enter 3");
		System.out.println("-To display list of addresses from table enter 4");
		System.out.println("-To delete a customer from table enter 5");
		System.out.println("-To delete a customer address from table enter 6");
		System.out.println("-To view customers and address table enter 7");
		System.out.println("-To exit this session enter -1");
		System.out.println("");
		System.out.print(">>>>>>>> ");
	}

	static void displayCustomers() {
		try {
			boolean run = true;
			custable = new MySQLAccess();
			Scanner input = new Scanner(System.in);
			while (run) {
				userMenuOptions();
				int choice = input.nextInt();

				if (choice == 1)
					addAddress();
				else if (choice == 2)
					addCustomer();
				else if (choice == 3) {
					ResultSet rs = custable.displayCustomers();
					printCustomerTable(rs);
					Thread.sleep(5000);
				} else if (choice == 4) {
					ResultSet rs = custable.displayAddress();
					printAddressTable(rs);
					Thread.sleep(5000);
				} else if (choice == 5)
					deleteCustomer();
				else if (choice == 6)
					deleteAddress();
				else if (choice == 7) {
					ResultSet rs = custable.joinCustomerAddress();
					printCustomerAddress(rs);
					Thread.sleep(5000);
				} else {
					run=false;
				}
			}
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	static void addCustomer() {
		int cus_id;
		String firstName, lastName;
		String number;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		try {
			custable = new MySQLAccess();
			System.out.println();
			System.out.print("Enter address id and press enter: ");
			cus_id = kb.nextInt();
			System.out.print("Enter customer firstname and press enter: ");
			firstName = kb.next();
			System.out.print("Enter customer lastname and press enter: ");
			lastName = kb.next();
			System.out.print("Enter customer number and press enter: ");
			number = kb.next();
			System.out.println();
			try {
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				Customers cus = new Customers(cus_id, firstName, lastName, prefix, digits);
				boolean res = custable.insertCustomerInfo(cus);
				if (res == true)
					System.out.println("\nDetails Saved\n");
			} catch (CustomersException e) {
				System.out.println("ERROR: Customer Details NOT Saved: " + e.getMessage() + "\n");
			}
			;
		} catch (InputMismatchException | SQLConnectionException e) {
			addCustomer();
		}
	}

	static void deleteCustomer() throws SQLConnectionException {
		custable = new MySQLAccess();
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.print("\nEnter Customer Id to be deleted or -99 to remove all customers: ");
		int deleteCustId = kb.nextInt();
		boolean deleteResult = custable.deleteCustomerById(deleteCustId);
		if ((deleteResult == true) && (deleteCustId == -99))
			System.out.println("\nCustomer Table Emptied");
		else if (deleteResult == true)
			System.out.println("\nCustomer Deleted");
		else
			System.out.println("\nERROR: Customer Details NOT Deleted or Do Not Exist");
	}

	static void addAddress() {
		int address_id, houseNumber;
		String street, town, city, country;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		try {
			custable = new MySQLAccess();
			System.out.println();
			System.out.print("Street: ");
			street = kb.nextLine();
			System.out.print("Town: ");
			town = kb.next();
			System.out.print("City: ");
			city = kb.next();
			System.out.print("Country: ");
			country = kb.next();
			System.out.print("House number: ");
			houseNumber = kb.nextInt();
			System.out.print("Address_id: ");
			address_id = kb.nextInt();
			try {
				Address address = new Address(address_id, houseNumber, street, town, city, country);
				boolean res = custable.insertAddressInfo(address);
				if (res == true)
					System.out.println("\nAddress Details Saved\n");
			} catch (CustomersException e) {
				System.out.println("ERROR: Address details NOT saved: " + e.getMessage() + "\n");
			}
			;
		} catch (InputMismatchException | SQLConnectionException e) {
			addAddress();
		}
	}

	static void deleteAddress() {

	}

	static boolean printCustomerTable(ResultSet rs) {
		try {
			MySQLAccess custable = new MySQLAccess();
			rs = custable.displayCustomers();
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int h = 1; h <= colCount; h++)
				System.out.printf("%20s", meta.getColumnName(h));
			System.out.println("\n");
			while (rs.next()) {
				int id = rs.getInt("customer_id");
				String add_id = rs.getString("address_id");
				String fName = rs.getString("firstName");
				String lName = rs.getString("lastName");
				String number = rs.getString("mobileNumber");
				System.out.printf("%20s%20s%20s%20s%20s", id, add_id, fName, lName, number);
				System.out.println();
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	static boolean printAddressTable(ResultSet rs) {
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();

			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int h = 1; h <= colCount; h++)
				System.out.printf("%20s", meta.getColumnName(h));
			System.out.println("\n");
			while (rs.next()) {
				int id = rs.getInt("address_id");
				int number = rs.getInt("houseNumber");
				String street = rs.getString("street");
				String town = rs.getString("town");
				String city = rs.getString("city");
				String country = rs.getString("country");
				System.out.printf("%20s%20s%20s%20s%20s%20s", id, number, street, town, city, country);
				System.out.println();
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	static boolean printCustomerAddress(ResultSet rs) {
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();

			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Table: " + meta.getTableName(1));
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int h = 1; h <= colCount; h++)
				System.out.printf("%20s", meta.getColumnName(h));
			System.out.println("\n");
			while (rs.next()) {
				int id = rs.getInt("customer_id");
				String fname = rs.getString("Name");
				int number = rs.getInt("houseNumber");
				String street = rs.getString("street");
				String town = rs.getString("town");
				String city = rs.getString("city");
				String country = rs.getString("country");
				System.out.printf("%20s%20s%20s%20s%20s%20s%20s", id, fname, number, street, town, city, country);
				System.out.println();
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public void runProgram()
	{
		try {
			MySQLAccess mysql = new MySQLAccess();
			// Configure System for Running
			Scanner in = new Scanner(System.in);
			boolean running = true;
			while (running == true) {
				// Present list of functionality and get selection
				listCmdGui();
				String choice = in.next();
// option 1
				if (choice.equals("1")) {
					// Get Publication Details
					System.out.printf("Enter Publications Order Id: \n");
					String publicationOrderId = in.next();
					System.out.printf("Enter Publications Name: \n");
					String publicationName = in.next();
					System.out.printf("Enter Publication Price(�): \n");
					double pubcliationPrice = in.nextDouble();

					Publication pub = new Publication(publicationOrderId, publicationName, pubcliationPrice);
					// Insert Publication Details into the database
					boolean insertResult = mysql.insertNewPublication(pub);
					if (insertResult == true)
						System.out.println("New Publication Added");
					else
						System.out.println("Failed: Publication not added");
					/* option 2 */} else if (choice.equals("2")) {
					// Update Publication Details
					System.out.printf("Enter Publications Order Id which you want to update: \n");
					String publicationOrderId = in.next();
					System.out.printf("Enter new Publications Name for " + publicationOrderId + ": \n");
					String publicationName = in.next();
					System.out.printf("Enter new Publications Price(�) for " + publicationOrderId + ": \n");
					double pubcliationPrice = in.nextDouble();

					Publication pub = new Publication(publicationOrderId, publicationName, pubcliationPrice);
					// Insert Publication into the database
					boolean insertResult = mysql.updatePublication(pub);
					if (insertResult)
						System.out.println("New Publication Added");
					else
						System.out.println("Failed: Publication not added");
					/* option 4 */ } else if (choice.equals("4")) {
					// Update Publication Details
					System.out.printf("Enter Publications Order Id which you want to delete: \n");
					String publicationOrderId = in.next();
					Publication pub = new Publication(publicationOrderId, "Deleted", 11.12);
					// Insert Publication into the database
					boolean insertResult = mysql.delete(pub);
					if (insertResult)
						System.out.println("Publication Deleted");
					else
						System.out.println("Failed: Publication not deleted");
					/* option 3 */ } else if (choice.equals("3")) {
					// Retrieve ALL Publication Records
					List<Publication> resT = mysql.retrieveAllPublications();
					printPublicationTable(resT);
					/* option 5 */ } else if (choice.equals("5")) {
					System.out.println("Enter Area Name: ");
					String areaName = in.next();
					System.out.println("Enter Size: ");
					int areaSize = in.nextInt();
					boolean result = mysql.insertDeliveryArea(new DeliveryArea(areaName, areaSize));
					if (result == true)
						System.out.println("New Publication Added");
					else
						System.out.println("Failed: Publication not added");
					/* option 6 */ } else if (choice.equals("6")) {
					System.out.println("Enter Area Id you want to update:");
					int id = in.nextInt();
					System.out.println("Change the Area Name:");
					String name = in.next();
					System.out.println("Change the size of the Area");
					int size = in.nextInt();
					DeliveryArea deliveryArea = new DeliveryArea(id, name, size);
					mysql.updateDeliveryArea(deliveryArea);
					/* option 8 */ } else if (choice.equals("8")) {
					System.out.println("Enter Area Id you want to delete:");
					int id = in.nextInt();
					if (mysql.readDeliveryAreaById(id) == null) {
						throw new DeliveryAreaException("delivery Area Id is not in the Database");
					}
					DeliveryArea deliveryArea = mysql.readDeliveryAreaById(id);
					mysql.deleteDeliveryArea(deliveryArea);
					/* option 9 */ } else if (choice.equals("9")) {
					List<DeliveryDocket> deliveryDockets = mysql.readAllDeliveryDockets();
					printDeliveryDocketTable(deliveryDockets);
					/* option 10 */ } else if (choice.equals("10")) {
					System.out.print("Publication ID: ");
					int publicationID = in.nextInt();
					System.out.print("Delivery Area ID: ");
					int deliveryAreaID = in.nextInt();
					System.out.print("Customer ID: ");
					int customerID = in.nextInt();

					boolean result = mysql
							.insertDeliveryDocket(new DeliveryDocket(publicationID, deliveryAreaID, customerID));
					if (result == true)
						System.out.println("New Delivery Docket Added");
					else
						System.out.println("Failed: Delivery Docket not added");
					/* option 10 */ } else if (choice.equals("10")) {
					System.out.println("Publication ID: ");
					int publicationID = in.nextInt();
					System.out.println("Delivery Area ID: ");
					int deliveryAreaID = in.nextInt();
					System.out.println("Customer ID: ");
					int customerID = in.nextInt();

					boolean result = mysql
							.insertDeliveryDocket(new DeliveryDocket(publicationID, deliveryAreaID, customerID));
					if (result == true)
						System.out.println("New Delivery Docket Added");
					else
						System.out.println("Failed: Delivery Docket not added");
					/* option 11 */ } else if (choice.equals("11")) {
					System.out.println("Enter Delivery Docket Id you want to update:");
					int id = in.nextInt();
					System.out.println("Change the Publication ID: ");
					int publicationID = in.nextInt();
					System.out.println("Change the Delivery Area ID: ");
					int deliveryAreaID = in.nextInt();
					System.out.println("Change the Customer ID: ");
					int customerID = in.nextInt();
					mysql.updateDeliveryDocketByID(id, publicationID, deliveryAreaID, customerID);
					/* option 12 */ } else if (choice.equals("12")) {
					System.out.println("Enter Delivery Docket Id you want to delete:");
					int id = in.nextInt();
					if (mysql.getDeliveryDocketByID(id) == null) {
						throw new DeliveryDocketException("Delivery Docket Id is not in the Database");
					}
						mysql.deleteDeliveryDocketByID(id);
					}
					else if (choice.equals("13"))
					{
						displayCustomers();
					} 
					/* option 7 */ else if (choice.equals("7")) {
					List<DeliveryArea> deliveryAreas = mysql.readAllDeliveryArea();
					printDeliveryAreaTable(deliveryAreas);
					/* option 0 */ } else if (choice.equals("0")) {
					running = false;
					System.out.println("Program will now close.");
				}

			}
			in.close();
		} catch (Exception e) 
		{
			System.out.println("WE HEre Now");
			System.out.println("System fail: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// Create the Database Object
		new CommandLine();
// end try-catch

	} // end main
}