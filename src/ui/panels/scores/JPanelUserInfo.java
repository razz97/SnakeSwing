package ui.panels.scores;

import javax.swing.JPanel;

import controller.AppController;
import model.Score;
import model.User;

import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;

public class JPanelUserInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblUser;
	private JLabel lblGames;
	private JLabel lblBest;
	private JLabel lblDate;
	private JLabel lblUser_1;
	private JLabel lblGames_1;
	private JLabel lblBest_1;
	private JLabel lblDate_1;
	
	
	public JPanelUserInfo(User user) {
		setLayout(null);
		
		lblUser = new JLabel("New Label");
		lblUser.setBounds(81, 11, 119, 14);
		add(lblUser);
		
		lblGames = new JLabel("New label");
		lblGames.setBounds(81, 36, 119, 14);
		add(lblGames);
		
		lblBest = new JLabel("New label");
		lblBest.setBounds(81, 61, 119, 14);
		add(lblBest);
		
		lblDate = new JLabel("New label");
		lblDate.setBounds(81, 86, 119, 14);
		add(lblDate);
		
		lblUser_1 = new JLabel("User:");
		lblUser_1.setBounds(10, 11, 46, 14);
		add(lblUser_1);
		
		lblGames_1 = new JLabel("Games:");
		lblGames_1.setBounds(10, 36, 46, 14);
		add(lblGames_1);
		
		lblBest_1 = new JLabel("Best:");
		lblBest_1.setBounds(10, 61, 46, 14);
		add(lblBest_1);
		
		lblDate_1 = new JLabel("Date:");
		lblDate_1.setBounds(10, 86, 46, 14);
		add(lblDate_1);
		
		setUser(user);
		
	}
	
	public void setUser(User user) {
		List<Score> scores = AppController.getInstance().getScores(user);
		Score max = scores.stream().max(Comparator.naturalOrder()).orElse(null);
		lblUser.setText(user.getName());
		lblGames.setText(String.valueOf(scores.size()));
		lblBest.setText(max == null ? "0" : String.valueOf(max.getPoints()));
		lblDate.setText(max == null ? "-" : AppController.getInstance().formatter.format(max.getDate()));		
	}
}
