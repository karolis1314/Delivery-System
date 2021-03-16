package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.QueryTableModel;

@SuppressWarnings("serial")
class NewsagentInterface extends JFrame
{
	private JButton btnLogin;
	private JTextField txtUserName, txtUserPass;
	
	private QueryTableModel qtm = new QueryTableModel();
	private TableModel tm = new TableModel();
	private JTable table = new JTable(tm);
	
	private Container ct;
	private CardLayout cl;

	NewsagentInterface() 
	{
		nimbus();
		
		cl = new CardLayout();		
		ct = getContentPane();
		ct.setBackground(Color.BLACK);
		
		
		setLayout(cl);
	
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
		txtUserName.setText("Enter Username");
		txtUserName.setFont(new Font("default", 0, 20));
		txtUserName.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e)
			{
				if(txtUserName.getText().isEmpty())
					txtUserName.setText("Enter Username");
			}
			public void focusGained(FocusEvent e) 
			{
				txtUserName.setText("");
			}
		});
		
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
		JPanel menuPanel = new JPanel(new BorderLayout());
		qtm.openConnection();
		
		JPanel pnPub = new JPanel();
		JPanel pnArea = new JPanel();
		JPanel pnDocket = new JPanel();
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Customers", Customers());
		tabs.addTab("Publications", pnPub);
		tabs.addTab("Delivery Area", pnArea);
		tabs.addTab("Delivery Docket", pnDocket);
		
		menuPanel.add(tabs);
		return menuPanel;
	}
	
	JPanel Customers()
	{
		JPanel pnCus = new JPanel(null);
		
		JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(10, 10, 460, 300);
		
		ResultSet rs = qtm.displayCustomers();
		tm.RefreshDatabase(rs);
		
		for(int i=0; i<table.getRowCount(); i++)
		{
			table.setRowHeight(i, 20);
		}
		
		table.getTableHeader().setPreferredSize(new Dimension(1, 25));
		table.getTableHeader().setBackground(Color.CYAN);
		
		pnCus.add(sp);
		return pnCus;
	}
	
	JPanel Publications()
	{
		JPanel pnPub = new JPanel(null);
		
		JScrollPane sp  = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(10, 10, 460, 300);
		
		ResultSet rs = qtm.displayCustomers();
		tm.RefreshDatabase(rs);
		
		for(int i=0; i<table.getRowCount(); i++)
		{
			table.setRowHeight(i, 20);
		}
		
		table.getTableHeader().setPreferredSize(new Dimension(1, 25));
		table.getTableHeader().setBackground(Color.CYAN);
		
		pnPub.add(sp);
		return pnPub;
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
