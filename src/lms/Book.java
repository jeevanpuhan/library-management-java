package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import db.ConnectDB;

public class Book {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private String bookId;
	private String bookName;
	private String edition;
	private String publisher;
	private String price;
	private String pages;
	
	private String issueDate;
	private String dueDate;
	
	public Book(String bookId, String bookName, String edition, String publisher, String price, String pages) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.edition = edition;
		this.publisher = publisher;
		this.price = price;
		this.pages = pages;
		
	}
	
public Book(String bookId, String bookName, String issueDate, String dueDate) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		
	}
	
	public String getBookId() {
		return bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getEdition() {
		return edition;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getPrice() {
		return price;
	}
	public String getPages() {
		return pages;
	}
	
	
	
	public String getIssueDate() {
		return issueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	// add Book to database
	public void addBook() {
		
		con = ConnectDB.connectdb();
		
		String sql = "insert into books(book_id, book_name, edition, publisher, price, pages) values(?,?,?,?,?,?)";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, bookId);
			ps.setString(2, bookName);
			ps.setString(3, edition);
			ps.setString(4, publisher);
			ps.setString(5, price);
			ps.setString(6, pages);
			
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Book Added");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
		
		
	}
	
	
}
