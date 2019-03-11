package ui.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import controller.AppController;

public class JPanelMain extends JPanel {
	private JTable table;
	
	
	public JPanelMain() {
		setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(62, 210, 89, 23);
		add(btnNewGame);
		
		JButton btnScores = new JButton("Scores");
		btnScores.setBounds(178, 210, 89, 23);
		add(btnScores);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(302, 210, 89, 23);
		add(btnExit);
		
		String[] columnNames = {"Date","Score"};
		Object[][] data = {
				{"dateValue","scoreValue"}
		};
		
		table = new JTable(data,columnNames);
		table.setBounds(114, 53, 200, 100);
		add(table);
	}
}
