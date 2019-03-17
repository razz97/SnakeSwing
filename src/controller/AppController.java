package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	public final DateFormat formatter;
	
	private JFrameCustom frame;
	private JSplitPaneCustom splitPane;
	
	private final Dao dao;
	private final LogController logger;
	
	private User user;
	
	public void bootstrap() {
		splitPane = new JSplitPaneCustom();
		frame = new JFrameCustom(splitPane);
	}
	
	public static AppController getInstance() {
		if (instance == null)
			instance = new AppController();
		return instance;
	}
	
	private AppController() {
		formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		dao = Dao.getInstance();
		logger = LogController.getInstance();
	}
	
	public void auth(String username, String password) {
		User user = new User(username, password);
		if (dao.auth(user)) {
			logger.log("Logged in successfully.");
			this.user = user;
			setPanel(new JPanelMain());
		} else {
			logger.log("Invalid credentials.");
		}
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}
	
	public void fatalError() {
		JOptionPane.showMessageDialog(
				frame, 
				"XML is corrupted.", 
				"Fatal error.", 
				JOptionPane.ERROR_MESSAGE
		);
		System.exit(0);
	}
	
	public List<Score> getUserScores() {
		return dao.getScores(user);
	}
	
	public List<Score> getScores(User user) {
		return dao.getScores(user);
	}
	
	public Score getMaxScore(List<Score> scores) {
		return  scores.stream().max(Comparator.naturalOrder()).orElse(null);
	}
	
	public Score getMaxScore(User user) {
		return  getScores(user).stream().max(Comparator.naturalOrder()).orElse(null);
	}
	
	private void setPanel(JPanel panel) {
		splitPane.setRightComponent(panel);
		splitPane.setDividerLocation(200);
	}

	public void saveAndExit() {
		dao.commit();
		System.exit(0);
	}

	public void showScores() {
		logger.log("Scores panel shown.");
		setPanel(new JPanelScore());
	}

	public void showHome() {
		logger.log("Home panel shown.");
		setPanel(new JPanelMain());
	}
	
	public void logout() {
		user = null;
		logger.log("Logged off.");
		setPanel(new JPanelLogin());
	}
	
	public void showGame() {
		logger.log("Game panel shown.");
		setPanel(new JPanelGame());
	}

	public Score getBestScore() {
		return dao.getBestScore();
	}
	
}
