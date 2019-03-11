package controller;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdom2.Element;

import model.Score;
import model.User;
import persistance.Dao;
import ui.JFrameCustom;
import ui.JSplitPaneCustom;
import ui.panels.JPanelGame;
import ui.panels.JPanelLogin;
import ui.panels.JPanelMain;
import ui.panels.JPanelScore;

public class AppController {

	private static AppController instance;
	
	private JFrameCustom frame;
	private JSplitPaneCustom splitPane;
	private final JPanelLogin login = new JPanelLogin();
	private final JPanelMain main = new JPanelMain();
	private final JPanelGame game = new JPanelGame();
	private final JPanelScore score = new JPanelScore();
	private User user;
	
	public void bootstrap() {
		splitPane = new JSplitPaneCustom(login);
		frame = new JFrameCustom(splitPane);
	}
	
	public static AppController getInstance() {
		if (instance == null)
			instance = new AppController();
		return instance;
	}
	
	private AppController() {}
	
	public void auth(String username, String password) {
		User user = new User(username, password);
		if (Dao.getInstance().auth(user)) {
			LogController.getInstance().log("Logged in successfully");
			this.user = user;
			setPanel(main);
		} else {
			LogController.getInstance().log("Invalid credentials");
		}
	}
	
	public void fatalError() {
		JOptionPane.showMessageDialog(frame, "XML is corrupted.", "Fatal error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public List<Score> getUserScores() {
		return Dao.getInstance().getScores(user);
	}
	
	
	
	private void setPanel(JPanel panel) {
		splitPane.setRightComponent(panel);
		splitPane.setDividerLocation(200);
	}
	
	
}
