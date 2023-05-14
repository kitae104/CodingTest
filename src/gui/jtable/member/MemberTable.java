package gui.jtable.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class MemberTable extends JFrame
{

	private JPanel contentPane;
	private JTextField txtTest;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

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
					MemberTable frame = new MemberTable();
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
	public MemberTable()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel.setBounds(12, 22, 962, 731);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(23, 23, 915, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("사용자 정보 테이블");
		lblNewLabel.setFont(new Font("D2Coding", Font.BOLD, 50));
		lblNewLabel.setBounds(228, 10, 507, 72);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel_2.setBounds(12, 122, 580, 599);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblNewLabel_1.setBounds(37, 40, 105, 29);
		panel_2.add(lblNewLabel_1);
		
		txtTest = new JTextField();
		txtTest.setText("test");
		txtTest.setFont(new Font("D2Coding", Font.BOLD, 24));
		txtTest.setBounds(154, 37, 373, 35);
		panel_2.add(txtTest);
		txtTest.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(37, 82, 105, 29);
		panel_2.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setText("test");
		textField.setFont(new Font("D2Coding", Font.BOLD, 24));
		textField.setColumns(10);
		textField.setBounds(154, 79, 373, 35);
		panel_2.add(textField);
		
		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblNewLabel_1_2.setBounds(37, 130, 105, 29);
		panel_2.add(lblNewLabel_1_2);
		
		textField_1 = new JTextField();
		textField_1.setText("test");
		textField_1.setFont(new Font("D2Coding", Font.BOLD, 24));
		textField_1.setColumns(10);
		textField_1.setBounds(154, 127, 373, 35);
		panel_2.add(textField_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("New label");
		lblNewLabel_1_3.setFont(new Font("D2Coding", Font.BOLD, 24));
		lblNewLabel_1_3.setBounds(37, 175, 105, 29);
		panel_2.add(lblNewLabel_1_3);
		
		textField_2 = new JTextField();
		textField_2.setText("test");
		textField_2.setFont(new Font("D2Coding", Font.BOLD, 24));
		textField_2.setColumns(10);
		textField_2.setBounds(154, 172, 373, 35);
		panel_2.add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 229, 524, 342);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel_2_1.setBounds(596, 122, 354, 599);
		panel.add(panel_2_1);
	}
}
