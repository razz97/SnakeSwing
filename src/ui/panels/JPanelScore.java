package ui.panels;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ui.panels.scores.JPanelBestScore;
import ui.panels.scores.JPanelFilterScore;
import ui.panels.scores.JSplitPaneScore;

import javax.swing.JButton;
import javax.swing.JTextField;

import controller.AppController;

public class JPanelScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public JPanelScore() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(73, 73, 430, 300);
		add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Users list", null, panel, null);
		panel.setLayout(null);
		
		JSplitPaneScore splitPane = new JSplitPaneScore();
		splitPane.setBounds(0, 53, 425, 292);
		panel.add(splitPane);
		
		textField = new JTextField();
		textField.setBounds(10, 22, 301, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.setBounds(321, 21, 89, 23);
		btnNewButton.addActionListener(e -> splitPane.filter(textField.getText()));
		panel.add(btnNewButton);
		
		tabbedPane.addTab("Best user score", null, new JPanelBestScore(), null);
		tabbedPane.addTab("Score filter", null, new JPanelFilterScore(), null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 89, 23);
		btnBack.addActionListener(e -> AppController.getInstance().showHome());
		add(btnBack);
	}
}
