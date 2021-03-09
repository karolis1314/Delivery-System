package view;

import controller.MySQLAccess;
import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.PublicationException;
import model.DeliveryArea;
import model.DeliveryDocket;
import model.Publication;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

@SuppressWarnings("serial")
public class JDBCMainWindowContent extends JInternalFrame implements ActionListener {
    String cmd = null;

    // DB Connectivity Attributes
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private MySQLAccess mysql = new MySQLAccess();

    private Container content;

    private JPanel detailsPanel;
    private JPanel exportButtonPanel;
    // private JPanel exportConceptDataPanel;
    private JScrollPane dbContentsPanel;

    private Border lineBorder;

    private JLabel label = new JLabel("ID:");
    private JLabel label1 = new JLabel("Order ID:");
    private JLabel label2 = new JLabel("Name:");
    private JLabel label3 = new JLabel("Price:");
    private JLabel label4 = new JLabel("Mobile Number:");


    private JTextField text = new JTextField(10);
    private JTextField text1 = new JTextField(10);
    private JTextField text2 = new JTextField(10);
    private JTextField text3 = new JTextField(10);
    private JTextField text4 = new JTextField(10);


    private static QueryTableModel TableModel = new QueryTableModel();
    // Add the models to JTabels
    private JTable TableofDBContents = new JTable(TableModel);
    // Buttons for inserting, and updating members
    // also a clear button to clear details panel
    private JButton updateButtonPublication = new JButton("Update Publication");
    private JButton updateButtonArea = new JButton("Update Area");
    private JButton updateButtonDoc = new JButton("Update Docket");
    private JButton updateButtonCust = new JButton("Update Customer");
    private JButton insertButtonPublication = new JButton("Insert Pub");
    private JButton insertButtonCustomer = new JButton("Insert Cust");
    private JButton insertButtonArea = new JButton("Insert Area");
    private JButton insertButtonDocket = new JButton("Insert Docket");
    private JButton showPub = new JButton("Show Pub");
    private JButton deleteButtonPublication = new JButton("Delete Pub");
    private JButton deleteButtonCustomer = new JButton("Delete Cust");
    private JButton deleteButtonArea = new JButton("Delete Area");
    private JButton deleteButtonDocket = new JButton("Delete Dic");
    private JButton clearButton = new JButton("Clear");
    private JButton showCust = new JButton("Show Cust");
    private JButton showArea = new JButton("Show Area");
    private JButton showDocket = new JButton("Show Doc");



    public JDBCMainWindowContent(String aTitle) throws Exception {
        // setting up the GUI
        super(aTitle, false, false, false, false);
        setEnabled(true);

        initiate_db_conn();
        // add the 'main' panel to the Internal Frame
        content = getContentPane();
        content.setLayout(null);
        content.setBackground(Color.lightGray);
        lineBorder = BorderFactory.createEtchedBorder(15, Color.red, Color.black);

        // setup details panel and add the components to it
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(12, 2));
        detailsPanel.setBackground(Color.lightGray);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "CRUD Actions"));

        detailsPanel.add(label);
        detailsPanel.add(text);
        detailsPanel.add(label1);
        detailsPanel.add(text1);
        detailsPanel.add(label2);
        detailsPanel.add(text2);
        detailsPanel.add(label3);
        detailsPanel.add(text3);
        detailsPanel.add(label4);
        detailsPanel.add(text4);


        insertButtonPublication.setSize(100, 30);
        insertButtonCustomer.setSize(100, 30);
        insertButtonArea.setSize(100, 30);
        insertButtonDocket.setSize(100, 30);
        updateButtonArea.setSize(100, 30);
        updateButtonPublication.setSize(100, 30);
        updateButtonCust.setSize(100, 30);
        updateButtonDoc.setSize(100, 30);
        showPub.setSize(100, 30);
        deleteButtonCustomer.setSize(100, 30);
        deleteButtonPublication.setSize(100, 30);
        deleteButtonArea.setSize(100, 30);
        deleteButtonDocket.setSize(100, 30);
        clearButton.setSize(100, 30);
        showCust.setSize(100, 30);
        showArea.setSize(100, 30);
        showDocket.setSize(100, 30);


        insertButtonPublication.setLocation(370, 10);
        insertButtonCustomer.setLocation(370, 10);
        insertButtonDocket.setLocation(370, 10);
        insertButtonArea.setLocation(370, 10);
        updateButtonDoc.setLocation(370, 70);
        updateButtonPublication.setLocation(370, 70);
        updateButtonCust.setLocation(370, 70);
        updateButtonArea.setLocation(370, 70);
        showPub.setLocation(370, 130);
        deleteButtonDocket.setLocation(370, 40);
        deleteButtonPublication.setLocation(370, 40);
        deleteButtonArea.setLocation(370, 40);
        deleteButtonCustomer.setLocation(370, 40);
        clearButton.setLocation(370, 100);
        showCust.setLocation(370, 160);
        showArea.setLocation(370, 190);
        showDocket.setLocation(370, 220);

        //Inserts
        insertButtonPublication.addActionListener(this);
        insertButtonCustomer.addActionListener(this);
        insertButtonDocket.addActionListener(this);
        insertButtonArea.addActionListener(this);

        updateButtonArea.addActionListener(this);
        updateButtonPublication.addActionListener(this);
        updateButtonCust.addActionListener(this);
        updateButtonDoc.addActionListener(this);
        showPub.addActionListener(this);
        deleteButtonCustomer.addActionListener(this);
        deleteButtonPublication.addActionListener(this);
        deleteButtonArea.addActionListener(this);
        deleteButtonDocket.addActionListener(this);
        clearButton.addActionListener(this);
        showCust.addActionListener(this);
        showArea.addActionListener(this);
        showDocket.addActionListener(this);

        content.add(insertButtonPublication);
        content.add(insertButtonCustomer);
        content.add(insertButtonDocket);
        content.add(insertButtonArea);
        content.add(updateButtonArea);
        content.add(updateButtonPublication);
        content.add(updateButtonCust);
        content.add(updateButtonDoc);
        content.add(showPub);
        content.add(deleteButtonArea);
        content.add(deleteButtonPublication);
        content.add(deleteButtonCustomer);
        content.add(deleteButtonDocket);
        content.add(clearButton);
        content.add(showCust);
        content.add(showArea);
        content.add(showDocket);


        TableofDBContents.setPreferredScrollableViewportSize(new Dimension(900, 300));

        dbContentsPanel = new JScrollPane(TableofDBContents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dbContentsPanel.setBackground(Color.lightGray);
        dbContentsPanel.setBorder(BorderFactory.createTitledBorder(lineBorder, "Database Content"));

        detailsPanel.setSize(360, 300);
        detailsPanel.setLocation(3, 0);
        dbContentsPanel.setSize(700, 300);
        dbContentsPanel.setLocation(477, 0);

        content.add(detailsPanel);
        content.add(dbContentsPanel);

        setSize(982, 300);
        setVisible(true);


        //Initial settings
        TableModel.refreshFromDBPublication(stmt);

        insertButtonCustomer.setVisible(false);
        insertButtonPublication.setVisible(true);
        insertButtonDocket.setVisible(false);
        insertButtonArea.setVisible(false);
        deleteButtonDocket.setVisible(false);
        deleteButtonArea.setVisible(false);
        updateButtonDoc.setVisible(false);
        updateButtonArea.setVisible(false);
        updateButtonCust.setVisible(false);
        label.setText("Pub Order ID:");
        label1.setText("Publication Name:");
        label2.setText("Publication Price:");
        label3.setVisible(false);
        text2.setVisible(true);
        label4.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);


    }

    public void initiate_db_conn() {
        try {
            // Load the JConnector Driver
            Class.forName("com.mysql.jdbc.Driver");
            // Specify the DB Name
            String url = "jdbc:mysql://localhost:3306/newsagent2021";
            // Connect to DB using DB URL, Username and password
            con = DriverManager.getConnection(url, "root", "a00252699");
            // Create a generic statement which is passed to the TestInternalFrame1
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: Failed to connect to database\n" + e.getMessage());
        }
    }

    // event handling
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == clearButton) {
            text.setText("");
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");

        }

        if (target == showCust) {
            TableModel.refreshFromDBCustomer(stmt);
            insertButtonCustomer.setVisible(true);
            insertButtonPublication.setVisible(false);
            insertButtonDocket.setVisible(false);
            insertButtonArea.setVisible(false);
            label.setText("Customer ID:");
            label1.setText("Customer Address:");
            label2.setText("First Name:");
            label3.setText("Last Name:");
            label4.setText("Mobile Number:");
            label3.setVisible(true);
            label2.setVisible(true);
            label4.setVisible(true);
            text3.setVisible(true);
            text4.setVisible(true);
            text2.setVisible(true);
            deleteButtonDocket.setVisible(false);
            deleteButtonArea.setVisible(false);
            deleteButtonCustomer.setVisible(true);
            deleteButtonPublication.setVisible(false);
            updateButtonDoc.setVisible(false);
            updateButtonArea.setVisible(false);
            updateButtonPublication.setVisible(false);
            updateButtonCust.setVisible(true);

        }

        if (target == showArea) {

            TableModel.refreshFromDBDeliveryArea(stmt);
            insertButtonCustomer.setVisible(false);
            insertButtonPublication.setVisible(false);
            insertButtonDocket.setVisible(false);
            insertButtonArea.setVisible(true);
            label.setText("Area Name:");
            label1.setText("Area Size:");
            label2.setText("ID:");
            text2.setVisible(true);
            label3.setVisible(false);
            text3.setVisible(false);
            label4.setVisible(false);
            text4.setVisible(false);
            deleteButtonDocket.setVisible(false);
            deleteButtonArea.setVisible(true);
            deleteButtonCustomer.setVisible(false);
            deleteButtonPublication.setVisible(false);
            updateButtonDoc.setVisible(false);
            updateButtonArea.setVisible(true);
            updateButtonPublication.setVisible(false);
            updateButtonCust.setVisible(false);

        }

        if (target == showDocket) {

            TableModel.refreshFromDBDeliveryDocket(stmt);
            insertButtonCustomer.setVisible(false);
            insertButtonPublication.setVisible(false);
            insertButtonDocket.setVisible(true);
            insertButtonArea.setVisible(false);
            label.setText("Pub ID:");
            label1.setText("Area ID:");
            label2.setText("Customer ID:");
            label3.setText("Docket ID:");
            label2.setVisible(true);
            label3.setVisible(true);
            text2.setVisible(true);
            label4.setVisible(false);
            text3.setVisible(true);
            text4.setVisible(false);
            deleteButtonDocket.setVisible(true);
            deleteButtonArea.setVisible(false);
            deleteButtonCustomer.setVisible(false);
            deleteButtonPublication.setVisible(false);
            updateButtonDoc.setVisible(true);
            updateButtonArea.setVisible(false);
            updateButtonPublication.setVisible(false);
            updateButtonCust.setVisible(false);

        }
        if (target == showPub) {

            TableModel.refreshFromDBPublication(stmt);
            insertButtonCustomer.setVisible(false);
            insertButtonPublication.setVisible(true);
            insertButtonDocket.setVisible(false);
            insertButtonArea.setVisible(false);
            label.setText("Pub Order ID:");
            label1.setText("Publication Name:");
            label2.setText("Publication Price:");
            label3.setVisible(false);
            text2.setVisible(true);
            label4.setVisible(false);
            text3.setVisible(false);
            text4.setVisible(false);
            deleteButtonDocket.setVisible(false);
            deleteButtonArea.setVisible(false);
            deleteButtonCustomer.setVisible(false);
            deleteButtonPublication.setVisible(true);
            updateButtonDoc.setVisible(false);
            updateButtonArea.setVisible(false);
            updateButtonPublication.setVisible(true);
            updateButtonCust.setVisible(false);

        }
        if (target == deleteButtonPublication) {

            try {
                Publication pub = new Publication(text.getText(), "Deleted", 11.12);
                // Insert Publication into the database
                boolean insertResult = mysql.delete(pub);
                if (insertResult == true)
                    System.out.println("Publication Deleted");
                else
                    System.out.println("Failed: Publication not deleted");

            } catch (PublicationException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBPublication(stmt);
            }

        }


        if (target == deleteButtonArea) {

            try {
                int id = Integer.parseInt(text2.getText());
                DeliveryArea da = new DeliveryArea(id, "Deleted", 120);
                // Delete Area
                boolean insertResult = mysql.deleteDeliveryArea(da);
                if (insertResult == true)
                    System.out.println("Area Deleted");
                else
                    System.out.println("Failed: Area not deleted");

            } catch (DeliveryAreaException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryArea(stmt);
            }

        }
        if (target == deleteButtonDocket) {

            try {
                int id = Integer.parseInt(text3.getText());
                DeliveryDocket doc = new DeliveryDocket(id, id, id, id);
                // Delete Area
                boolean insertResult = mysql.deleteDeliveryDocket(doc);
                if (insertResult == true)
                    System.out.println("Docket Deleted");
                else
                    System.out.println("Failed: Docket not deleted");

            } catch (DeliveryDocketException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryDocket(stmt);
            }

        }

        if (target == insertButtonPublication) {


            try {
                double price = Double.parseDouble(text3.getText());
                Publication pub = new Publication(text1.getText(), text2.getText(), price);
                // Insert Publication Details into the database
                boolean insertResult = mysql.insertNewPublication(pub);
                if (insertResult == true)
                    System.out.println("New Publication Added");
                else
                    System.out.println("Failed: Publication not added");

            } catch (PublicationException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBPublication(stmt);
            }
        }


        if (target == insertButtonArea) {


            try {
                int size = Integer.parseInt(text1.getText());
                DeliveryArea da = new DeliveryArea(text.getText(), size);
                // Insert Publication Details into the database
                boolean insertResult = mysql.insertNewDeliveryArea(da);
                if (insertResult == true)
                    System.out.println("New Delivery Area Added");
                else
                    System.out.println("Failed: Area not added");

            } catch (DeliveryAreaException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryArea(stmt);
            }
        }

        if (target == insertButtonDocket) {


            try {
                int idp = Integer.parseInt(text.getText());
                int ida = Integer.parseInt(text1.getText());
                int idc = Integer.parseInt(text2.getText());
                DeliveryDocket doc = new DeliveryDocket(idp, ida, idc);
                // Insert Publication Details into the database
                boolean insertResult = mysql.insertDeliveryDocket(doc);
                if (insertResult == true)
                    System.out.println("New Docket Added");
                else
                    System.out.println("Failed: Docket not added");

            } catch (DeliveryDocketException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryDocket(stmt);
            }
        }


        if (target == updateButtonPublication) {


            try {
                double price = Double.parseDouble(text2.getText());
                Publication pub = new Publication(text.getText(), text1.getText(), price);
                // Insert Publication Details into the database
                boolean insertResult = mysql.updatePublication(pub);
                if (insertResult == true)
                    System.out.println("Publication Update");
                else
                    System.out.println("Failed: to Update");

            } catch (PublicationException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBPublication(stmt);
            }
        }
        if (target == updateButtonArea) {


            try {
                int id = Integer.parseInt(text2.getText());
                int size = Integer.parseInt(text1.getText());
                DeliveryArea da = new DeliveryArea(id, text.getText(), size);
                // Insert Publication Details into the database
                boolean insertResult = mysql.updateDeliveryArea(da);
                if (insertResult == true)
                    System.out.println("Area Updated");
                else
                    System.out.println("Failed: to Update the Area");

            } catch (DeliveryAreaException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryArea(stmt);
            }
        }
        if (target == updateButtonDoc) {


            try {
                int id = Integer.parseInt(text3.getText());
                int idp = Integer.parseInt(text.getText());
                int idc = Integer.parseInt(text2.getText());
                int ida = Integer.parseInt(text1.getText());
                DeliveryDocket da = new DeliveryDocket(id, idp, ida, idc);
                // Insert Publication Details into the database
                boolean insertResult = mysql.updateDeliveryDocket(da);
                if (insertResult == true)
                    System.out.println("Docket Updated");
                else
                    System.out.println("Failed: to Update the Docket");

            } catch (DeliveryDocketException sqle) {
                System.err.println("Error with  insert:\n" + sqle.toString());
            } finally {
                TableModel.refreshFromDBDeliveryDocket(stmt);
            }
        }

    }
}
