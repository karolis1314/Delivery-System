package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;

import controller.QueryTableModel;
import exceptions.PublicationException;

@SuppressWarnings("serial")
class NewsagentInterface extends JFrame
{
	private JButton btnLogin;
	private JTextField txtUserName, txtUserPass;
	
	private QueryTableModel qtm = new QueryTableModel();
	private TableModel tm; 
	private JTable table;
	
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
		
		menuPanel.add(tabs);
		return menuPanel;
	}
	
	JPanel Customers()
	{
		JPanel pnCus = new JPanel(null);
		try
		{
			ResultSet rs = qtm.displayCustomers();
			tm = new TableModel();
			tm.RefreshDatabase(rs);
			
			table = new JTable(tm);
			JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 460, 300);
			
			for(int i=0; i<table.getRowCount(); i++)
			{
				table.setRowHeight(i, 20);
			}
			
			table.getTableHeader().setPreferredSize(new Dimension(1, 25));
			table.getTableHeader().setBackground(Color.CYAN);

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
					if(e.getSource()==txtDetails[0])
						txtDetails[0].setText("");
					if(e.getSource()==txtDetails[1])
						txtDetails[1].setText("");
					if(e.getSource()==txtDetails[2])
						txtDetails[2].setText("");
					if(e.getSource()==txtDetails[3])
						txtDetails[3].setText("");
					if(e.getSource()==txtDetails[4])
						txtDetails[4].setText("");
				}
			});
		}
		insert = new JButton("Insert");
		update = new JButton("Update");
		delete = new JButton("Delete");
		
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
			tm = new TableModel();
			tm.RefreshDatabase(rs);
			
			table = new JTable(tm);
			JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 460, 300);
			
			for(int i=0; i<table.getRowCount(); i++)
			{
				table.setRowHeight(i, 20);
			}
			table.getTableHeader().setPreferredSize(new Dimension(1, 25));
			table.getTableHeader().setBackground(Color.CYAN);
			
			pnPub.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		return pnPub;
	}
	
	JPanel DeliveryArea()
	{
		JPanel pnDA = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllDeliveryArea();
			tm = new TableModel();
			tm.RefreshDatabase(rs);
			
			table = new JTable(tm);
			JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 460, 300);
			
			for(int i=0; i<table.getRowCount(); i++)
			{
				table.setRowHeight(i, 20);
			}
			table.getTableHeader().setPreferredSize(new Dimension(1, 25));
			table.getTableHeader().setBackground(Color.CYAN);
			
			pnDA.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}

		return pnDA;
	}
	
	JPanel DeliveryDocket()
	{
		JPanel pnDD = new JPanel(null);	
		try
		{	
			ResultSet rs = qtm.retrieveAllDeliveryDockets();
			tm = new TableModel();
			tm.RefreshDatabase(rs);
			
			table = new JTable(tm);
			JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setBounds(10, 10, 460, 300);
			
			for(int i=0; i<table.getRowCount(); i++)
			{
				table.setRowHeight(i, 20);
			}
			table.getTableHeader().setPreferredSize(new Dimension(1, 25));
			table.getTableHeader().setBackground(Color.CYAN);
			
			pnDD.add(sp);
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		return pnDD;
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
