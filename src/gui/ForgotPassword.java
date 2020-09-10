package gui;

import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import db.*;

public class ForgotPassword extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField regdNoField;
	private JLabel lblName;
	private JLabel lblSecurityQuestion;
	private JLabel lblAnswer;
	private JLabel lblPassword;
	private JTextField nameField;
	private JTextField securityField;
	private JTextField answerField;
	private JTextField passwordField;
	private JButton searchRegdNo;
	private JButton retrievePassword;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		
		super("Forgot Password");
		con = ConnectDB.connectdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
				setBounds((width-500)/2, (height-450)/2, 500, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regd No.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 109, 72, 17);
		contentPane.add(lblNewLabel);
		
		regdNoField = new JTextField();
		regdNoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regdNoField.setBounds(152, 109, 207, 24);
		contentPane.add(regdNoField);
		regdNoField.setColumns(10);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(20, 153, 72, 17);
		contentPane.add(lblName);
		
		lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecurityQuestion.setBounds(20, 197, 110, 17);
		contentPane.add(lblSecurityQuestion);
		
		lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnswer.setBounds(20, 241, 72, 17);
		contentPane.add(lblAnswer);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(20, 285, 72, 17);
		contentPane.add(lblPassword);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(152, 150, 207, 24);
		contentPane.add(nameField);
		
		securityField = new JTextField();
		securityField.setEditable(false);
		securityField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		securityField.setColumns(10);
		securityField.setBounds(152, 198, 207, 24);
		contentPane.add(securityField);
		
		answerField = new JTextField();
		answerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		answerField.setColumns(10);
		answerField.setBounds(152, 242, 207, 24);
		contentPane.add(answerField);
		
		passwordField = new JTextField();
		passwordField.setEditable(false);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(152, 286, 207, 24);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Back button action in forgot window
				setVisible(false);
				Login obj = new Login();
				obj.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(206, 336, 88, 29);
		contentPane.add(btnNewButton);
		
		searchRegdNo = new JButton("Search");
		searchRegdNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// search button action in forgot password
				search();
			}
		});
		searchRegdNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchRegdNo.setBounds(369, 110, 85, 21);
		contentPane.add(searchRegdNo);
		
		retrievePassword = new JButton("Retrieve");
		retrievePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve button action in forgot window
				retrieve();
			}
		});
		retrievePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		retrievePassword.setBounds(369, 243, 85, 21);
		contentPane.add(retrievePassword);
		
		lblNewLabel_1 = new JLabel("Forgot Password");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(20, 52, 171, 24);
		contentPane.add(lblNewLabel_1);
	}
	
	// search name and security question by username
	public void search() {
		String search = regdNoField.getText();
		String sql = "select * from library.users where regd_no='"+search+"'";
		
		try {
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				nameField.setText(rs.getString("name"));
				securityField.setText(rs.getString("security"));
				rs.close();
				ps.close();
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Invalid Registration No.");
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	// retrieve password function
	public void retrieve() {
		
		String search = regdNoField.getText();
		String answer = answerField.getText();
		
		String sql = "select * from users where answer='"+answer+"' and regd_no='"+search+"'";
		
		try {
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				passwordField.setText(rs.getString("password"));
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
