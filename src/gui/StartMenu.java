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
import javax.swing.UIManager;
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

public class StartMenu extends JFrame {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//WindowUtilities.setNativeLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		///
		// try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch (Exception e) {     e.printStackTrace();}

		///
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {
		
		super("Login");
		
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
		
		JButton login_btn = new JButton("ADMIN");
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// admin button in startmenu window
				setVisible(false);
				AdminLogin obj = new AdminLogin();
				obj.setVisible(true);
			}
		});
		login_btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login_btn.setBounds(73, 220, 120, 41);
		contentPane.add(login_btn);
		
		JButton reg_btn = new JButton("STUDENT");
		reg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// student button in startmenu window
				setVisible(false);
				Login obj = new Login();
				obj.setVisible(true);
				
			}
		});
		reg_btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		reg_btn.setBounds(276, 220, 120, 41);
		contentPane.add(reg_btn);
		
		JLabel lblNewLabel_2 = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(52, 43, 369, 55);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Please select your role: ");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(52, 115, 369, 55);
		contentPane.add(lblNewLabel_2_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY, 2));
		panel.setBounds(37, 183, 409, 121);
		contentPane.add(panel);
		panel.setLayout(null);
	}
	
	
}
