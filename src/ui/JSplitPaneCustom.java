package ui;

import javax.swing.JSplitPane;

import ui.panels.JPanelConsole;
import ui.panels.JPanelLogin;

public class JSplitPaneCustom extends JSplitPane {
	
	private static final long serialVersionUID = 1L;

	public JSplitPaneCustom() {
		setDividerLocation(200);
		setEnabled(false);
		setLeftComponent(new JPanelConsole());
		setRightComponent(new JPanelLogin());
	}
}