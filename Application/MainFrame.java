/**
 * Sets the JFrame and certain aspects of it. 
 * 
 * modified     20220620
 * date         20220620
 * @filename    MainFrame.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		initBoard();
	}

	public void initBoard() {
		GamePanel panel = new GamePanel();
		panel.setLocation(0, 0);
		panel.setSize(this.getSize());
		final Color LIGHT_BLUE = new Color(102, 178, 255);
		panel.setBackground(LIGHT_BLUE);
		panel.setVisible(true);
		this.add(panel);

		addKeyListener(new KeyChecker(panel));

	}

	public void paintComponent(Graphics g) {

	}

}