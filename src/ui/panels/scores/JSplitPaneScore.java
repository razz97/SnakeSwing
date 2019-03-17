package ui.panels.scores;

import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import controller.AppController;
import model.User;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;

public class JSplitPaneScore extends JSplitPane {
	
	private static final long serialVersionUID = 1L;
	
	private JPanelUserInfo panelUser;
	private DefaultListModel<User> model;
	private List<User> users;
	private JList<User> list;
	
	public JSplitPaneScore() {		
		setDividerLocation(200);
		JPanel panel = new JPanel();
		setLeftComponent(panel);
		panel.setLayout(null);
		
		users = AppController.getInstance().getUsers();	
		model = new DefaultListModel<User>();
		panelUser = new JPanelUserInfo();
		setRightComponent(panelUser);
		
		list = new JList<>(model);	
		list.addListSelectionListener(e -> {
			User user = list.getSelectedValue();
			if (!e.getValueIsAdjusting() && user != null)
				panelUser.setUser(user);
		});
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(0, 0, 199, 298);
		panel.add(list);
		
		updateModel(users);
	}
	
	public void filter(String query) {
		List<User> filtered = users.stream().filter(u -> u.getName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
		updateModel(filtered);
	}
	
	private void updateModel(List<User> newList) {
		model.removeAllElements();
		if (newList.isEmpty())
			panelUser.setDefaultInfo();
		else {
			newList.forEach(u -> model.addElement(u));
			list.setSelectedIndex(0);
		}
	}

	

}
