package database;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Application.Application;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;

	public Login() throws Exception {
		initialize();
		conn = SqlConnection.getConnection("jdbc:mysql://localhost:3306/login");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(91, 64, 80, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(91, 117, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setBounds(210, 64, 86, 17);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM logininfo WHERE username = ? and password = ?";
					PreparedStatement pst = conn.prepareStatement (query);
					pst.setString(1, usernameField.getText());
					pst.setString(2, passwordField.getText());
					
					String checkpasswordchar = passwordField.getText();
					
					ResultSet rs = pst.executeQuery();
					
					int specials = 0;
					
					for (int i = 0; i < (checkpasswordchar).length(); ++i) {
					   char ch = (checkpasswordchar).charAt(i);
					   if (!Character.isDigit(ch) && !Character.isLetter(ch) && !Character.isSpace(ch)) {
							   ++specials;
					   }
					}
					
					if (specials > 0) {
						JOptionPane.showMessageDialog(null, "Special characters not allowed.");
						passwordField.setText("");
						return;
					}
					
					int count = 0;
					
					while (rs.next()) {
						count = count + 1;
					}
					
					if (count ==1) {
						JOptionPane.showMessageDialog(null, "Username and password correct.");
						Application.main(null);
					}
					else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Duplicate username and password.");
					}
					else {
						passwordField.setText("");
						JOptionPane.showMessageDialog(null, "Username and password are not correct. Please try again.");
					}
					
					rs.close();
					pst.close();
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		btnNewButton.setBounds(165, 170, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('•');
		passwordField.setBounds(210, 117, 86, 17);
		frame.getContentPane().add(passwordField);
	}
}
