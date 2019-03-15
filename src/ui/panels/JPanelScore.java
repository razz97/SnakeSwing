package ui.panels;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ui.panels.scores.JSplitPaneScore;

import javax.swing.JSplitPane;

public class JPanelScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public JPanelScore() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 83, 430, 151);
		add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Users list", null, panel, null);
		panel.setLayout(null);
		
		JSplitPane splitPane = new JSplitPaneScore();
		splitPane.setBounds(0, 0, 425, 123);
		panel.add(splitPane);
		tabbedPane.addTab("Best user score", null, new JPanel(), null);
		tabbedPane.addTab("Score filter", null, new JPanel(), null);
	}
}
