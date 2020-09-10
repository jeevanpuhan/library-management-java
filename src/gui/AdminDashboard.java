package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import db.*;
import javax.swing.border.LineBorder;

public class AdminDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
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
	public AdminDashboard() {
		
		super("LMS - Admin Dashboard");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
						
		setBounds((width-800)/2, (height-500)/2, 800, 500);
				
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuFile);
		
		JMenuItem menuItemLogout = new JMenuItem("Logout");
		menuItemLogout.setIcon(null);
		menuItemLogout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				AdminLogin obj = new AdminLogin();
				obj.setVisible(true);
			}
		});
		menuFile.add(menuItemLogout);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		menuFile.add(menuItemExit);
		
		JMenu menuActions = new JMenu("Actions");
		menuActions.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuActions);
		
		JMenuItem menuItemClearDatabase = new JMenuItem("Clear Database");
		menuItemClearDatabase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuActions.add(menuItemClearDatabase);
		menuItemClearDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// menuItemClearDatabase action in menu bar
				
				ClearDB.clearDB();
				
			}
		});
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(menuHelp);
		
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				About obj = new About();
				obj.setVisible(true);
			}
		});
		menuHelp.add(menuItemAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(37, 10, 270, 22);
		contentPane.add(lblNewLabel);
		
		JButton addBook = new JButton("Add Book");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add book button action in homepage window
				setVisible(false);
				AddBook obj = new AddBook();
				obj.setVisible(true);
			}
		});
		addBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBook.setBounds(37, 99, 149, 38);
		contentPane.add(addBook);
		
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// statistics button action in Admin Dashboard
				setVisible(false);
				Statistics obj = new Statistics();
				obj.setVisible(true);
				
			}
		});
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStatistics.setBounds(315, 99, 149, 38);
		contentPane.add(btnStatistics);
		
		JButton addStudent = new JButton("Add Student");
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add student button action in homepage
				setVisible(false);
				AddStudent obj = new AddStudent();
				obj.setVisible(true);
			}
		});
		addStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addStudent.setBounds(593, 99, 149, 38);
		contentPane.add(addStudent);
		
		JButton issueBook = new JButton("Issue Book");
		issueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// issue book button action in homepage
				setVisible(false);
				IssueBook obj = new IssueBook();
				obj.setVisible(true);
			}
		});
		issueBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		issueBook.setBounds(37, 260, 149, 38);
		contentPane.add(issueBook);
		
		JButton returnBook = new JButton("Return Book");
		returnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// return book button action in admin dashboard
				
				setVisible(false);
				ReturnBook obj = new ReturnBook();
				obj.setVisible(true);
			}
		});
		returnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnBook.setBounds(315, 260, 149, 38);
		contentPane.add(returnBook);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				About obj = new About();
				obj.setVisible(true);
			}
		});
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbout.setBounds(593, 260, 149, 38);
		contentPane.add(btnAbout);
		
		JLabel lblWelcome = new JLabel("Welcome, ADMIN");
		lblWelcome.setForeground(Color.RED);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 14));
		lblWelcome.setBounds(610, 10, 132, 22);
		contentPane.add(lblWelcome);
		
		JButton viewStudents = new JButton("View Students");
		viewStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view student button action in Admin Dashboard
				
				setVisible(false);
				ViewStudents obj = new ViewStudents();
				obj.setVisible(true);
			}
		});
		viewStudents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewStudents.setBounds(593, 178, 149, 38);
		contentPane.add(viewStudents);
		
		JButton viewBook = new JButton("View Books");
		viewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view books button action in Admin Dashboard
				setVisible(false);
				AdminViewBooks obj = new AdminViewBooks();
				obj.setVisible(true);
			}
		});
		viewBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewBook.setBounds(37, 178, 149, 38);
		contentPane.add(viewBook);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255), 7));
		panel.setBounds(10, 42, 766, 1);
		contentPane.add(panel);
	}
}
