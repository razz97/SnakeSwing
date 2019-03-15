package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JFrameCustom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JFrameCustom(JSplitPaneCustom splitPane) {
		setSizeAndPosition();
		setIconImage(new ImageIcon("resources" + File.separator + "snake.png").getImage());
		setTitle("SnakeTucom");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(splitPane);		
		setResizable(false);
		setVisible(true);
	}
	
	private void setSizeAndPosition() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 800;
		int height = 500;
		int x = dim.width/2 - width/2;
		int y = dim.height/2 - height/2;
		setBounds(x, y, width, height);
	}
	
		

}
