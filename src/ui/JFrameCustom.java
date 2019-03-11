package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JFrameCustom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JFrameCustom(JSplitPaneCustom splitPane) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(800, 500);
		this.setIconImage(new ImageIcon("resources" + File.separator + "snake.png").getImage());
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle("SnakeTucom");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.add(splitPane);
		setResizable(false);
		this.setVisible(true);
	}
	
		

}
