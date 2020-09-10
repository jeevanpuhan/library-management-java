package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.ConnectDB;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class StudentProfile extends JFrame {
	
	
	static String user;
	static String pass;
	
	static String name;
	static String security;
	static String answer;
	static String course;
	static String branch;
	static String year;
	static String semester;
	
//	static 
//	{
//		user = Session.username;
//		pass = Session.password;
//		
//		name = Session.name;
//		security = Session.security;
//		answer = Session.answer;
//		course = Session.course;
//		branch = Session.branch;
//		year = Session.year;
//		semester = Session.semester;
//	}
	
	{
		user = Session.username;
		pass = Session.password;
		
		name = Session.name;
		security = Session.security;
		answer = Session.answer;
		course = Session.course;
		branch = Session.branch;
		year = Session.year;
		semester = Session.semester;
	}
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField nameField;
	private JTextField passField;
	private JTextField answerField;
	private JButton btnUpdate;
	private JButton btnBack;
	private JButton btnReset;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentProfile frame = new StudentProfile();
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
	public StudentProfile() {
		
		super("Student Details");
		con = ConnectDB.connectdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
				setBounds((width-424)/2, (height-609)/2, 424, 609);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(51, 97, 74, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(51, 130, 74, 23);
		contentPane.add(lblName);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setBounds(129, 97, 214, 23);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(129, 134, 214, 23);
		contentPane.add(nameField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(51, 163, 74, 23);
		contentPane.add(lblPassword);
		
		JLabel lblSecurity = new JLabel("Security");
		lblSecurity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecurity.setBounds(51, 196, 74, 23);
		contentPane.add(lblSecurity);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnswer.setBounds(51, 229, 74, 23);
		contentPane.add(lblAnswer);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCourse.setBounds(51, 264, 74, 23);
		contentPane.add(lblCourse);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBranch.setBounds(51, 301, 74, 23);
		contentPane.add(lblBranch);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(51, 334, 74, 23);
		contentPane.add(lblYear);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSemester.setBounds(51, 367, 74, 23);
		contentPane.add(lblSemester);
		
		passField = new JTextField();
		passField.setEditable(false);
		passField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passField.setColumns(10);
		passField.setBounds(129, 167, 214, 23);
		contentPane.add(passField);
		
		answerField = new JTextField();
		answerField.setEditable(false);
		answerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		answerField.setColumns(10);
		answerField.setBounds(129, 233, 214, 23);
		contentPane.add(answerField);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back button action in student details window
				
				setVisible(false);
				StudentDashboard obj = new StudentDashboard();
				obj.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(158, 500, 81, 33);
		contentPane.add(btnBack);
		
		JComboBox securityField = new JComboBox();
		securityField.setEnabled(false);
		securityField.setBounds(129, 199, 214, 23);
		contentPane.add(securityField);
		
		JComboBox courseField = new JComboBox();
		courseField.setEnabled(false);
		courseField.setBounds(129, 266, 214, 23);
		contentPane.add(courseField);
		
		JComboBox branchField = new JComboBox();
		branchField.setEnabled(false);
		branchField.setBounds(129, 299, 214, 23);
		contentPane.add(branchField);
		
		JComboBox yearField = new JComboBox();
		yearField.setEnabled(false);
		yearField.setBounds(129, 332, 214, 23);
		contentPane.add(yearField);
		
		JComboBox semesterField = new JComboBox();
		semesterField.setEnabled(false);
		semesterField.setBounds(129, 365, 214, 23);
		contentPane.add(semesterField);
		
		///
		usernameField.setText(user);
		nameField.setText(name);
		passField.setText(pass);
		securityField.setModel(new DefaultComboBoxModel(new String[] {security}));
		answerField.setText(answer);
		courseField.setModel(new DefaultComboBoxModel(new String[] {course}));
		branchField.setModel(new DefaultComboBoxModel(new String[] {branch}));
		yearField.setModel(new DefaultComboBoxModel(new String[] {year}));
		semesterField.setModel(new DefaultComboBoxModel(new String[] {semester}));
		///
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				usernameField.setEditable(true);
				nameField.setEditable(true);
				passField.setEditable(true);
				
				securityField.setModel(new DefaultComboBoxModel(new String[] {"What is your pet name?", "What is your mother tounge?", "What is your college name?", "Who is your best friend?"}));
				securityField.setSelectedItem(security);
				securityField.setEnabled(true);
				
				answerField.setEditable(true);
				
				courseField.setModel(new DefaultComboBoxModel(new String[] {"B.TECH", "M.TECH", "BCA", "MCA", "B.SC", "M.SC", "BBA", "MBA", "B.AGRI"}));
				courseField.setSelectedItem(course);
				courseField.setEnabled(true);
				
				branchField.setModel(new DefaultComboBoxModel(new String[] {"CSE", "CSIT", "ECE", "EE", "EEE", "ETC", "MECH", "CIVIL", "BIOTECH"}));
				branchField.setSelectedItem(branch);
				branchField.setEnabled(true);
				
				yearField.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
				yearField.setSelectedItem(year);
				yearField.setEnabled(true);
				
				semesterField.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
				semesterField.setSelectedItem(semester);
				semesterField.setEnabled(true);
				
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(51, 434, 81, 33);
		contentPane.add(btnEdit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// reset button action in student profile
				
				nameField.setEditable(false);
				passField.setEditable(false);
				securityField.setEnabled(false);
				answerField.setEditable(false);
				courseField.setEnabled(false);
				branchField.setEnabled(false);
				yearField.setEnabled(false);
				semesterField.setEnabled(false);
				
				usernameField.setText(user);
				nameField.setText(name);
				passField.setText(pass);
				securityField.setModel(new DefaultComboBoxModel(new String[] {security}));
				answerField.setText(answer);
				courseField.setModel(new DefaultComboBoxModel(new String[] {course}));
				branchField.setModel(new DefaultComboBoxModel(new String[] {branch}));
				yearField.setModel(new DefaultComboBoxModel(new String[] {year}));
				semesterField.setModel(new DefaultComboBoxModel(new String[] {semester}));
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(158, 434, 81, 33);
		contentPane.add(btnReset);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update button action in student details
				
				String newName = nameField.getText();
				String newPass = passField.getText();
				String newSecurity = (String) securityField.getSelectedItem();
				String newAnswer = answerField.getText();
				String newCourse = (String) courseField.getSelectedItem();
				String newBranch = (String) branchField.getSelectedItem();
				String newYear = (String) yearField.getSelectedItem();
				String newSemester = (String) semesterField.getSelectedItem();
				
				updateProfile(newName, newPass, newSecurity, newAnswer, newCourse, newBranch, newYear, newSemester);
				
				// disable all fields
				nameField.setEditable(false);
				passField.setEditable(false);
				securityField.setEnabled(false);
				answerField.setEditable(false);
				courseField.setEnabled(false);
				branchField.setEnabled(false);
				yearField.setEnabled(false);
				semesterField.setEnabled(false);
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(262, 434, 81, 33);
		contentPane.add(btnUpdate);
		
		lblNewLabel_1 = new JLabel("Student Profile");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(51, 46, 151, 23);
		contentPane.add(lblNewLabel_1);
		
	}
	
	public void updateProfile (String newName, String newPass, String newSecurity, String newAnswer, String newCourse, String newBranch, String newYear, String newSemester) {
		
		String sql = "update users set regd_no = ?, name = ?, password = ?, security = ?, answer = ?, course = ?, branch = ?, year = ?, semester = ? where regd_no='"+user+"' and password='"+pass+"'";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, user);
			ps.setString(2, newName);
			ps.setString(3, newPass);
			ps.setString(4, newSecurity);
			ps.setString(5, newAnswer);
			ps.setString(6, newCourse);
			ps.setString(7, newBranch);
			ps.setString(8, newYear);
			ps.setString(9, newSemester);
			
			
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Profile Updated");
			
			Session.logout();
			Session.login(user, newPass);
			
			user = Session.username;
			pass = Session.password;
			
			name = Session.name;
			security = Session.security;
			answer = Session.answer;
			course = Session.course;
			branch = Session.branch;
			year = Session.year;
			semester = Session.semester;
			
		} catch (Exception e2) {
			
			JOptionPane.showMessageDialog(null, e2);
		}
		
	}
}
