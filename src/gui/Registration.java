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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import db.*;

public class Registration extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
	
	private JPanel contentPane;
	private JTextField regd_no;
	private JLabel lblName;
	private JLabel lblSecurityQuestion;
	private JLabel lblAnswer;
	private JLabel lblPassword;
	private JTextField name;
	private JTextField answer;
	private JLabel lblNewLabel_1;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		
		super("Registration"); //
		
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
		
		JLabel lblNewLabel = new JLabel("Reg No.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 109, 72, 17);
		contentPane.add(lblNewLabel);
		
		regd_no = new JTextField();
		regd_no.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regd_no.setBounds(152, 109, 207, 24);
		contentPane.add(regd_no);
		regd_no.setColumns(10);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(20, 153, 72, 17);
		contentPane.add(lblName);
		
		lblSecurityQuestion = new JLabel("Password");
		lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecurityQuestion.setBounds(20, 197, 110, 17);
		contentPane.add(lblSecurityQuestion);
		
		lblAnswer = new JLabel("Security Question");
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnswer.setBounds(20, 241, 110, 17);
		contentPane.add(lblAnswer);
		
		lblPassword = new JLabel("Answer");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(20, 285, 72, 17);
		contentPane.add(lblPassword);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setColumns(10);
		name.setBounds(152, 150, 207, 24);
		contentPane.add(name);
		
		answer = new JTextField();
		answer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		answer.setColumns(10);
		answer.setBounds(152, 286, 207, 24);
		contentPane.add(answer);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Back button action
				setVisible(false);
				Login obj = new Login();
				obj.setVisible(true);
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(271, 338, 88, 29);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Register Account");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(20, 52, 171, 24);
		contentPane.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(152, 198, 207, 24);
		contentPane.add(password);
		
		JComboBox security = new JComboBox();
		security.setFont(new Font("Tahoma", Font.PLAIN, 14));
		security.setModel(new DefaultComboBoxModel(new String[] {"What is your pet name?", "What is your mother tounge?", "What is your college name?", "Who is your best friend?"}));
		security.setBounds(152, 241, 207, 24);
		contentPane.add(security);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Register button action
				
				try {
					
					String sql = "insert into library.users (regd_no, name, password, security, answer) values (?,?,?,?,?);";
					ps = con.prepareStatement(sql);
					
					ps.setString(1, regd_no.getText());
					ps.setString(2, name.getText());
					ps.setString(3, password.getText().toString());
					ps.setString(4, (String) security.getSelectedItem());
					ps.setString(5, answer.getText());
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "New Account Created");
					
					regd_no.setText("");
					name.setText("");
					password.setText("");
					answer.setText("");
					
//					con.close();	
					
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
					
					
				}
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(152, 338, 88, 29);
		contentPane.add(btnRegister);
	}
}
