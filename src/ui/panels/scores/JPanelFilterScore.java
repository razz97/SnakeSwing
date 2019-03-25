package ui.panels.scores;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.AppController;
import model.Score;
import model.User;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class JPanelFilterScore extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<String> model;
	private JList<String> list;
	private List<User> users;
	
	public JPanelFilterScore() {
		setLayout(null);
		setupFields();
		setupList();
	}
	
	private void setupList() {
		users = AppController.getInstance().getUsers();	
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setBounds(37, 70, 340, 190);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37,70,340,190);
		scrollPane.setViewportView(list);
		add(scrollPane);
		updateModel(0);
	}
	
	private void setupFields() {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 500));
		spinner.setBounds(37, 33, 228, 20);
		add(spinner);
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(e -> updateModel((int) spinner.getValue()));
		btnFilter.setBounds(306, 32, 89, 23);
		add(btnFilter);
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased( final KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
                	btnFilter.doClick();
                }
            }
        } );
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
}
