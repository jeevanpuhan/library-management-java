package db;

import java.sql.*;
import javax.swing.*;

public class ConnectDB {
	
	public static String url = "jdbc:mysql://localhost:3306/library";
	public static String username = "root";
	public static String password = "Jeevraj,66";
	
	static Connection con;
	
	public static Connection connectdb() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection created...");
			return con;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
