package ui.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AppController;
import controller.LogController;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class JPanelLogin extends JPanel {
	private JTextField userField;
	private JPasswordField pswdField;
	public JPanelLogin() {
		setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(199, 141, 200, 20);
		add(userField);
		userField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setBounds(199, 116, 200, 14);
		add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(199, 195, 200, 14);
		add(lblPassword);
		
		JButton btnDone = new JButton("Login");
		btnDone.setBounds(241, 306, 100, 23);
		btnDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AppController.getInstance().auth(userField.getText(), new String(pswdField.getPassword()));
			}
		});
		add(btnDone);
		
		pswdField = new JPasswordField();
		pswdField.setBounds(199, 220, 200, 20);
		add(pswdField);
	}
}
