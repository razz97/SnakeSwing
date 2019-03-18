package ui.panels.scores;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.AppController;
import model.Score;
import model.User;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class JPanelFilterScore extends JPanel {
	
	private DefaultListModel<String> model;
	private JList<String> list;
	private List<User> users;
	
	public JPanelFilterScore() {
		setLayout(null);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5000));
		spinner.setBounds(37, 33, 228, 20);
		add(spinner);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(e -> updateModel((int) spinner.getValue()));
		btnFilter.setBounds(306, 32, 89, 23);
		add(btnFilter);
		
		users = AppController.getInstance().getUsers();	
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setBounds(37, 78, 358, 198);
		add(list);
		
		updateModel(0);
		
		
	}
	
	private void updateModel(int min) {
		List<String> results = new ArrayList<>();
		for (User user: users) {
			Score max = AppController.getInstance().getMaxScore(user);
			if (max != null && max.getPoints() > min)
				results.add("<html><pre>" + user.getName() + "\t\t" +max.getPoints() + "</pre></html>");
		}
		model.removeAllElements();
		if (!results.isEmpty()) {
			results.forEach(u -> model.addElement(u));
			list.setSelectedIndex(0);
		}
	}
	
	

	private static final long serialVersionUID = 1L;
}
