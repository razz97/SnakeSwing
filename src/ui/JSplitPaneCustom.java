package ui;

import javax.swing.JSplitPane;

import ui.panels.JPanelConsole;
import ui.panels.JPanelLogin;

import javax.swing.JPanel;

public class JSplitPaneCustom extends JSplitPane {
	
	private static final long serialVersionUID = 1L;

	public JSplitPaneCustom(JPanel login) {
		
		setDividerLocation(200);
		setEnabled(false);
		
		setRightComponent(login);
		
		JPanel panel = new JPanelConsole();
		setLeftComponent(panel);
		
	}
}