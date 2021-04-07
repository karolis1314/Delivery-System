package view;

import controller.QueryTableModel;
import exceptions.CustomersException;
import exceptions.PublicationException;
import exceptions.StaffException;
import model.Customers;
import model.Publication;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import model.StaffMember;

@SuppressWarnings("serial")
class NewsagentInterface extends JFrame
{
	private JButton btnLogin;
	private JTextField txtUserName, txtUserPass;
	private JMenuBar mb;
	
	private QueryTableModel qtm = new QueryTableModel();
	private TableModel customers, publications, deliverydocket, deliveryarea, staffMem;
	private JTable custable, pubtable, ddtable, datable, staffTable;

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
	
		ct.add(LoginPanel());
		ct.add(welcomePanel());
		ct.add(MainMenu());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
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
		pnLogin.setBounds(0, 0, 500, 500);
	
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
					}
			}
		});
		pnLogin.add(txtUserName);
		pnLogin.add(txtUserPass);
		pnLogin.add(btnLogin);
		
		return pnLogin;
	}
	JPanel MainMenu()
	{
		qtm.openConnection();
		JPanel menuPanel = new JPanel(new BorderLayout());
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Customers", Customers());
		tabs.addTab("Publications", Publications());
		tabs.addTab("Delivery Area", DeliveryArea());
		tabs.addTab("Delivery Docket", DeliveryDocket());
		tabs.add("Customer Orders", Orders());
		tabs.addTab("Staff Member", StaffMember());
		
		menuPanel.add(tabs);
		return menuPanel;
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
			sp.setBounds(10, 10, 460, 300);
		
			setPubtableDimension();
			mstaff.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 460, 100);
		crud.setBackground(Color.RED);
		
		JButton insert, update, delete;
		
		String[] placeHolder = {"ID","Name","Surname","Password","Area ID"};
		JTextField[] txtDetails = new JTextField[5];
		
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
		
		
		crud.add(insert);crud.add(update);crud.add(delete);
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
			sp.setBounds(10, 10, 460, 300);
			
			setCustableDimension();
			pnCus.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 460, 100);
		crud.setBackground(Color.RED);
		
		JButton insert, update, delete;
		
		String[] placeHolder = {"Id","Address","FirstName","LastName","Number"};
		JTextField[] txtDetails = new JTextField[5];
		
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
		insert .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cus_id = Integer.parseInt(txtDetails[0].getText());
				String add = txtDetails[1].getText();
				String fname = txtDetails[2].getText();
				String lname = txtDetails[3].getText();
				String number = txtDetails[4].getText();
				
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				try
				{
					Customers cus = new Customers(cus_id, add, fname, lname, prefix, digits);
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
				
				String prefix = number.substring(0, 3);
				String digits = number.substring(3, number.length());
				try
				{
					Customers cus = new Customers(cus_id, add, fname, lname, prefix, digits);
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
		
		crud.add(insert);crud.add(update);crud.add(delete);
		pnCus.add(crud);
		return pnCus;
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
			sp.setBounds(10, 10, 460, 300);
		
			setPubtableDimension();
			pnPub.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 460, 100);
		crud.setBackground(Color.RED);
		
		JButton insert, update, delete;
		
		String[] placeHolder = {"ID","Frequency","Name","Price","Stock"};
		JTextField[] txtDetails = new JTextField[5];
		
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
		
		
		crud.add(insert);crud.add(update);crud.add(delete);
		pnPub.add(crud);
		return pnPub;
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
			sp.setBounds(10, 10, 460, 300);
			
			setDATableDimension();
			pnDA.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 460, 100);
		crud.setBackground(Color.RED);
		
		pnDA.add(crud);
		return pnDA;
	}
	JPanel DeliveryDocket()
	{
		JPanel pnDD = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllDeliveryDockets();
			deliverydocket = new TableModel();
			deliverydocket.RefreshDatabase(rs);
			
			ddtable = new JTable(deliverydocket);
			JScrollPane sp  = new JScrollPane(ddtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 460, 300);
			
			setDDTableDimension();
			pnDD.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		JPanel crud = new JPanel(new FlowLayout(0,1,5));
		crud.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crud.setBounds(10, 320, 460, 100);
		crud.setBackground(Color.RED);
		
		pnDD.add(crud);
		return pnDD;
	}
	JPanel Orders()
	{
		JPanel pnOrders = new JPanel();
		pnOrders.setBackground(Color.BLACK);
		
		return pnOrders;
	}
	JPanel welcomePanel()
	{
		JPanel pnWelcome=new JPanel(null);
		pnWelcome.setBackground(Color.ORANGE);
		 
		mb = new JMenuBar();
		JMenu menu, submenu;
		
		menu = new JMenu("Menu");
		submenu = new JMenu("Show");
		String[] menuOptions = {"Users", "Publications", "Delivery Area", "Exit"};
		for(String op: menuOptions)
		{
			submenu.add(op);
		}
		menu.add(submenu);
		mb.add(menu);
			
		JButton next = new JButton("Next Panel");
		next.setBounds(10, 50, 100, 50);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setJMenuBar(null);
				cl.next(ct);
			}
		});
		
		pnWelcome.add(next);
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
	void setDDTableDimension()
	{
		for(int i=0; i<ddtable.getRowCount(); i++)
		{
			ddtable.setRowHeight(i, 20);
		}
		ddtable.getTableHeader().setPreferredSize(new Dimension(1, 25));
		ddtable.getTableHeader().setBackground(Color.CYAN);
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
