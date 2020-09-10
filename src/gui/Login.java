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
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import db.*;

public class Login extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;
	private JTextField regd_no;
	private JLabel lblPassword;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		super("Login - Student");
		
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
		lblNewLabel.setBounds(64, 110, 88, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		regd_no = new JTextField();
		regd_no.setBounds(170, 113, 207, 24);
		regd_no.setToolTipText("Enter Regd No. as your Username");
		contentPane.add(regd_no);
		regd_no.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(64, 155, 88, 24);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(170, 158, 207, 24);
		contentPane.add(password);
		
		JButton login_btn = new JButton("Login");
		login_btn.setBounds(170, 211, 88, 29);
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// login button action in login window
				String sql = "select * from users where regd_no=? and password=?;";
				
				try {
					
					ps = con.prepareStatement(sql);
					ps.setString(1, regd_no.getText());
					ps.setString(2, password.getText().toString());
					
					rs = ps.executeQuery();
					if(rs.next()) {
						rs.close();
						ps.close();
						
						Session.login(regd_no.getText(), password.getText().toString());
						
						setVisible(false);
						StudentDashboard obj = new StudentDashboard();					
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
		contentPane.add(login_btn);
		
		JButton reg_btn = new JButton("Register");
		reg_btn.setBounds(289, 211, 88, 29);
		reg_btn.setToolTipText("Register a New Account");
		reg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Register button action in login window
				setVisible(false);
				Registration obj = new Registration();
				obj.setVisible(true);
			}
		});
		reg_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(reg_btn);
		
		JLabel lblTroubleLogging = new JLabel("Trouble Logging?");
		lblTroubleLogging.setBounds(64, 275, 110, 24);
		lblTroubleLogging.setForeground(Color.RED);
		lblTroubleLogging.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTroubleLogging);
		
		JButton forgot_btn = new JButton("Forgot Password");
		forgot_btn.setBounds(194, 273, 166, 29);
		forgot_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Forgot password button action in login window
				setVisible(false);
				ForgotPassword obj = new ForgotPassword();
				obj.setVisible(true);
			}
		});
		forgot_btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(forgot_btn);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(387, 158, 34, 21);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/key (3).png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setBounds(387, 113, 34, 21);
		lblNewLabel_1_1.setIcon(new ImageIcon(Login.class.getResource("/images/user_icon.png")));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel_2.setBounds(51, 28, 369, 55);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblNewLabel_2);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(230, 333, 88, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back button action in login window
				setVisible(false);
				StartMenu obj = new StartMenu();
				obj.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 93, 409, 290);
		panel.setBorder(new LineBorder(Color.BLUE, 2));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
	}
}
