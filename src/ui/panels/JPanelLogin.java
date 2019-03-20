package ui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AppController;

import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class JPanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
		
	public JPanelLogin() {
		setLayout(null);
		setupLabels();
		setupFields();
		setupRegisterButton();
	}
	
	private void setupRegisterButton() {
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(436, 429, 100, 23);
		btnRegister.addActionListener(e -> AppController.getInstance().showRegister());
		add(btnRegister);
	}
	
	private void setupLabels() {
		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setBounds(199, 116, 200, 14);
		add(lblUsuario);
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(199, 195, 200, 14);
		add(lblPassword);
	}
	
	private void setupFields() {
		JTextField userField = new JTextField();
		userField.setBounds(199, 141, 200, 20);
		add(userField);
		userField.setColumns(10);
		JPasswordField pswdField = new JPasswordField();
		pswdField.setBounds(199, 220, 200, 20);
		add(pswdField);
		JButton btnDone = new JButton("Login");
		btnDone.setBounds(249, 295, 100, 23);
		btnDone.addActionListener(e -> AppController.getInstance().auth(userField.getText(), String.valueOf(pswdField.getPassword())));
		add(btnDone);
		pswdField.addActionListener(e -> btnDone.doClick());
		userField.addActionListener(e -> btnDone.doClick());
	}
	
}
