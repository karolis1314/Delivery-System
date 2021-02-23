package controller;

import exceptions.DeliveryAreaException;
import exceptions.PublicationException;
import exceptions.SQLConnectionException;
import model.DeliveryArea;
import model.Publication;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private static void listCmdGui() {

        // Present options to the user
        System.out.println("|------------------------|");
        System.out.println("Select desired option:");
        System.out.println("1. Create new publication");
        System.out.println("2. Update existing publication");
        System.out.println("3. Print publication table");
        System.out.println("4. Delete publication");
        System.out.println("5. Create new Delivery Area");
        System.out.println("6. Update Delivery Area");
        System.out.println("7. Print Delivery Area Table");
        System.out.println("8. Delete Delivery Area");
        System.out.println("0. Close the application");
        System.out.println("|------------------------|");
    }


    private static boolean printPublicationTable(List<Publication> publications) throws Exception {

        //Print The Contents of the Full Publication Table

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

    }

    private static boolean printDeliveryAreaTable(List<DeliveryArea> deliveryAreas) throws Exception {

        //Print The Contents of the Full Publication Table

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

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
                    System.out.printf("Enter Publications Order Id: \n");
                    String publicationOrderId = in.next();
                    System.out.printf("Enter Publications Name: \n");
                    String publicationName = in.next();
                    System.out.printf("Enter Publication Price(€): \n");
                    double pubcliationPrice = in.nextDouble();

                    Publication pub = new Publication(publicationOrderId, publicationName, pubcliationPrice);
                    // Insert Publication Details into the database
                    boolean insertResult = mysql.insertNewPublication(pub);
                    if (insertResult == true)
                        System.out.println("New Publication Added");
                    else
                        System.out.println("Failed: Publication not added");
                } else if (choice.equals("2")) {
                    // Update Publication Details
                    System.out.printf("Enter Publications Order Id which you want to update: \n");
                    String publicationOrderId = in.next();
                    System.out.printf("Enter new Publications Name for " + publicationOrderId + ": \n");
                    String publicationName = in.next();
                    System.out.printf("Enter new Publications Price(€) for " + publicationOrderId + ": \n");
                    double pubcliationPrice = in.nextDouble();

                    Publication pub = new Publication(publicationOrderId, publicationName, pubcliationPrice);
                    // Insert Publication into the database
                    boolean insertResult = mysql.updatePublication(pub);
                    if (insertResult)
                        System.out.println("New Publication Added");
                    else
                        System.out.println("Failed: Publication not added");
                } else if (choice.equals("4")) {
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
                } else if (choice.equals("3")) {
                    //Retrieve ALL Publication Records
                    List<Publication> resT = mysql.retrieveAllPublications();
                    printPublicationTable(resT);
                } else if (choice.equals("5")) {
                    System.out.println("Enter Area Name: ");
                    String areaName = in.next();
                    System.out.println("Enter Size: ");
                    int areaSize = in.nextInt();
                    DeliveryAreaController deliveryAreaController = new DeliveryAreaController();
                    boolean result = deliveryAreaController.createDeliveryArea(areaName, areaSize);
                    if (result == true)
                        System.out.println("New Publication Added");
                    else
                        System.out.println("Failed: Publication not added");
                } else if(choice.equals("6")){
                    System.out.println("Enter Area Id you want to update:");
                    int id = in.nextInt();
                    System.out.println("Change the Area Name:");
                    String name = in.next();
                    System.out.println("Change the size of the Area");
                    int size = in.nextInt();
                    DeliveryArea deliveryArea = new DeliveryArea(id,name,size);
                    mysql.updateDeliveryArea(deliveryArea);
                }  else if(choice.equals("8")){
                    System.out.println("Enter Area Id you want to delete:");
                    int id = in.nextInt();
                    if (mysql.readDeliveryAreaById(id) == null) {
                        throw new DeliveryAreaException("delivery Area Id is not in the Database");
                    }
                    DeliveryArea deliveryArea = mysql.readDeliveryAreaById(id);
                    mysql.deleteDeliveryArea(deliveryArea);
                } else if (choice.equals("7")) {
                    List<DeliveryArea> deliveryAreas = mysql.readAllDeliveryArea();
                    printDeliveryAreaTable(deliveryAreas);
                } else if (choice.equals("0")) {
                    running = false;
                    System.out.println("Program will now close.");
                }

            }
            in.close();

        } catch (Exception e) {
            System.out.println("System fail:" + e.getMessage());
        }
// end try-catch

    } // end main

}