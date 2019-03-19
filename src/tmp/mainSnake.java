package tmp;

import java.awt.EventQueue;
import javax.swing.JFrame;

import ui.panels.Snake;

public class mainSnake extends JFrame {

	private static final long serialVersionUID = 1L;

	public mainSnake() {
        initUI();
    }
    
    private void initUI() {
        
        add(new Snake());
            
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new mainSnake();
            ex.setVisible(true);
        });
    }
}
