package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import db.*;

public class AddStudent extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField regdNo;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddStudent() {
		
		super("Add New Student");
		
		con = ConnectDB.connectdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
						
				setBounds((width-500)/2, (height-450)/2, 500, 450);		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regd No.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 81, 71, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblBookName = new JLabel("Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(54, 125, 82, 17);
		contentPane.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Course");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition.setBounds(54, 169, 71, 17);
		contentPane.add(lblEdition);
		
		JLabel lblPublisher = new JLabel("Branch");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(54, 213, 71, 17);
		contentPane.add(lblPublisher);
		
		JLabel lblPrice = new JLabel("Year");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(54, 257, 71, 17);
		contentPane.add(lblPrice);
		
		JLabel lblPages = new JLabel("Semester");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages.setBounds(54, 301, 71, 17);
		contentPane.add(lblPages);
		
		regdNo = new JTextField();
		regdNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regdNo.setBounds(170, 78, 251, 24);
		contentPane.add(regdNo);
		regdNo.setColumns(10);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setColumns(10);
		name.setBounds(170, 122, 251, 24);
		contentPane.add(name);
		
		JComboBox course = new JComboBox();
		course.setFont(new Font("Tahoma", Font.PLAIN, 14));
		course.setModel(new DefaultComboBoxModel(new String[] {"B.TECH", "M.TECH", "BCA", "MCA", "B.SC", "M.SC", "BBA", "MBA", "B.AGRI"}));
		course.setBounds(170, 166, 251, 24);
		contentPane.add(course);
		
		JLabel lblNewLabel_1 = new JLabel("Add Student");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(54, 33, 130, 24);
		contentPane.add(lblNewLabel_1);
		
		JComboBox branch = new JComboBox();
		branch.setModel(new DefaultComboBoxModel(new String[] {"CSE", "CSIT", "ECE", "EE", "EEE", "ETC", "MECH", "CIVIL", "BIOTECH"}));
		branch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		branch.setBounds(170, 210, 251, 24);
		contentPane.add(branch);
		
		JComboBox year = new JComboBox();
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		year.setFont(new Font("Tahoma", Font.PLAIN, 14));
		year.setBounds(170, 254, 251, 24);
		contentPane.add(year);
		
		JComboBox semester = new JComboBox();
		semester.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		semester.setFont(new Font("Tahoma", Font.PLAIN, 14));
		semester.setBounds(170, 298, 251, 24);
		contentPane.add(semester);
		
		JButton addStudent = new JButton("Add");
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add button action in Add STUDENT window
				String sql = "insert into users(regd_no, name, password, answer, course, branch, year, semester) values(?,?,?,?,?,?,?,?)";
				
				try {
					
					ps = con.prepareStatement(sql);
					
					ps.setString(1, regdNo.getText());
					ps.setString(2, name.getText());
					ps.setString(3, regdNo.getText());
					ps.setString(4, name.getText());
					ps.setString(5, (String) course.getSelectedItem());
					ps.setString(6, (String) branch.getSelectedItem());
					ps.setString(7, (String) year.getSelectedItem());
					ps.setString(8, (String) semester.getSelectedItem());
					
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Student Added");
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
			}
		});
		addStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addStudent.setBounds(170, 349, 88, 29);
		contentPane.add(addStudent);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Back button action in add book window
				setVisible(false);
				AdminDashboard obj = new AdminDashboard();
				obj.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(333, 349, 88, 29);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 2));
		panel.setBounds(10, 20, 466, 372);
		contentPane.add(panel);
		panel.setLayout(null);
	}
}
