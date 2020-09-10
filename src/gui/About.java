package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setTitle("About Library Management System");
		
		// show panel in the center of screen
				int width = Toolkit.getDefaultToolkit().getScreenSize().width;
				int height = Toolkit.getDefaultToolkit().getScreenSize().height;
				
				setBounds((width-434)/2, (height-287)/2, 434, 287);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("Close");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					About.this.setVisible(false);
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton.setBounds(172, 195, 81, 33);
			contentPanel.add(btnNewButton);
		}
		{
			JLabel lblNewLabel = new JLabel("Library Management System");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setBounds(48, 20, 331, 24);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Version: 2.0 Build 2 (JUN/12/2020)");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(58, 48, 297, 24);
		contentPanel.add(lblNewLabel_1);
		{
			JLabel lblNewLabel_1_1 = new JLabel("Support Team:");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(79, 156, 85, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("contact@jeevanpuhan.com");
			lblNewLabel_1_1.setForeground(Color.BLUE);
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(174, 156, 181, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Made with Love in India");
			lblNewLabel_1_1.setForeground(Color.RED);
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(58, 70, 297, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Developed By:");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(79, 105, 85, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Jeevan Kumar Puhan");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setForeground(Color.BLUE);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(174, 104, 181, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Registration No:");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(79, 130, 85, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("1841012124");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setForeground(Color.BLACK);
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(172, 130, 181, 24);
			contentPanel.add(lblNewLabel_1_1);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.RED));
		panel.setBounds(10, 10, 400, 230);
		contentPanel.add(panel);
	}
}
