package ui.panels;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPanelConsole extends JPanel {
	
	public JPanelConsole() {
		setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 20, 200, 480);
		add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 200, 20);
		JMenu menu = new JMenu("Actions");
		menuBar.add(menu);
		
		JMenuItem home = new JMenuItem("Home");
		menu.add(home);
		JMenuItem logout = new JMenuItem("Logout");
		menu.add(logout);
		JMenuItem exit = new JMenuItem("Exit");
		menu.add(exit);
		
		add(menuBar);
		
				
		
	}

	private static final long serialVersionUID = 1L;
}
