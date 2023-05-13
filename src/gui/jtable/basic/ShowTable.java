package gui.jtable.basic;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShowTable extends JFrame
{

	private JPanel contentPane;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ShowTable frame = new ShowTable();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowTable()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShow = new JButton("Display Data");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jpastudy", "root", "1234");
					Statement stmt = conn.createStatement();
					String query = "select * from sales";
					ResultSet rs = stmt.executeQuery(query);
					
					// 메타 데이터 가져오기
					ResultSetMetaData metaData = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
					int cols = metaData.getColumnCount();
					String[] colName = new String[cols];
					
					for (int i = 0; i < colName.length; i++)
					{
						colName[i] = metaData.getColumnName(i+1);
					}
					
					model.setColumnIdentifiers(colName);
					
					String saleDate, productName;
					int seqNo, price, salesCount;
					
					while(rs.next()) {
						saleDate = rs.getString(1);
						seqNo = rs.getInt(2);
						price = rs.getInt(3);
						productName = rs.getString(4);
						salesCount = rs.getInt(5);
						
						Object[] row = {saleDate, seqNo, price, productName, salesCount};
						model.addRow(row);
					}
					stmt.close();
					conn.close();
				} 
				catch (ClassNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShow.setFont(new Font("D2Coding", Font.BOLD, 14));
		btnShow.setBounds(32, 73, 146, 37);
		contentPane.add(btnShow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 23, 535, 413);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		tblData.setFont(new Font("D2Coding", Font.PLAIN, 12));
		scrollPane.setViewportView(tblData);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tblData.getModel();
				model.setRowCount(0);
				
			}
		});
		btnClear.setFont(new Font("D2Coding", Font.BOLD, 14));
		btnClear.setBounds(32, 150, 146, 37);
		contentPane.add(btnClear);
	}
}
