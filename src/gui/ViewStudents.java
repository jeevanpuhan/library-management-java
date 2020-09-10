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
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

import db.*;
import lms.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewStudents extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTable studentTable;
	private JTextField usernameField;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudents frame = new ViewStudents();
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
	public ViewStudents() {
		
		super("View Students");
		
		con = ConnectDB.connectdb();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
		setBounds((width-1200)/2, (height-450)/2, 1200, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 20, 790, 372);
		contentPane.add(scrollPane);
		
		studentTable = new JTable();
		scrollPane.setViewportView(studentTable);
		studentTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		studentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Regd No.", "Name", "Password", "Security Question", "Answer", "Course", "Branch", "Year", "Semester"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		studentTable.getColumnModel().getColumn(4).setMinWidth(30);
		
		JLabel lblNewLabel = new JLabel("Regd No.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 70, 71, 17);
		contentPane.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setColumns(10);
		usernameField.setBounds(139, 67, 217, 24);
		contentPane.add(usernameField);
		
		JLabel lblBookName = new JLabel("Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(23, 114, 82, 17);
		contentPane.add(lblBookName);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(139, 111, 217, 24);
		contentPane.add(nameField);
		
		JLabel lblEdition = new JLabel("Course");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition.setBounds(23, 158, 71, 17);
		contentPane.add(lblEdition);
		
		JComboBox courseField = new JComboBox();
		courseField.setModel(new DefaultComboBoxModel(new String[] {"", "B.TECH", "M.TECH", "BCA", "MCA", "B.SC", "M.SC", "BBA", "MBA", "B.AGRI"}));
		courseField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseField.setBounds(139, 155, 217, 24);
		contentPane.add(courseField);
		
		JLabel lblPublisher = new JLabel("Branch");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(23, 202, 71, 17);
		contentPane.add(lblPublisher);
		
		JComboBox branchField = new JComboBox();
		branchField.setModel(new DefaultComboBoxModel(new String[] {"", "CSE", "CSIT", "ECE", "EE", "EEE", "ETC", "MECH", "CIVIL", "BIOTECH"}));
		branchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		branchField.setBounds(139, 199, 217, 24);
		contentPane.add(branchField);
		
		JLabel lblPrice = new JLabel("Year");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(23, 246, 71, 17);
		contentPane.add(lblPrice);
		
		JComboBox yearField = new JComboBox();
		yearField.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5"}));
		yearField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yearField.setBounds(139, 243, 217, 24);
		contentPane.add(yearField);
		
		JLabel lblPages = new JLabel("Semester");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages.setBounds(23, 290, 71, 17);
		contentPane.add(lblPages);
		
		JComboBox semesterField = new JComboBox();
		semesterField.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		semesterField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		semesterField.setBounds(139, 287, 217, 24);
		contentPane.add(semesterField);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view button action in view students
				
				clearTable();
				studentTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"#", "Regd No.", "Name", "Password", "Security Question", "Answer", "Course", "Branch", "Year", "Semester"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
					studentTable.getColumnModel().getColumn(4).setMinWidth(30);
				
				String sql = "select * from users where regd_no like '%"+usernameField.getText()+"%' and name like '%"+nameField.getText()+"%' and course like '%"+(String) courseField.getSelectedItem()+"%' and branch like '%"+(String) branchField.getSelectedItem()+"%' and year like '%"+(String) yearField.getSelectedItem()+"%' and semester like '%"+(String) semesterField.getSelectedItem()+"%'";
				
				try {
					
					ps = con.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					if(rs.next()) {
						
						showStudentTable(sql);
						
						rs.close();
						ps.close();
						
					}
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
				}
				
				finally {
					
					try {
						
						rs.close();
						ps.close();
						
					} catch (Exception e3) {
						
					}
					
				}
				
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnView.setBounds(139, 338, 88, 29);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back button action in view student window
				
				setVisible(false);
				AdminDashboard obj = new AdminDashboard();
				obj.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(268, 338, 88, 29);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 2));
		panel.setBounds(10, 20, 361, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("View Students");
		lblNewLabel_1.setBounds(10, 10, 142, 24);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		
	}
	
	public ArrayList<Student> studentList(String sql) {
		
		ArrayList<Student> studentList = new ArrayList<>();
		
		try {
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Student student;
			while(rs.next()) {
				
				student = new Student(rs.getString("regd_no"), rs.getString("name"),rs.getString("password"),rs.getString("security"),rs.getString("answer"),rs.getString("course"),rs.getString("branch"),rs.getString("year"),rs.getString("semester"));
				studentList.add(student);
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		return studentList;
		
	}
	
	public void showStudentTable(String sql) {
		
		ArrayList<Student> list = studentList(sql);
		DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
		Object[] row = new Object[10];
		
		for (int i = 0; i < list.size(); i++) {
			
			row[0] = (i+1);
			row[1] = list.get(i).getRegdNo();
			row[2] = list.get(i).getName();
			row[3] = list.get(i).getPassword();
			row[4] = list.get(i).getSecurity();
			row[5] = list.get(i).getAnswer();
			row[6] = list.get(i).getCourse();
			row[7] = list.get(i).getBranch();
			row[8] = list.get(i).getYear();
			row[9] = list.get(i).getSemester();
			model.addRow(row);
		}
	}
	
	public void clearTable() {
		DefaultTableModel tModel = (DefaultTableModel) studentTable.getModel();
		tModel.setRowCount(0);
	}
	
}
