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
		setupBackButton();
	}
	
	private void setupBackButton() {
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(26, 27, 89, 23);
		btnBack.addActionListener(e -> AppController.getInstance().showLogin());
		add(btnBack);
	}

	private void setupFields() {
		JTextField username = new JTextField();
		username.setBounds(267, 104, 240, 20);
		add(username);
		username.setColumns(10);
		JPasswordField password = new JPasswordField();
		password.setBounds(267, 191, 240, 20);
		add(password);
		JPasswordField repeat = new JPasswordField();
		repeat.setBounds(267, 278, 240, 20);
		add(repeat);
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(221, 350, 89, 23);
		btnSignIn.addActionListener(e -> {
			AppController.getInstance().register(username.getText(), 
					String.valueOf(password.getPassword()), 
					String.valueOf(repeat.getPassword()));
		});
		add(btnSignIn);		
		repeat.addActionListener(e -> btnSignIn.doClick());
		password.addActionListener(e -> btnSignIn.doClick());
		username.addActionListener(e -> btnSignIn.doClick());
	}
	
	private void setupLabels() {
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(76, 107, 181, 14);
		add(lblUsername);
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(76, 194, 181, 14);
		add(lblPassword);
		JLabel lblRepeatPassword = new JLabel("Repeat password: ");
		lblRepeatPassword.setBounds(76, 281, 181, 14);
		add(lblRepeatPassword);
	}
}
