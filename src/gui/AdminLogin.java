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

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import db.*;

public class AdminLogin extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField username;
	private JLabel lblPassword;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		
		super("Login - Admin");
		
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
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(64, 158, 88, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setToolTipText("");
		username.setBounds(170, 161, 207, 24);
		contentPane.add(username);
		username.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(64, 203, 88, 24);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(170, 206, 207, 24);
		contentPane.add(password);
		
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// login button action in login window
				String sql = "select * from admin where username=? and password=?;";
				
				try {
					
					ps = con.prepareStatement(sql);
					ps.setString(1, username.getText());
					ps.setString(2, password.getText().toString());
					
					rs = ps.executeQuery();
					if(rs.next()) {
						rs.close();
						ps.close();
						
						setVisible(false);
						AdminDashboard obj = new AdminDashboard();					
						obj.setVisible(true);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Invalid Credentials");
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				finally {
					
					try {
						
						rs.close();
						ps.close();
						
					} catch (Exception e3) {
						
						// to write an exception
					}
					
				} 
			}
		});
		login_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		login_btn.setBounds(170, 259, 88, 29);
		contentPane.add(login_btn);
		
		JButton reg_btn = new JButton("Back");
		reg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Register button action in login window
				setVisible(false);
				StartMenu obj = new StartMenu();
				obj.setVisible(true);
			}
		});
		reg_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reg_btn.setBounds(289, 259, 88, 29);
		contentPane.add(reg_btn);
		
		JLabel lblTroubleLogging = new JLabel("Trouble Logging?");
		lblTroubleLogging.setForeground(Color.RED);
		lblTroubleLogging.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTroubleLogging.setBounds(64, 323, 110, 24);
		contentPane.add(lblTroubleLogging);
		
		JButton forgot_btn = new JButton("Forgot Password");
		forgot_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Forgot password button action in login window
				setVisible(false);
				ForgotPassword obj = new ForgotPassword();
				obj.setVisible(true);
			}
		});
		forgot_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		forgot_btn.setBounds(194, 321, 166, 29);
		contentPane.add(forgot_btn);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AdminLogin.class.getResource("/images/key (3).png")));
		lblNewLabel_1.setBounds(387, 206, 34, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(AdminLogin.class.getResource("/images/user_icon.png")));
		lblNewLabel_1_1.setBounds(387, 161, 34, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE, 2));
		panel.setBounds(37, 125, 409, 258);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(52, 43, 369, 55);
		contentPane.add(lblNewLabel_2);
	}
}
