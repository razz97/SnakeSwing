package ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.LogController;
import controller.LogController.LogListener;

public class JPanelConsole extends JPanel implements LogListener {
	
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public JPanelConsole() {
		LogController.getInstance().addListener(this);
		setLayout(null);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 20, 200, 480);
		add(textArea);
		setUpMenu();
	}
	
	private void setUpMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 200, 20);
		JMenu menu = new JMenu("Actions");
		menuBar.add(menu);
		JMenuItem home = new JMenuItem("Home");
		home.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				LogController.getInstance().log("Navigating to home...");
			}
		});
		menu.add(home);
		JMenuItem logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				LogController.getInstance().log("Logging off...");
			}
		});
		menu.add(logout);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				LogController.getInstance().log("Goodbye!");
			}
		});
		menu.add(exit);
		add(menuBar);
	}
	
	@Override
	public void onNewLogMessage(String message) {
		textArea.append(message + "\n");
	}
}
