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

public class ViewBooksAdmin extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField bookId;
	private JTextField bookName;
	private JTextField publisher;
	private JTextField price;
	private JTextField pages;
	private JTable bookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooksAdmin frame = new ViewBooksAdmin();
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
	public ViewBooksAdmin() {
		
		super("Add New Book");
		
		con = ConnectDB.connectdb();
		
//		random();
		
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
		lblNewLabel.setBounds(23, 81, 71, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(23, 125, 82, 17);
		contentPane.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition.setBounds(23, 169, 71, 17);
		contentPane.add(lblEdition);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(23, 213, 71, 17);
		contentPane.add(lblPublisher);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(23, 257, 71, 17);
		contentPane.add(lblPrice);
		
		JLabel lblPages = new JLabel("Pages");
		lblPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPages.setBounds(23, 301, 71, 17);
		contentPane.add(lblPages);
		
		bookId = new JTextField();
		bookId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookId.setBounds(139, 78, 251, 24);
		contentPane.add(bookId);
		bookId.setColumns(10);
		
		bookName = new JTextField();
		bookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookName.setColumns(10);
		bookName.setBounds(139, 122, 251, 24);
		contentPane.add(bookName);
		
		publisher = new JTextField();
		publisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		publisher.setColumns(10);
		publisher.setBounds(139, 210, 251, 24);
		contentPane.add(publisher);
		
		price = new JTextField();
		price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		price.setColumns(10);
		price.setBounds(139, 254, 251, 24);
		contentPane.add(price);
		
		pages = new JTextField();
		pages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pages.setColumns(10);
		pages.setBounds(139, 298, 251, 24);
		contentPane.add(pages);
		
		JComboBox edition = new JComboBox();
		edition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edition.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		edition.setBounds(139, 166, 251, 24);
		contentPane.add(edition);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add button action in Add book window
				
				
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnView.setBounds(139, 349, 88, 29);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Back button action in add book window
				setVisible(false);
				StudentDashboard obj = new StudentDashboard();
				obj.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(302, 349, 88, 29);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("View Books");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(23, 33, 118, 24);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 20, 564, 372);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		scrollPane.setViewportView(bookTable);
		bookTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book ID", "Book Name", "Edition", "Publisher", "Price", "Pages"
			}
		));
		bookTable.getColumnModel().getColumn(1).setMinWidth(30);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 2));
		panel.setBounds(10, 20, 394, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		showBookTable();
		
	}
	
	public ArrayList<Book> bookList() {
		
		ArrayList<Book> bookList = new ArrayList<>();
		
		try {
			
			String sql = "select * from books";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Book book;
			while(rs.next()) {
				
				book = new Book(rs.getString("book_id"), rs.getString("book_name"),rs.getString("edition"),rs.getString("publisher"),rs.getString("price"),rs.getString("pages"));
				bookList.add(book);
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		return bookList;
		
	}
	
	public void showBookTable() {
		
		ArrayList<Book> list = bookList();
		DefaultTableModel model = (DefaultTableModel) bookTable.getModel(); 
		Object[] row = new Object[6];
		
		for (int i = 0; i < list.size(); i++) {
			
			row[0] = list.get(i).getBookId();
			row[1] = list.get(i).getBookName();
			row[2] = list.get(i).getEdition();
			row[3] = list.get(i).getPublisher();
			row[4] = list.get(i).getPrice();
			row[5] = list.get(i).getPages();
			model.addRow(row);
		}
	}
	
}
