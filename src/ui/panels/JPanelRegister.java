package ui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.AppController;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class JPanelRegister extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public JPanelRegister() {
		setSize(600, 500);
		setLayout(null);
		setupLabels();
		setupFields();
	}
	
	private void setupFields() {
		JTextField username = new JTextField();
		username.setBounds(236, 148, 240, 20);
		add(username);
		username.setColumns(10);
		JPasswordField password = new JPasswordField();
		password.setBounds(236, 235, 240, 20);
		add(password);
		JPasswordField repeat = new JPasswordField();
		repeat.setBounds(236, 322, 240, 20);
		add(repeat);
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(224, 409, 89, 23);
		btnSignIn.addActionListener(e -> {
			AppController.getInstance().register(username.getText(), 
					String.valueOf(password.getPassword()), 
					String.valueOf(repeat.getPassword()));
		});
		add(btnSignIn);
	}
	
	private void setupLabels() {
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(10, 67, 552, 14);
		lblRegister.setHorizontalAlignment(JLabel.CENTER);
		add(lblRegister);
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(45, 151, 181, 14);
		add(lblUsername);
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(45, 238, 181, 14);
		add(lblPassword);
		JLabel lblRepeatPassword = new JLabel("Repeat password: ");
		lblRepeatPassword.setBounds(45, 325, 181, 14);
		add(lblRepeatPassword);
	}
}
