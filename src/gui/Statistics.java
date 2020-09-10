package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.ConnectDB;
import lms.Book;
import lms.BookIssue;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Statistics extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTable bookTable;
	private JTable returnBookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		
		super("Books Issued");
		
		con = ConnectDB.connectdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
		setBounds((width-1000)/2, (height-730)/2, 1000, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books Issued");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 29, 140, 24);
		contentPane.add(lblNewLabel);
		
		JScrollPane issueScrollPane = new JScrollPane();
		issueScrollPane.setBounds(20, 63, 945, 255);
		contentPane.add(issueScrollPane);
		
		bookTable = new JTable();
		issueScrollPane.setViewportView(bookTable);
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Book ID", "Book Name", "Regd No.", "Student Name", "Issue Date", "Due Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		bookTable.getColumnModel().getColumn(2).setMinWidth(30);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back button action in View issued books window
				
				setVisible(false);
				AdminDashboard obj = new AdminDashboard();
				obj.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(434, 642, 88, 29);
		contentPane.add(btnBack);
		
		JScrollPane returnScrollPane = new JScrollPane();
		returnScrollPane.setBounds(20, 377, 945, 255);
		contentPane.add(returnScrollPane);
		
		returnBookTable = new JTable();
		returnBookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book Id", "Book Name", "Regd No.", "Student Name", "Due Date", "Return Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		returnScrollPane.setViewportView(returnBookTable);
		
		JLabel lblBooksReturned = new JLabel("Books Returned");
		lblBooksReturned.setForeground(Color.BLUE);
		lblBooksReturned.setFont(new Font("Arial", Font.BOLD, 20));
		lblBooksReturned.setBounds(20, 343, 163, 24);
		contentPane.add(lblBooksReturned);
		
		// display data in issue table
		
		String sql = "select * from issuebook";
		showBookTable(sql);
		
		// display data in return table
		
				String sql1 = "select * from returnbook";
				showReturnBookTable(sql1);
	}
	
public ArrayList<BookIssue> bookList(String sql) {
		
		ArrayList<BookIssue> bookList = new ArrayList<>();
		
		try {
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			BookIssue bookIssue;
			while(rs.next()) {
				
				bookIssue = new BookIssue();
				bookIssue.setItems(rs.getString("book_id"), rs.getString("book_name"),rs.getString("regd_no"), rs.getString("name"), rs.getString("issueDate"),rs.getString("dueDate"));
				bookList.add(bookIssue);
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		return bookList;
		
	}
	
	public void showBookTable(String sql) {
		
		ArrayList<BookIssue> list = bookList(sql);
		DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
		Object[] row = new Object[7];
		
		for (int i = 0; i < list.size(); i++) {
			
			row[0] = (i+1);
			row[1] = list.get(i).getBookId();
			row[2] = list.get(i).getBookName();
			row[3] = list.get(i).getRegdNo();
			row[4] = list.get(i).getName();
			row[5] = list.get(i).getIssueDate();
			row[6] = list.get(i).getDueDate();
			model.addRow(row);
			
		}
	}
	
public ArrayList<BookIssue> returnBookList(String sql) {
		
		ArrayList<BookIssue> bookList = new ArrayList<>();
		
		try {
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			BookIssue bookIssue;
			while(rs.next()) {
				
				bookIssue = new BookIssue();
				bookIssue.setItemsForReturn(rs.getString("book_id"), rs.getString("book_name"),rs.getString("regd_no"), rs.getString("name"), rs.getString("dueDate"),rs.getString("returnDate"));
				bookList.add(bookIssue);
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		return bookList;
		
	}
	
	public void showReturnBookTable(String sql) {
		
		ArrayList<BookIssue> returnList = returnBookList(sql);
		DefaultTableModel model = (DefaultTableModel) returnBookTable.getModel();
		Object[] row = new Object[7];
		
		for (int i = 0; i < returnList.size(); i++) {
			
			row[0] = (i+1);
			row[1] = returnList.get(i).getBookId();
			row[2] = returnList.get(i).getBookName();
			row[3] = returnList.get(i).getRegdNo();
			row[4] = returnList.get(i).getName();
			row[5] = returnList.get(i).getDueDate();
			row[6] = returnList.get(i).getReturnDate();
			model.addRow(row);
			
		}
	}
	
//	public void writeToFile(String sql) {
//		
//		ArrayList<Book> list = bookList(sql);
//		try {
//			FileWriter myWriter = new FileWriter("BooksIssued.txt");
//			
//			
//			myWriter.write("*************************** \n");
//			myWriter.write("LIBRARY MANAGEMENT SYSTEM \n");
//			myWriter.write("*************************** \n\n");
//			
//			myWriter.write("Name            : "+Session.name+" \n");
//			myWriter.write("Registration No : "+Session.username+" \n\n");
//			
//			myWriter.write("============== \n");
//			myWriter.write("BOOKS ISSUED: \n");
//			myWriter.write("============== \n\n");
//			
//			String formatStr = "%-10s %-10s %-30s %-15s %-15s%n";
////			myWriter.write("#      Book ID      Book Name          Issue Date      Due Date \n");
//			myWriter.write(String.format(formatStr, "#","Book ID","Book Name","Issue Date","Due Date"));
//			myWriter.write("--------------------------------------------------------------------------------------------------------------- \n");
//			
//			for (int i = 0; i < list.size(); i++) {
//		
////				myWriter.write((i+1)+"      "+list.get(i).getBookId()+"      "+list.get(i).getBookName()+"          "+list.get(i).getIssueDate()+"      "+list.get(i).getDueDate()+" \n");
//				myWriter.write(String.format(formatStr, i+1, list.get(i).getBookId(),list.get(i).getBookName(),list.get(i).getIssueDate(),list.get(i).getDueDate())+" \n");
//				
//			}
//			myWriter.close();
//			JOptionPane.showMessageDialog(null, "Successfully wrote to the file!");
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//	}
	
	public void clearTable() {
		DefaultTableModel tModel = (DefaultTableModel) bookTable.getModel();
		tModel.setRowCount(0);
	}
}
