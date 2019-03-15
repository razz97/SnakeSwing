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
		
		JTextField userField = new JTextField();
		userField.setBounds(199, 141, 200, 20);
		add(userField);
		userField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setBounds(199, 116, 200, 14);
		add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(199, 195, 200, 14);
		add(lblPassword);
		
		JPasswordField pswdField = new JPasswordField();
		pswdField.setBounds(199, 220, 200, 20);
		add(pswdField);
		
		JButton btnDone = new JButton("Login");
		btnDone.setBounds(241, 306, 100, 23);
		btnDone.addActionListener(e -> {
			AppController.getInstance().auth(
					userField.getText(), 
					String.valueOf(pswdField.getPassword())
			);
		});
		add(btnDone);
	}
}
