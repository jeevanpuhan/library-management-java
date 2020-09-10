package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StudentDashboard extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboard frame = new StudentDashboard();
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
	public StudentDashboard() {
		
		super("LMS - Student Dashboard");
		
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
				
				Session.logout();
				
				setVisible(false);
				Login obj = new Login();
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
		
		nameField = new JTextField() {
			public void setBorder (Border border) {
				// No!
			}
		};
		nameField.setForeground(Color.RED);
		nameField.setFont(new Font("Arial", Font.BOLD, 14));
		nameField.setEditable(false);
		nameField.setBounds(37, 10, 326, 22);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		nameField.setText("Welcome, "+Session.name);
		
		JLabel lblNewLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(506, 10, 270, 22);
		contentPane.add(lblNewLabel);
		
		JButton addBook = new JButton("View Books");
		addBook.setToolTipText("View available books in the library");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view books action button student dashboard
				
				setVisible(false);
				ViewBooks obj = new ViewBooks();
				obj.setVisible(true);
				
			}
		});
		addBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBook.setBounds(37, 99, 149, 38);
		contentPane.add(addBook);
		
		JButton btnBooksIssued = new JButton("Books Issued");
		btnBooksIssued.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// books issued button action in student dashboard
				setVisible(false);
				ViewIssuedBooks obj = new ViewIssuedBooks();
				obj.setVisible(true);
			}
		});
		btnBooksIssued.setToolTipText("View details of currently issued Books");
		btnBooksIssued.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBooksIssued.setBounds(315, 99, 149, 38);
		contentPane.add(btnBooksIssued);
		
		JButton addStudent = new JButton("My Profile");
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 
				
				setVisible(false);
				StudentProfile obj = new StudentProfile();
				obj.setVisible(true);
				
			}
		});
		addStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addStudent.setBounds(593, 99, 149, 38);
		contentPane.add(addStudent);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// about button action in student dashboard
				
				About obj = new About();
				obj.setVisible(true);
				
			}
		});
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAbout.setBounds(593, 260, 149, 38);
		contentPane.add(btnAbout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255), 7));
		panel.setBounds(10, 51, 766, 1);
		contentPane.add(panel);
		
	}
}
