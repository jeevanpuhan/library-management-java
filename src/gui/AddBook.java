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
import java.util.Random;
import java.awt.event.ActionEvent;

import db.*;
import lms.Book;

public class AddBook extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField bookId;
	private JTextField bookName;
	private JTextField publisher;
	private JTextField price;
	private JTextField pages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		
		super("Add New Book");
		
		con = ConnectDB.connectdb();
		
//		random();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
// show panel in the center of screen
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
		setBounds((width-500)/2, (height-450)/2, 500, 450);
		
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
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(54, 257, 71, 17);
		contentPane.add(lblPrice);
		
		JLabel lblPages = new JLabel("Pages");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages.setBounds(54, 301, 71, 17);
		contentPane.add(lblPages);
		
		bookId = new JTextField();
		bookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookId.setBounds(170, 78, 251, 24);
		contentPane.add(bookId);
		bookId.setColumns(10);
		
		bookName = new JTextField();
		bookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookName.setColumns(10);
		bookName.setBounds(170, 122, 251, 24);
		contentPane.add(bookName);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		publisher.setColumns(10);
		publisher.setBounds(170, 210, 251, 24);
		contentPane.add(publisher);
		
		price = new JTextField();
		price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		price.setColumns(10);
		price.setBounds(170, 254, 251, 24);
		contentPane.add(price);
		
		pages = new JTextField();
		pages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pages.setColumns(10);
		pages.setBounds(170, 298, 251, 24);
		contentPane.add(pages);
		
		JComboBox edition = new JComboBox();
		edition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edition.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		edition.setBounds(170, 166, 251, 24);
		contentPane.add(edition);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add button action in Add book window
				
					Book obj = new Book(bookId.getText(), bookName.getText(), (String) edition.getSelectedItem(), publisher.getText(), price.getText(), pages.getText());
					obj.addBook();	
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(170, 349, 88, 29);
		contentPane.add(btnNewButton);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Add Book");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(54, 33, 102, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 2));
		panel.setBounds(21, 20, 441, 372);
		contentPane.add(panel);
		panel.setLayout(null);
	}
	
	// generate random book id
//	public void random() {
//		Random obj = new Random();
//		bookId.setText(""+obj.nextInt(1000+1));
//	}

}
