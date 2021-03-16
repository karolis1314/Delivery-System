package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class TableModel extends AbstractTableModel
{
	private Vector<String[]> modelData; 
	private int colCount;
	private String[] headers=new String[0] ;
	private Connection con;
	private Statement stmt = null;
	private String[] record;
	private ResultSet rs = null;
	private String command;
	
	void QueryTableModel()
	{
		modelData = new Vector<String[]>();
	}

	public String getColumnName(int i)
	{
		return headers[i];
	}	
	public int getColumnCount()
	{
		return colCount;
	}
	
	public int getRowCount()
	{
		return modelData.size();
	}
	
	public Object getValueAt(int row, int col)
	{
		return ((String[])modelData.elementAt(row))[col];
	}

	public void RefreshDatabase(String cmd, Statement stmt1)
	{
		modelData = new Vector<String[]>();
		command = cmd;stmt = stmt1;
		try
		{
			rs = stmt.executeQuery(command);
			ResultSetMetaData meta = rs.getMetaData();

			colCount = meta.getColumnCount(); 
			headers = new String[colCount];
	
			for(int h=1; h<=colCount; h++)
			{
				headers[h-1] = meta.getColumnName(h);
			}
			while(rs.next())
			{
				record = new String[colCount];
				
				for(int i=1; i<=colCount; i++)
				{
					record[i-1] = rs.getString(i);
				}
				modelData.addElement(record);
			}
			fireTableChanged(null); 
		}
		catch(Exception e) 
		{
			System.out.println("Error with refreshFromDB: " + e.getMessage());
		} 
	}
}
