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

public class ViewBooks extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField bookNameField;
	private JTextField publisherField;
	private JTable bookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks frame = new ViewBooks();
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
	public ViewBooks() {
		
		super("View Books");
		
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
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(23, 80, 82, 17);
		contentPane.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdition.setBounds(23, 124, 71, 17);
		contentPane.add(lblEdition);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisher.setBounds(23, 168, 71, 17);
		contentPane.add(lblPublisher);
		
		bookNameField = new JTextField();
		bookNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bookNameField.setColumns(10);
		bookNameField.setBounds(139, 77, 195, 24);
		contentPane.add(bookNameField);
		
		publisherField = new JTextField();
		publisherField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		publisherField.setColumns(10);
		publisherField.setBounds(139, 165, 195, 24);
		contentPane.add(publisherField);
		
		JComboBox editionField = new JComboBox();
		editionField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editionField.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		editionField.setBounds(139, 121, 195, 24);
		contentPane.add(editionField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 20, 604, 372);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.setEnabled(false);
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
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view button action in Add book window
				
				clearTable();
				bookTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Book ID", "Book Name", "Edition", "Publisher", "Price", "Pages"
						}
					));
					bookTable.getColumnModel().getColumn(1).setMinWidth(30);
				
				
				String sql = "SELECT * FROM books where book_name like '%"+bookNameField.getText()+"%' and edition like '%"+(String) editionField.getSelectedItem()+"%' and publisher like '%"+publisherField.getText()+"%';";
				
				try {
					
					ps = con.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					if(rs.next()) {
						
						showBookTable(sql);
						
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
		btnView.setBounds(139, 293, 88, 29);
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
		btnBack.setBounds(246, 293, 88, 29);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("View Books");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(23, 33, 118, 24);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 2));
		panel.setBounds(10, 20, 339, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
	}
	
	public ArrayList<Book> bookList(String sql) {
		
		ArrayList<Book> bookList = new ArrayList<>();
		
		try {
		
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
	
	public void showBookTable(String sql) {
		
		ArrayList<Book> list = bookList(sql);
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
	
	public void clearTable() {
		DefaultTableModel tModel = (DefaultTableModel) bookTable.getModel();
		tModel.setRowCount(0);
	}
	
}
