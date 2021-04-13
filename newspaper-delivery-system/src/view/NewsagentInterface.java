package view;

import controller.QueryTableModel;
import exceptions.CustomersException;
import exceptions.DeliveryAreaException;
import exceptions.DeliveryDocketException;
import exceptions.OrdersException;
import exceptions.PublicationException;
import exceptions.StaffException;
import model.Customers;
import model.Publication;
import model.Orders;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import model.StaffMember;
import model.DeliveryArea;

@SuppressWarnings("serial")
class NewsagentInterface extends JFrame
{
	private JButton btnLogin, back;
	private JTextField txtUserName, txtUserPass;
	private JMenuBar mb;
	
	private QueryTableModel qtm = new QueryTableModel();
	private TableModel customers, publications, deliveryarea, staffMem, printDoc, orders;
	private JTable custable, pubtable, datable, staffTable, docPrintTable, ordersTable;

	private Container ct;
	private CardLayout cl;
	private String title = "LoginScreen";

	NewsagentInterface() 
	{
		nimbus();
		
		cl = new CardLayout();		
		ct = getContentPane();
		ct.setBackground(Color.BLACK);
		
		setLayout(cl);
		setTitle(title);
		
		mb = new JMenuBar();
		JMenu menu, submenu;
		
		menu = new JMenu("Menu");
		back = new JButton("Exit");
		
		menu.add(back);
		mb.add(menu);
	
		ct.add("LogIn",LoginPanel());
		ct.add("Welcome",welcomePanel());
		ct.add("Publication", Publications());
		ct.add("Customers",Customers());
		ct.add("Staff",StaffMember());
		ct.add("Docket",PrintDocket());
		ct.add("Orders",ordersPanel());
		ct.add("Area",DeliveryArea());
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setVisible(true);
		requestFocus();
		requestFocusInWindow();
	}
	
	public static void main(String[] args)
	{
		new NewsagentInterface();
	}
	
	JPanel LoginPanel()
	{
		JPanel pnLogin = new JPanel(null);
		pnLogin.setBackground(Color.RED);
		pnLogin.setBounds(0, 0, 700, 500);
	
		txtUserName = new JTextField();
		txtUserName.setBounds(150, 150, 200, 40);
		txtUserName.setHorizontalAlignment(JTextField.CENTER);
		txtUserName.setText("Username");
		txtUserName.setFont(new Font("default", 0, 20));
		txtUserName.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e)
			{
				if(txtUserName.getText().isEmpty())
					txtUserName.setText("Username");
			}
			public void focusGained(FocusEvent e)
			{
				txtUserName.setText("");
			}
		});
		title="MainMenu";
		
		txtUserPass = new JPasswordField();
		txtUserPass.setBounds(150, 200, 200, 40);
		txtUserPass.setHorizontalAlignment(JTextField.CENTER);
		txtUserPass.setFont(new Font("default", 0, 20));
		txtUserPass.setText("Enter Password");
		txtUserPass.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e)
			{
				if(txtUserPass.getText().isEmpty())
					txtUserPass.setText("********");
			}
			public void focusGained(FocusEvent e) 
			{
				txtUserPass.setText("");
			}
		});
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(175, 260, 150, 40);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==btnLogin)
					if(txtUserName.getText().equals("root") && txtUserPass.getText().toString().equals("1234"))
					{
						setTitle(title);
						setJMenuBar(mb);
						cl.next(ct);
						txtUserName.setText("Username");
						txtUserPass.setText("********");
					}
			}
		});
		JButton exitNow = new JButton("Exit");
		exitNow.setBounds(175, 300, 150, 40);
		exitNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		pnLogin.add(txtUserName);
		pnLogin.add(txtUserPass);
		pnLogin.add(btnLogin);
		pnLogin.add(exitNow);
		
		return pnLogin;
	}

	
	JPanel ordersPanel()
	{
		JPanel pnOrders = new JPanel(null);
		try
		{
			ResultSet rs = qtm.displayOrders();
			orders = new TableModel();
			orders.RefreshDatabase(rs);
			
			ordersTable = new JTable(orders);
			JScrollPane sp  = new JScrollPane(ordersTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
			
			setCustableDimension();
			pnOrders.add(sp);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert, update, delete, goBack;
		
		String[] placeHolder = {"CustomerId","PublicationId","isActive"};
		JTextField[] txtDetails = new JTextField[3];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(18);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
					if(txtDetails[1].getText().isEmpty())
						txtDetails[1].setText(placeHolder[1]);
					if(txtDetails[2].getText().isEmpty())
						txtDetails[2].setText(placeHolder[2]);
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1] && txtDetails[1].getText().equals(placeHolder[1]))
						txtDetails[1].setText("");
					if(e.getSource()==txtDetails[2] && txtDetails[2].getText().equals(placeHolder[2]))
						txtDetails[2].setText("");
				}
			});
		}
		insert = new JButton("Insert");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cus_id = Integer.parseInt(txtDetails[0].getText());
				int pub_id = Integer.parseInt(txtDetails[1].getText());
				int text = Integer.parseInt(txtDetails[2].getText());
				boolean active=false;
				
				try
				{
					if(text==1)
						active=true;
					else
						active=false;
					
					Orders order = new Orders(cus_id, pub_id, active);
					qtm.insertOrder(order);
		
					ResultSet rs = qtm.displayOrders();
					orders.RefreshDatabase(rs);
				}
				catch(OrdersException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		update = new JButton("Update");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		
		delete = new JButton("Delete");
		delete.setFont(new Font("Garamond", 1, 15));
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = Integer.parseInt(txtDetails[2].getText());
				boolean active=false;
				try
				{
					if(res==1)
						active=true;
					else
						active=false;
//					qtm.deleteOrder(active);
					ResultSet rs = qtm.displayOrders();
					orders.RefreshDatabase(rs);
				}
				catch(OrdersException e1)
				{System.out.println(e1.getMessage());}
				
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});
		
		crud.add(insert);crud.add(update);crud.add(delete);crud.add(goBack);
		pnOrders.add(crud);
		return pnOrders;
	}
	JPanel Publications()
	{
		JPanel pnPub = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllPublications();
			publications = new TableModel();
			publications.RefreshDatabase(rs);
			
			pubtable = new JTable(publications);
			JScrollPane sp  = new JScrollPane(pubtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
		
			setPubtableDimension();
			pnPub.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert, update, delete, goBack;
		
		String[] placeHolder = {"ID","Frequency","Name","Price","Stock"};
		JTextField[] txtDetails = new JTextField[5];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(10);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
					if(txtDetails[1].getText().isEmpty())
						txtDetails[1].setText(placeHolder[1]);
					if(txtDetails[2].getText().isEmpty())
						txtDetails[2].setText(placeHolder[2]);
					if(txtDetails[3].getText().isEmpty())
						txtDetails[3].setText(placeHolder[3]);
					if(txtDetails[4].getText().isEmpty())
						txtDetails[4].setText(placeHolder[4]);
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1] && txtDetails[1].getText().equals(placeHolder[1]))
						txtDetails[1].setText("");
					if(e.getSource()==txtDetails[2] && txtDetails[2].getText().equals(placeHolder[2]))
						txtDetails[2].setText("");
					if(e.getSource()==txtDetails[3] && txtDetails[3].getText().equals(placeHolder[3]))
						txtDetails[3].setText("");
					if(e.getSource()==txtDetails[4] && txtDetails[4].getText().equals(placeHolder[4]))
						txtDetails[4].setText("");
				}
			});
		}
		insert = new JButton("Insert");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String freq = txtDetails[1].getText();
				String pName = txtDetails[2].getText();
				double pPrice = Double.parseDouble(txtDetails[3].getText());
				int stock = Integer.parseInt(txtDetails[4].getText());
				try
				{
					Publication pub = new Publication(20, freq, pName, pPrice, stock);
					qtm.insertNewPublication(pub);
					
					ResultSet rs = qtm.retrieveAllPublications();
					publications.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(PublicationException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		update = new JButton("Update");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				String freq = txtDetails[1].getText();
				String pName = txtDetails[2].getText();
				double pPrice = Double.parseDouble(txtDetails[3].getText());
				int stock = Integer.parseInt(txtDetails[4].getText());
				try
				{
					Publication pub = new Publication(ID, freq, pName, pPrice, stock);
					qtm.updatePublication(pub);
					
					ResultSet rs = qtm.retrieveAllPublications();
					publications.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(PublicationException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		
		delete = new JButton("Delete");
		delete.setFont(new Font("Garamond", 1, 15));
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				try
				{
					Publication pub = new Publication(ID, "14", "dummy", 12.20, 2);
					qtm.deletePublication(pub);
					
					ResultSet rs = qtm.retrieveAllPublications();
					publications.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(PublicationException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});
		
		
		
		crud.add(insert);crud.add(update);crud.add(delete);crud.add(goBack);
		pnPub.add(crud);
		return pnPub;
	}
	
	
	JPanel StaffMember()
	{
		JPanel mstaff = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllStaff();
			staffMem = new TableModel();
			staffMem.RefreshDatabase(rs);
			
			staffTable = new JTable(staffMem);
			JScrollPane sp  = new JScrollPane(staffTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
		
			setPubtableDimension();
			mstaff.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert, update, delete, goBack;
		
		String[] placeHolder = {"ID","Name","Surname","Password","Area ID"};
		JTextField[] txtDetails = new JTextField[5];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(10);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
					if(txtDetails[1].getText().isEmpty())
						txtDetails[1].setText(placeHolder[1]);
					if(txtDetails[2].getText().isEmpty())
						txtDetails[2].setText(placeHolder[2]);
					if(txtDetails[3].getText().isEmpty())
						txtDetails[3].setText(placeHolder[3]);
					if(txtDetails[4].getText().isEmpty())
						txtDetails[4].setText(placeHolder[4]);
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1] && txtDetails[1].getText().equals(placeHolder[1]))
						txtDetails[1].setText("");
					if(e.getSource()==txtDetails[2] && txtDetails[2].getText().equals(placeHolder[2]))
						txtDetails[2].setText("");
					if(e.getSource()==txtDetails[3] && txtDetails[3].getText().equals(placeHolder[3]))
						txtDetails[3].setText("");
					if(e.getSource()==txtDetails[4] && txtDetails[4].getText().equals(placeHolder[4]))
						txtDetails[4].setText("");
				}
			});
		}
		insert = new JButton("Insert");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtDetails[1].getText();
				String lName = txtDetails[2].getText();
				String pass = txtDetails[3].getText();
				int areaId = Integer.parseInt(txtDetails[4].getText());
				try
				{
					StaffMember staff = new StaffMember(1, name, lName, pass, areaId);
					qtm.insertNewStaff(staff);
					
					ResultSet rs = qtm.retrieveAllStaff();
					staffMem.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(StaffException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		update = new JButton("Update");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				String name = txtDetails[1].getText();
				String lName = txtDetails[2].getText();
				String pass = txtDetails[3].getText();
				int areaId = Integer.parseInt(txtDetails[4].getText());
				try
				{
					StaffMember staff = new StaffMember(ID, name, lName, pass, areaId);
					qtm.updateStaff(staff);
					
					ResultSet rs = qtm.retrieveAllStaff();
					staffMem.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(StaffException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		
		delete = new JButton("Delete");
		delete.setFont(new Font("Garamond", 1, 15));
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				try
				{
					StaffMember staff = new StaffMember(ID, "name", "surname", "password", 2);
					qtm.deleteStaff(staff);
					
					ResultSet rs = qtm.retrieveAllStaff();
					staffMem.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(StaffException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});
		
		
		crud.add(insert);crud.add(update);crud.add(delete);crud.add(goBack);
		mstaff.add(crud);
		return mstaff;
	}
	
	JPanel Customers()
	{
		JPanel pnCus = new JPanel(null);
		try
		{
			ResultSet rs = qtm.displayCustomers();
			customers = new TableModel();
			customers.RefreshDatabase(rs);
			
			custable = new JTable(customers);
			JScrollPane sp  = new JScrollPane(custable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
			
			setCustableDimension();
			pnCus.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert, update, delete, goBack;
		
		String[] placeHolder = {"Id","Address","FirstName","LastName","Number","AreaID"};
		JTextField[] txtDetails = new JTextField[6];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(7);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
					if(txtDetails[1].getText().isEmpty())
						txtDetails[1].setText(placeHolder[1]);
					if(txtDetails[2].getText().isEmpty())
						txtDetails[2].setText(placeHolder[2]);
					if(txtDetails[3].getText().isEmpty())
						txtDetails[3].setText(placeHolder[3]);
					if(txtDetails[4].getText().isEmpty())
						txtDetails[4].setText(placeHolder[4]);
					if(txtDetails[5].getText().isEmpty())
						txtDetails[5].setText(placeHolder[5]);
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1] && txtDetails[1].getText().equals(placeHolder[1]))
						txtDetails[1].setText("");
					if(e.getSource()==txtDetails[2] && txtDetails[2].getText().equals(placeHolder[2]))
						txtDetails[2].setText("");
					if(e.getSource()==txtDetails[3] && txtDetails[3].getText().equals(placeHolder[3]))
						txtDetails[3].setText("");
					if(e.getSource()==txtDetails[4] && txtDetails[4].getText().equals(placeHolder[4]))
						txtDetails[4].setText("");
					if(e.getSource()==txtDetails[5] && txtDetails[5].getText().equals(placeHolder[5]))
						txtDetails[5].setText("");
				}
			});
		}
		
		insert = new JButton("Insert");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cus_id = Integer.parseInt(txtDetails[0].getText());
				String add = txtDetails[1].getText();
				String fname = txtDetails[2].getText();
				String lname = txtDetails[3].getText();
				String number = txtDetails[4].getText();
				int areaId = Integer.parseInt(txtDetails[5].getText());
				
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				try
				{
					Customers cus = new Customers(cus_id, add, fname, lname, prefix, digits, areaId);
					qtm.insertCustomerInfo(cus);
		
					ResultSet rs = qtm.displayCustomers();
					customers.RefreshDatabase(rs);
					setCustableDimension();
				}
				catch(CustomersException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		update = new JButton("Update");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cus_id = Integer.parseInt(txtDetails[0].getText());
				String add = txtDetails[1].getText();
				String fname = txtDetails[2].getText();
				String lname = txtDetails[3].getText();
				String number = txtDetails[4].getText();
				int areaId = Integer.parseInt(txtDetails[5].getText());
				
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				try
				{
					Customers cus = new Customers(cus_id, add, fname, lname, prefix, digits, areaId);
					qtm.updateCustomerDetails(cus);
					ResultSet rs = qtm.displayCustomers();
					customers.RefreshDatabase(rs);
					setCustableDimension();
				}
				catch (Exception e1) 
				{
					System.out.println(e1.getMessage());
				}
				
			}
		});
		
		delete = new JButton("Delete");
		delete.setFont(new Font("Garamond", 1, 15));
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cus_id = Integer.parseInt(txtDetails[0].getText());
				try
				{
					qtm.deleteCustomerById(cus_id);
					ResultSet rs = qtm.displayCustomers();
					customers.RefreshDatabase(rs);
					setCustableDimension();
				}
				catch (Exception e1) 
				{
					System.out.println(e1.getMessage());
				}
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});
		
		crud.add(insert);crud.add(update);crud.add(delete);crud.add(goBack);
		pnCus.add(crud);
		return pnCus;
	}
	
	JPanel PrintDocket()
	{
		JPanel docPri = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllDeliveryDockets();
			printDoc = new TableModel();
			printDoc.RefreshDatabase(rs);
			
			docPrintTable = new JTable(printDoc);
			JScrollPane sp  = new JScrollPane(docPrintTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
		
			docPri.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert,update,week2, monthly, goBack;
		
		String[] placeHolder = {"Staff ID"};
		JTextField[] txtDetails = new JTextField[1];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(4);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
				}
			});
		}
		insert = new JButton("Daily");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{

					qtm.createDailyDeliveryDocket();
					
					ResultSet rs = qtm.retrieveAllDeliveryDockets();
					printDoc.RefreshDatabase(rs);
				}
				catch(DeliveryDocketException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		
		update = new JButton("Weekly");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					qtm.createWeeklyDeliveryDocket();

					ResultSet rs = qtm.retrieveAllDeliveryDockets();
					printDoc.RefreshDatabase(rs);
				}
				catch(DeliveryDocketException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		
		week2 = new JButton("2-Weeks");
		week2.setFont(new Font("Garamond", 1, 15));
		week2.setPreferredSize(new Dimension(150,30));
		week2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					qtm.createBiWeeklyDeliveryDocket();
					
					ResultSet rs = qtm.retrieveAllDeliveryDockets();
					printDoc.RefreshDatabase(rs);
				}
				catch(DeliveryDocketException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		monthly = new JButton("Monthly");
		monthly.setFont(new Font("Garamond", 1, 15));
		monthly.setPreferredSize(new Dimension(150,30));
		monthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					qtm.createMonthlyDeliveryDocket();
	
					ResultSet rs = qtm.retrieveAllDeliveryDockets();
					printDoc.RefreshDatabase(rs);
				}
				catch(DeliveryDocketException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});

		crud.add(insert);crud.add(update);crud.add(week2);crud.add(monthly);crud.add(goBack);
		docPri.add(crud);
		return docPri;
	}
	
	JPanel DeliveryArea()
	{
		JPanel pnDA = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllDeliveryArea();
			deliveryarea = new TableModel();
			deliveryarea.RefreshDatabase(rs);
			
			datable = new JTable(deliveryarea);
			JScrollPane sp  = new JScrollPane(datable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 670, 300);
			
			setDATableDimension();
			pnDA.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 670, 130);
		crud.setBackground(Color.gray);
		
		JButton insert, update, delete, goBack;
		
		String[] placeHolder = {"ID","Area Name"};
		JTextField[] txtDetails = new JTextField[2];
		
		for(int i=0; i<txtDetails.length; i++)
		{
			txtDetails[i] = new JTextField(7);
			crud.add(txtDetails[i]);
			txtDetails[i].setHorizontalAlignment(JTextField.CENTER);
			txtDetails[i].setText(placeHolder[i]);
			txtDetails[i].addFocusListener(new FocusListener() 
			{
				public void focusLost(FocusEvent e) 
				{
					if(txtDetails[0].getText().isEmpty())
						txtDetails[0].setText(placeHolder[0]);
					if(txtDetails[1].getText().isEmpty())
						txtDetails[1].setText(placeHolder[1]);
			
				}
				public void focusGained(FocusEvent e) 
				{
					if(e.getSource()==txtDetails[0] && txtDetails[0].getText().equals(placeHolder[0]))
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1] && txtDetails[1].getText().equals(placeHolder[1]))
						txtDetails[1].setText("");
				
				}
			});
		}
		insert = new JButton("Insert");
		insert.setFont(new Font("Garamond", 1, 15));
		insert.setPreferredSize(new Dimension(150,30));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtDetails[1].getText();
				
				try
				{
					DeliveryArea area = new DeliveryArea(name);
					qtm.insertNewDeliveryArea(area);
					
					ResultSet rs = qtm.retrieveAllDeliveryArea();
					deliveryarea.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(DeliveryAreaException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		update = new JButton("Update");
		update.setFont(new Font("Garamond", 1, 15));
		update.setPreferredSize(new Dimension(150,30));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				String name = txtDetails[1].getText();
				
				try
				{
					DeliveryArea area = new DeliveryArea(ID, name);
					qtm.updateDeliveryArea(area);
					
					ResultSet rs = qtm.retrieveAllDeliveryArea();
					deliveryarea.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(DeliveryAreaException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		
		delete = new JButton("Delete");
		delete.setFont(new Font("Garamond", 1, 15));
		delete.setPreferredSize(new Dimension(150,30));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(txtDetails[0].getText());
				try
				{
					DeliveryArea area = new DeliveryArea(ID, "Deleted");
					qtm.deleteDeliveryArea(area);
					
					ResultSet rs = qtm.retrieveAllDeliveryArea();
					deliveryarea.RefreshDatabase(rs);
					setPubtableDimension();
				}
				catch(DeliveryAreaException e1)
				{System.out.println(e1.getMessage());}
			}
		});
		goBack = new JButton("Back");
		goBack.setFont(new Font("Garamond", 1, 15));
		goBack.setBackground(Color.red);
		goBack.setPreferredSize(new Dimension(150,30));
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(ct, "Welcome" );
			}
		});
		
		
		crud.add(insert);crud.add(update);crud.add(delete);crud.add(goBack);
		pnDA.add(crud);
		return pnDA;
	}
	JPanel welcomePanel()
	{
		
		qtm.openConnection();
		JPanel pnWelcome=new JPanel(null);
		pnWelcome.setBackground(Color.CYAN);
		
			
		 
		
			
		JButton pub = new JButton("Publications");
		pub.setBounds(10, 50, 150, 50);
		pub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Publication");
			}
		});
		JButton cust = new JButton("Customer");
		cust.setBounds(10, 100, 150, 50);
		cust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Customers");
			}
		});
		JButton order = new JButton("Order");
		order.setBounds(10, 150, 150, 50);
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Orders");
			}
		});
		JButton staff = new JButton("Staff Member");
		staff.setBounds(10, 200, 150, 50);
		staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Staff");
			}
		});
		JButton doc = new JButton("Delivery Docket");
		doc.setBounds(10, 250, 150, 50);
		doc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Docket");
			}
		});
		JButton area = new JButton("Delivery Area");
		area.setBounds(10, 300, 150, 50);
		area.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "Area");
			}
		});
		
		JButton invoice = new JButton("Get Invoices");
		invoice.setBounds(10, 350, 150, 50);
		invoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					qtm.createInvoice();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton logOff = new JButton("Log Off");
		logOff.setBounds(550, 350, 100, 50);
		logOff.setBackground(Color.red);
		logOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.show(ct, "LogIn");
			}
		});
		
		pnWelcome.add(pub); pnWelcome.add(cust);pnWelcome.add(staff);pnWelcome.add(doc);pnWelcome.add(area);pnWelcome.add(order); pnWelcome.add(invoice); pnWelcome.add(logOff);
		return pnWelcome;
	}
	
	void setCustableDimension()
	{
		for(int i=0; i<custable.getRowCount(); i++)
		{
			custable.setRowHeight(i, 20);
		}
		custable.getTableHeader().setPreferredSize(new Dimension(1, 25));
		custable.getTableHeader().setBackground(Color.CYAN);
	}
	void setPubtableDimension()
	{
		for(int i=0; i<pubtable.getRowCount(); i++)
		{
			pubtable.setRowHeight(i, 20);
		}
		pubtable.getTableHeader().setPreferredSize(new Dimension(1, 25));
		pubtable.getTableHeader().setBackground(Color.CYAN);
	}

	void setDATableDimension()
	{
		for(int i=0; i<datable.getRowCount(); i++)
		{
			datable.setRowHeight(i, 20);
		}
		datable.getTableHeader().setPreferredSize(new Dimension(1, 25));
		datable.getTableHeader().setBackground(Color.CYAN);
	}
	void nimbus()
	{
		try
		{
			for(LookAndFeelInfo ui : UIManager.getInstalledLookAndFeels())
			{
				if("Nimbus".equals(ui.getName()))
				{
					UIManager.setLookAndFeel(ui.getClassName());
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}