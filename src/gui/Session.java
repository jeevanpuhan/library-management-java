package gui;

import java.sql.*;

import javax.swing.JOptionPane;

public class Session {
	
	static String username;
	static String password;
	
	static String name;
	static String security;
	static String answer;
	static String course;
	static String branch;
	static String year;
	static String semester;
	
	public static String dbUrl = "jdbc:mysql://localhost:3306/library";
	public static String dbUsername = "root";
	public static String dbPassword = "Jeevraj,66";
	
	public static void login(String user, String pass) {
		
		username = user;
		password = pass;
		
		if(username != null && password != null)
		{
			setItems();
		}
		
		
	}
	
	public static void logout() {
		
		username = null;
		password = null;
		
		
	}
	
	
	public static void setItems() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded for session....");
			
			Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			System.out.println("Connection created for session...");
			
			String sql = "select * from library.users where regd_no='"+username+"' and password='"+password+"'";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				name = rs.getString("name");
				security = rs.getString("security");
				answer = rs.getString("answer");
				course = rs.getString("course");
				branch = rs.getString("branch");
				year = rs.getString("year");
				semester = rs.getString("semester");
				
				rs.close();
				ps.close();
				con.close();
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Invalid Registration No.");
			}
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
	
		

		
	}
}


	