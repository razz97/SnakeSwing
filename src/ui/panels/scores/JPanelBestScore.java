package ui.panels.scores;

import javax.swing.JPanel;

import controller.AppController;
import model.Score;

import javax.swing.JLabel;


public class JPanelBestScore extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public JPanelBestScore() {
		setLayout(null);
		setupLabels();
	}
	
	private void setupLabels() {
		Score score = AppController.getInstance().getBestScore();
		JLabel placeUser = new JLabel("User:");
		placeUser.setBounds(77, 64, 46, 14);
		add(placeUser);		
		JLabel placeScore = new JLabel("Score: ");
		placeScore.setBounds(77, 142, 46, 14);
		add(placeScore);		
		JLabel placeDate = new JLabel("Date:");
		placeDate.setBounds(77, 220, 46, 14);
		add(placeDate);		
		JLabel lblName = new JLabel(score.getUser());
		lblName.setBounds(200, 64, 171, 14);
		add(lblName);		
		JLabel lblScore = new JLabel(String.valueOf(score.getPoints()));
		lblScore.setBounds(200, 142, 171, 14);
		add(lblScore);		
		JLabel lblDate = new JLabel(AppController.getInstance().formatter.format(score.getDate()));
		lblDate.setBounds(200, 220, 171, 14);
		add(lblDate);
	}
	
	
	
	
}
