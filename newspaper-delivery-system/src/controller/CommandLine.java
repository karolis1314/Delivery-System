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
        System.out.println("2. Update existing publication");
        System.out.println("3. Print publication table");
        System.out.println("4. Delete publication");
        System.out.println("0. Close the application");
        System.out.println("|------------------------|");
    }


    private static boolean printPublicationTable(ResultSet rs) throws Exception {

        //Print The Contents of the Full Publication Table

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%30s",rs.getMetaData().getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            int id = rs.getInt("id");
            String order_id = rs.getString("publication_order_id");
            String publication_name = rs.getString("publicationName");
            String price = rs.getString("price_in_€");
            System.out.printf("%30s", id);
            System.out.printf("%30s", order_id);
            System.out.printf("%30s", publication_name);
            System.out.printf("%30s", price);
            System.out.println();
        }// end while
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

                    Publication pub = new Publication(publicationOrderId,publicationName, pubcliationPrice);
                    // Insert Publication Details into the database
                    boolean insertResult = mysql.insertNewPublication(pub);
                    if (insertResult == true)
                        System.out.println("New Publication Added");
                    else
                        System.out.println("Failed: Publication not added");
                }
                else if(choice.equals("2")) {
                    // Update Publication Details
                    System.out.printf("Enter Publications Order Id which you want to update: \n");
                    String publicationOrderId = in.next();
                    System.out.printf("Enter new Publications Name for "+publicationOrderId+": \n");
                    String publicationName = in.next();
                    System.out.printf("Enter new Publications Price(€) for "+publicationOrderId+": \n");
                    double pubcliationPrice = in.nextDouble();

                    Publication pub = new Publication(publicationOrderId,publicationName, pubcliationPrice);
                    // Insert Publication into the database
                    boolean insertResult = mysql.updatePublication(pub);
                    if (insertResult == true)
                        System.out.println("New Publication Added");
                    else
                        System.out.println("Failed: Publication not added");
                }
                else if(choice.equals("4")) {
                    // Update Publication Details
                    System.out.printf("Enter Publications Order Id which you want to delete: \n");
                    String publicationOrderId = in.next();
                    Publication pub = new Publication(publicationOrderId,"Deleted", 11.12);
                    // Insert Publication into the database
                    boolean insertResult = mysql.delete(pub);
                    if (insertResult == true)
                        System.out.println("Publication Deleted");
                    else
                        System.out.println("Failed: Publication not deleted");
                }
                else if(choice.equals("3")) {
                    //Retrieve ALL Publication Records
                    ResultSet resT = mysql.retrieveAllPublications();
                    if (resT == null) {
                        System.out.println("No Publication Records Found");
                    }
                    else {
                        boolean tablePrinted = printPublicationTable(resT);
                        if (tablePrinted == true)
                            resT.close();
                    }
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
            System.out.println("System fail:" + e.getMessage());
        } // end try-catch

    } // end main

}