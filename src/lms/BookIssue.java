package lms;

import java.sql.*;

import javax.swing.JOptionPane;

import db.ConnectDB;

public class BookIssue {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
	
	private String bookId;
	private String bookName;
	private String regdNo;
	private String name;
	private String issueDate;
	private String dueDate;
	
	private String returnDate;
	
	public void setItems(String bookId, String bookName, String regdNo, String name, String issueDate, String dueDate) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.regdNo = regdNo;
		this.name = name;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		
	}
	
public void setItemsForReturn(String bookId, String bookName, String regdNo, String name, String dueDate, String returnDate) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.regdNo = regdNo;
		this.name = name;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		
	}

	
	
	public String getBookId() {
	return bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String getRegdNo() {
		return regdNo;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}

	public void resetItems() {
		
		this.bookId = null;
		this.bookName = null;
		this.regdNo = null;
		this.name = null;
		this.issueDate = null;
		this.dueDate = null;
	}
	
	public void issueBook() {
		
		try {
			
			con = ConnectDB.connectdb();
			String sql = "insert into issuebook(book_id, book_name, regd_no, name, issueDate, dueDate) values(?,?,?,?,?,?);";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, bookId);
			ps.setString(2, bookName);
			ps.setString(3, regdNo);
			ps.setString(4, name);
			ps.setString(5, issueDate);
			ps.setString(6, dueDate);
			ps.execute();
			
			System.out.println("Book Issued");	
			
		} catch (Exception e) {
			
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			
		}
		
	}
	
	public void deleteIssue(String regd_no) {
		
		try {
			
			con = ConnectDB.connectdb();
			String sql = "delete from issuebook where book_id = ? and regd_no = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, bookId);
			ps.setString(2, regd_no);
			
			ps.execute();
			
			System.out.println("Deleted Issue");
			
		} catch (Exception e) {
			
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public void returnBook() {
		
		try {
			
			con = ConnectDB.connectdb();
			String sql = "insert into returnbook(book_id, book_name, regd_no, name, dueDate, returnDate) values(?,?,?,?,?,?);";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, bookId);
			ps.setString(2, bookName);
			ps.setString(3, regdNo);
			ps.setString(4, name);
			ps.setString(5, dueDate);
			ps.setString(6, returnDate);
			ps.execute();
			
			System.out.println("Book Returned");	
			
		} catch (Exception e) {
			
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			
		}
		
	}

}
