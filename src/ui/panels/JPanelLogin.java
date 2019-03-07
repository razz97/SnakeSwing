package ui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class JPanelLogin extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public JPanelLogin() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(23, 74, 232, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setBounds(23, 54, 46, 14);
		add(lblUsuario);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setBounds(209, 11, 46, 14);
		add(lblLogIn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(23, 168, 232, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(23, 147, 46, 14);
		add(lblPassword);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(44, 238, 89, 23);
		add(btnDone);
	}
}
