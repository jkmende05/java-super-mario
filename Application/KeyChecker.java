/**
 * Implements the KeyAdapter for the GamePanel
 * 
 * modified     20220620
 * date         20220620
 * @filename    KeyChecker.java
 * @author      Justin Mende,
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter {

	GamePanel panel;

	public KeyChecker(GamePanel panel) {
		this.panel = panel;
	}

	public void keyPressed(KeyEvent e) {
		panel.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		panel.keyReleased(e);
	}

}
