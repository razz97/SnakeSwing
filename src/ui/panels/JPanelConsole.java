package ui.panels;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.AppController;
import controller.LogController;
import javax.swing.JScrollPane;

public class JPanelConsole extends JPanel {
	
	private static final long serialVersionUID = 1L;
		
	public JPanelConsole() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 20, 200, 450);
		scrollPane.setFocusable(false);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 200, 450);
		textArea.setFocusable(false);
		scrollPane.setViewportView(textArea);
		
		LogController.getInstance().addListener(msg -> textArea.append(msg + "\n"));
		
		setUpMenu();
	}
	
	private void setUpMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 200, 20);
		
		JMenu menu = new JMenu("Actions");
		menuBar.add(menu);
		
		JMenuItem home = new JMenuItem("Home");
		home.addActionListener(e -> AppController.getInstance().showHome());
		menu.add(home);
		
		JMenuItem logout = new JMenuItem("Logout");
		logout.addActionListener(e -> AppController.getInstance().logout());
		menu.add(logout);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> AppController.getInstance().saveAndExit());
		menu.add(exit);
		
		add(menuBar);
	}
}
