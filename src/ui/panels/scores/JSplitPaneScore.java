package ui.panels.scores;

import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import controller.AppController;
import model.Score;
import model.User;

import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;

public class JSplitPaneScore extends JSplitPane {
	
	private static final long serialVersionUID = 1L;
	private JPanelUserInfo panelUser;

	public JSplitPaneScore() {
		
		setDividerLocation(200);
		JPanel panel = new JPanel();
		setLeftComponent(panel);
		panel.setLayout(null);
		

		DefaultListModel<User> model = new DefaultListModel<>();
		List<User> users = AppController.getInstance().getUsers();		
		users.forEach(u -> model.addElement(u));
		JList<User> list = new JList<>(model);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting())
				panelUser.setUser(model.get(list.getSelectedIndex()));
		});
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(0, 0, 199, 298);
		panel.add(list);
		
		panelUser = new JPanelUserInfo(users.get(0));
		setRightComponent(panelUser);
	}

	

}
