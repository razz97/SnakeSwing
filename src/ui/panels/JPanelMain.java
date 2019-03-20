package ui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;

import controller.AppController;
import model.Score;

public class JPanelMain extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public JPanelMain() {
		setLayout(null);
		setSize(600, 500);
		setupTable();
		setupButtons();
	}
	
	private void setupButtons() {
		JButton btnNewGame = new JButton("Play");
		btnNewGame.setBounds(150, 350, 89, 23);
		btnNewGame.addActionListener(e -> AppController.getInstance().showGame());
		add(btnNewGame);
		JButton btnScores = new JButton("Scores");
		btnScores.setBounds(250, 350, 89, 23);
		btnScores.addActionListener(e -> AppController.getInstance().showScores());
		add(btnScores);
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(350, 350, 89, 23);
		btnExit.addActionListener(e -> AppController.getInstance().saveAndExit());
		add(btnExit);
	}
	
	private void setupTable() {
		String[] columnNames = {"Date","Score"};
		JTable table = new JTable(getTableData(), columnNames);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85,70,400,250);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
	}
	
	private String[][] getTableData() {
		List<Score> scores = AppController.getInstance().getUserScores();
		String[][] array = new String[scores.size()][2];
		for (int i = 0; i < scores.size(); i++) {
			array[i][0] = AppController.getInstance().formatter.format(scores.get(i).getDate());
			array[i][1] = String.valueOf(scores.get(i).getPoints());
		}
		return array;
	}
}
