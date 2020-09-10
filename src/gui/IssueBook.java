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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import com.jgoodies.looks.windows.WindowsMenuItemUI;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;

import db.*;
import lms.*;

public class IssueBook extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField bookId;
	private JTextField bookNameField;
	private JTextField publisherField;
	private JTextField dueDateField;
	private JTextField regdNoField;
	private JTextField nameField;
	private JTextField branchField;
	private JTextField yearField;
	private JTextField semesterField;
	private JTextField courseField;
	private JTextField editionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		
		super("Issue Book");
		
		con = ConnectDB.connectdb();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
				setBounds((width-1000)/2, (height-450)/2, 1000, 450);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 81, 71, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(54, 125, 82, 17);
		contentPane.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition.setBounds(54, 169, 71, 17);
		contentPane.add(lblEdition);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(54, 213, 71, 17);
		contentPane.add(lblPublisher);
		
		JLabel lblPages = new JLabel("Due Date");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages.setBounds(54, 301, 82, 17);
		contentPane.add(lblPages);
		
		bookId = new JTextField();
		bookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookId.setBounds(170, 78, 251, 24);
		contentPane.add(bookId);
		bookId.setColumns(10);
		
		bookNameField = new JTextField();
		bookNameField.setBackground(Color.WHITE);
		bookNameField.setEditable(false);
		bookNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookNameField.setColumns(10);
		bookNameField.setBounds(170, 122, 251, 24);
		contentPane.add(bookNameField);
		
		publisherField = new JTextField();
		publisherField.setBackground(Color.WHITE);
		publisherField.setEditable(false);
		publisherField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		publisherField.setColumns(10);
		publisherField.setBounds(170, 210, 251, 24);
		contentPane.add(publisherField);
		
		dueDateField = new JTextField();
		dueDateField.setBackground(Color.WHITE);
		dueDateField.setEditable(false);
		dueDateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dueDateField.setColumns(10);
		dueDateField.setBounds(170, 298, 251, 24);
		contentPane.add(dueDateField);
		
		JLabel lblNewLabel_1 = new JLabel("Book Details");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(54, 33, 133, 24);
		contentPane.add(lblNewLabel_1);
		
		editionField = new JTextField();
		editionField.setBackground(Color.WHITE);
		editionField.setEditable(false);
		editionField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editionField.setColumns(10);
		editionField.setBounds(170, 166, 251, 24);
		contentPane.add(editionField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student Details");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(546, 33, 156, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Regd No.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(546, 81, 71, 17);
		contentPane.add(lblNewLabel_2);
		
		regdNoField = new JTextField();
		regdNoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regdNoField.setColumns(10);
		regdNoField.setBounds(662, 78, 251, 24);
		contentPane.add(regdNoField);
		
		JLabel lblBookName_1 = new JLabel("Name");
		lblBookName_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName_1.setBounds(546, 125, 82, 17);
		contentPane.add(lblBookName_1);
		
		nameField = new JTextField();
		nameField.setBackground(Color.WHITE);
		nameField.setEditable(false);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(662, 122, 251, 24);
		contentPane.add(nameField);
		
		JLabel lblEdition_1 = new JLabel("Course");
		lblEdition_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition_1.setBounds(546, 169, 71, 17);
		contentPane.add(lblEdition_1);
		
		JLabel lblPublisher_1 = new JLabel("Branch");
		lblPublisher_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher_1.setBounds(546, 213, 71, 17);
		contentPane.add(lblPublisher_1);
		
		branchField = new JTextField();
		branchField.setBackground(Color.WHITE);
		branchField.setEditable(false);
		branchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		branchField.setColumns(10);
		branchField.setBounds(662, 210, 251, 24);
		contentPane.add(branchField);
		
		JLabel lblPrice_1 = new JLabel("Year");
		lblPrice_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice_1.setBounds(546, 257, 71, 17);
		contentPane.add(lblPrice_1);
		
		yearField = new JTextField();
		yearField.setBackground(Color.WHITE);
		yearField.setEditable(false);
		yearField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yearField.setColumns(10);
		yearField.setBounds(662, 254, 251, 24);
		contentPane.add(yearField);
		
		JLabel lblPages_1 = new JLabel("Semester");
		lblPages_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages_1.setBounds(546, 301, 71, 17);
		contentPane.add(lblPages_1);
		
		semesterField = new JTextField();
		semesterField.setBackground(Color.WHITE);
		semesterField.setEditable(false);
		semesterField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		semesterField.setColumns(10);
		semesterField.setBounds(662, 298, 251, 24);
		contentPane.add(semesterField);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(170, 254, 251, 24);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateChooser);
		
		JButton issueBook = new JButton("Issue");
		issueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// issue button action in issue book window
				
				try {
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(dateChooser.getDate());
					Calendar c = Calendar.getInstance();
					try{
						
					   //Setting the date to the given date
					   c.setTime(sdf.parse(date));
					   
					}catch(ParseException e1){
						e1.printStackTrace();
					 }
					   
					//Number of Days to add
					c.add(Calendar.DAY_OF_MONTH, 21);  
					//Date after adding the days to the given date
					String dueDate = sdf.format(c.getTime()); 
					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
					String newDueDate = sd.format(c.getTime());
					dueDateField.setText(newDueDate);
					
					//
					BookIssue obj = new BookIssue();
					obj.setItems(bookId.getText(), bookNameField.getText(), regdNoField.getText(), nameField.getText(), date, dueDate);
					obj.issueBook();
					//
					
					
					JOptionPane.showMessageDialog(null, "Book Issued");
					
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
					
				}
				
			}
		});
		issueBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueBook.setBounds(662, 349, 88, 29);
		contentPane.add(issueBook);
		
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
		btnBack.setBounds(825, 349, 88, 29);
		contentPane.add(btnBack);
		
		courseField = new JTextField();
		courseField.setBackground(Color.WHITE);
		courseField.setEditable(false);
		courseField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseField.setColumns(10);
		courseField.setBounds(662, 166, 251, 24);
		contentPane.add(courseField);
		
		JLabel lblDateOfIssue = new JLabel("Date of Issue");
		lblDateOfIssue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfIssue.setBounds(54, 257, 88, 17);
		contentPane.add(lblDateOfIssue);
		
		JButton searchStudent = new JButton("");
		searchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// book search button action in issue book window
				
				String sql = "select * from books where book_id=?";
				
				try {
					
					ps = con.prepareStatement(sql);
					ps.setString(1, bookId.getText());
					rs = ps.executeQuery();
					
					if(rs.next()) {
						
						String addName =  rs.getString("book_name");
						bookNameField.setText(addName);
						
						String addEdition =  rs.getString("edition");
						editionField.setText(addEdition);
						
						String addPublisher =  rs.getString("publisher");
						publisherField.setText(addPublisher);
						
						
						rs.close();
						ps.close();
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Invalid Book ID");
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
		searchStudent.setBounds(425, 78, 39, 24);
		contentPane.add(searchStudent);
		searchStudent.setIcon(new ImageIcon(IssueBook.class.getResource("/images/search.png")));
		
		JButton searchbooks = new JButton("");
		searchbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// search student button action in issue book window
				String sql = "select * from users where regd_no=?";
				
				try {
					
					ps = con.prepareStatement(sql);
					ps.setString(1, regdNoField.getText());
					rs = ps.executeQuery();
					
					if(rs.next()) {
						
						String addName =  rs.getString("name");
						nameField.setText(addName);
						
						String addCourse =  rs.getString("course");
						courseField.setText(addCourse);
						
						String addBranch =  rs.getString("branch");
						branchField.setText(addBranch);
						
						String addYear =  rs.getString("year");
						yearField.setText(addYear);
						
						String addSemester =  rs.getString("semester");
						semesterField.setText(addSemester);
						
						rs.close();
						ps.close();
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Invalid Registration No.");
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
		searchbooks.setBounds(918, 78, 39, 24);
		contentPane.add(searchbooks);
		searchbooks.setIcon(new ImageIcon(IssueBook.class.getResource("/images/search.png")));
		
		JButton issueBook_1 = new JButton("Reset All");
		issueBook_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// reset button action in issue book window
				
				// clear all fields
				bookId.setText("");
				bookNameField.setText("");
				editionField.setText("");
				publisherField.setText("");
				dateChooser.setCalendar(null);
				dueDateField.setText("");
				
				regdNoField.setText("");
				nameField.setText("");
				courseField.setText("");
				branchField.setText("");
				yearField.setText("");
				semesterField.setText("");
				
			}
		});
		issueBook_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueBook_1.setBounds(212, 349, 88, 29);
		contentPane.add(issueBook_1);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new LineBorder(Color.ORANGE, 2));
		bookPanel.setBounds(10, 20, 466, 372);
		contentPane.add(bookPanel);
		bookPanel.setLayout(null);
			
		JPanel studentPanel = new JPanel();
		studentPanel.setLayout(null);
		studentPanel.setBorder(new LineBorder(Color.MAGENTA, 2));
		studentPanel.setBounds(510, 20, 466, 372);
		contentPane.add(studentPanel);
		studentPanel.setLayout(null);
		
	}
}
