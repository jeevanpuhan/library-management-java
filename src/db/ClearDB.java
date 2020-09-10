package db;

import java.sql.*;

import javax.swing.JOptionPane;

public class ClearDB {
	
	public static String url = "jdbc:mysql://localhost:3306/library";
	public static String username = "root";
	public static String password = "Jeevraj,66";
	
	static Connection con;
	
	public static void clearDB() {
		
		
		String[] tables = {"books", "issuebook", "returnbook", "users"};
		
		for (int i = 0; i < tables.length; i++) {
			
			String sql = "delete from ";
			sql = sql.concat(tables[i].toString());
			clearTables(sql);
			
		}
		
	}
	
	
	public static void clearTables(String sql) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded - Clear DB");
			
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection created... - Clear DB");
			
			PreparedStatement ps = con.prepareStatement(sql);
			int c = ps.executeUpdate();
			
			if(c>0)
			{
				System.out.println(c+" rows affected!");
			}
			else {
				System.out.println("No row affected...");
			}
			
		
			con.close();
			
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			
			
		}
			
	}
	
	
	

}
