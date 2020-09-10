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

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ViewIssuedBooks extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTable bookTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks frame = new ViewIssuedBooks();
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
	public ViewIssuedBooks() {
		
		super("Books Issued");
		
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
		
		JLabel lblNewLabel = new JLabel("Books Issued");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 29, 140, 24);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 63, 945, 255);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		scrollPane.setViewportView(bookTable);
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book ID", "Book Name", "Issue Date", "Due Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back button action in View issued books window
				
				setVisible(false);
				StudentDashboard obj = new StudentDashboard();
				obj.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(550, 348, 88, 29);
		contentPane.add(btnBack);
		
		JButton btnGenerateStatement = new JButton("Generate Statement");
		btnGenerateStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generate statement button action in viewIssuedBooks window
				String sql = "select ib.book_id, ib.book_name, ib.issueDate, ib.dueDate from issuebook ib join users on users.regd_no = ib.regd_no where users.regd_no = '"+Session.username+"'";
				writeToFile(sql);
			}
		});
		btnGenerateStatement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateStatement.setBounds(312, 348, 198, 29);
		contentPane.add(btnGenerateStatement);
		
		// display data in table
		
		String sql = "select ib.book_id, ib.book_name, ib.issueDate, ib.dueDate from issuebook ib join users on users.regd_no = ib.regd_no where users.regd_no = '"+Session.username+"'";
		showBookTable(sql);
		
	}
	
public ArrayList<Book> bookList(String sql) {
		
		ArrayList<Book> bookList = new ArrayList<>();
		
		try {
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Book book;
			while(rs.next()) {
				
				book = new Book(rs.getString("book_id"), rs.getString("book_name"),rs.getString("issueDate"),rs.getString("dueDate"));
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
		Object[] row = new Object[4];
		
		for (int i = 0; i < list.size(); i++) {
			
			row[0] = list.get(i).getBookId();
			row[1] = list.get(i).getBookName();
			row[2] = list.get(i).getIssueDate();
			row[3] = list.get(i).getDueDate();
			model.addRow(row);
			
		}
	}
	
	public void writeToFile(String sql) {
		
		ArrayList<Book> list = bookList(sql);
		try {
			FileWriter myWriter = new FileWriter("BooksIssued.txt");
			
			
			myWriter.write("*************************** \n");
			myWriter.write("LIBRARY MANAGEMENT SYSTEM \n");
			myWriter.write("*************************** \n\n");
			
			myWriter.write("Name            : "+Session.name+" \n");
			myWriter.write("Registration No : "+Session.username+" \n\n");
			
			myWriter.write("============== \n");
			myWriter.write("BOOKS ISSUED: \n");
			myWriter.write("============== \n\n");
			
			String formatStr = "%-10s %-10s %-30s %-15s %-15s%n";
//			myWriter.write("#      Book ID      Book Name          Issue Date      Due Date \n");
			myWriter.write(String.format(formatStr, "#","Book ID","Book Name","Issue Date","Due Date"));
			myWriter.write("--------------------------------------------------------------------------------------------------------------- \n");
			
			for (int i = 0; i < list.size(); i++) {
		
//				myWriter.write((i+1)+"      "+list.get(i).getBookId()+"      "+list.get(i).getBookName()+"          "+list.get(i).getIssueDate()+"      "+list.get(i).getDueDate()+" \n");
				myWriter.write(String.format(formatStr, i+1, list.get(i).getBookId(),list.get(i).getBookName(),list.get(i).getIssueDate(),list.get(i).getDueDate())+" \n");
				
			}
			myWriter.close();
			JOptionPane.showMessageDialog(null, "Successfully wrote to the file!");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void clearTable() {
		DefaultTableModel tModel = (DefaultTableModel) bookTable.getModel();
		tModel.setRowCount(0);
	}
}
