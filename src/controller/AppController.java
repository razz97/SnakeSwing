package controller;

import javax.swing.JPanel;

import persistance.Dao;
import ui.JFrameCustom;
import ui.JSplitPaneCustom;
import ui.panels.JPanelGame;
import ui.panels.JPanelLogin;
import ui.panels.JPanelMain;
import ui.panels.JPanelScore;

public class AppController {

	private static AppController instance;
	
	private JSplitPaneCustom splitPane;
	
	private final JPanelLogin login = new JPanelLogin();
	private final JPanelMain main = new JPanelMain();
	private final JPanelGame game = new JPanelGame();
	private final JPanelScore score = new JPanelScore();
	
	
	public void bootstrap() {
		splitPane = new JSplitPaneCustom(login);
		new JFrameCustom(splitPane);
	}
	
	public static AppController getInstance() {
		if (instance == null)
			instance = new AppController();
		return instance;
	}
	
	private AppController() {}
	
	public void auth(String username, char[] password) {
		if (Dao.getInstance().auth(username, password)) {
			LogController.getInstance().log("Logged in successfully");
			setPanel(main);
		} else {
			LogController.getInstance().log("Invalid credentials");
		}
	}
	
	private void setPanel(JPanel panel) {
		splitPane.setRightComponent(panel);
		splitPane.setDividerLocation(200);
	}
	
	
}
