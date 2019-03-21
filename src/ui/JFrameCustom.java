package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.AppController;

public class JFrameCustom extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JFrameCustom(JSplitPaneCustom splitPane) {
		setSizeAndPosition();
		setImageAndTitle();
		setCloseOperations();
		add(splitPane);
		setVisible(true);
	}
		
	private void setSizeAndPosition() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 800;
		int height = 500;
		int x = dim.width/2 - width/2;
		int y = dim.height/2 - height/2;
		setBounds(x, y, width, height);
		setResizable(false);
	}
	
	private void setImageAndTitle() {
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("snake.png")).getImage());
		setTitle("SnakeTucom");
	}
	
	private void setCloseOperations() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent we) {
		    	if (AppController.getInstance().areChangesCommitted()) 
		    		return;
		        int result = JOptionPane.showConfirmDialog(JFrameCustom.this,
		            "Save your games?", "There are unsaved changes",
		            JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION)
		        	AppController.getInstance().save();
		      }
		});
	}
}
