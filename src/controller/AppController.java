package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Score;
import model.User;
import persistance.Dao;
import ui.JFrameCustom;
import ui.JSplitPaneCustom;
import ui.panels.JPanelLogin;
import ui.panels.JPanelMain;
import ui.panels.JPanelRegister;
import ui.panels.JPanelScore;
import ui.panels.JPanelGame;

public class AppController {

	private static AppController instance;
	public final DateFormat formatter;
	private JFrameCustom frame;
	private JSplitPaneCustom splitPane;
	private final Dao dao;
	private final LogController logger;
	private User user;
	
	public static AppController getInstance() {
		if (instance == null)
			instance = new AppController();
		return instance;
	}
	
	private AppController() {
		formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		dao = Dao.getInstance(this);
		logger = LogController.getInstance();
	}
	
	public void bootstrap() {
		splitPane = new JSplitPaneCustom();
		frame = new JFrameCustom(splitPane);
	}
	
	private void setPanel(JPanel panel) {
		splitPane.setRightComponent(panel);
		splitPane.setDividerLocation(200);
	}
	
	public void auth(String username, String password) {
		User user = new User(username, password);
		if (dao.auth(user)) {
			logger.log("Logged in successfully.");
			this.user = user;
			setPanel(new JPanelMain());
		} else 
			logger.log("Invalid credentials.");
	}
	
	public void register(String username, String password, String passwordRepeat) {
		if (dao.isUsername(username))
			logger.log("This username is already in use.");
		else if (!password.equals(passwordRepeat))
			logger.log("Passwords aren't equal.");
		else {
			user = new User(username, password);
			dao.register(user);
			logger.log("Registered successfully");
			showHome();
		}
	}
	
	public void logout() {
		if (user != null) {
			user = null;
			dao.commit();
			logger.log("Logged off.");
			setPanel(new JPanelLogin());
		} else
			logger.log("You must log in first");
	}
	
	public void showScores() {
		if (user != null) {
			logger.log("Scores panel shown.");
			setPanel(new JPanelScore());
		} else
			logger.log("You must log in first.");
		
	}

	public void showHome() {
		if (user != null) {
			logger.log("Home panel shown.");
			setPanel(new JPanelMain());
		} else
			logger.log("You must log in first.");
	}
		
	public void showGame() {
		if (user != null) {
			logger.log("Game panel shown.");
			JPanelGame snake = new JPanelGame();
			setPanel(snake);
			snake.requestFocusInWindow();
		} else 
			logger.log("You must log in first.");
	}
	
	public void showRegister() {
		setPanel(new JPanelRegister());
		logger.log("Register panel shown.");
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}
	
	public List<Score> getUserScores() {
		return getScores(user);
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
	
	public Score getBestScore() {
		return dao.getBestScore();
	}

	public void addScore(int points) {
		logger.log("Added score.");
		frame.setTitle("* SnakeTucom");
		dao.addScore(new Score(user.getName(),new Date(),points));
	}

	public void save() {
		dao.commit();
	}
	
	public void saveAndExit() {
		save();
		System.exit(0);
	}

	public void changesCommitted() {
		frame.setTitle("SnakeTucom");
		logger.log("Changes saved successfully.");
	}
	
	public boolean areChangesCommitted() {
		return dao.areChangesCommitted();
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
}
