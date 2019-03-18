package ui.panels;

import javax.swing.JPanel;

import tmp.Snake;

public class JPanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanelGame() {
		add(new Snake());
	}

}
