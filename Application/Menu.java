/**
 * Displays the Home Page of the game, which is the first thing you see when you open our game
 * 
 * modified     20220620
 * date         20220620
 * @filename    Menu.java
 * @author      Justin Mende
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	Rectangle playButton = new Rectangle(250, 425, 200, 50);
	Rectangle instructionsButton = new Rectangle(250, 500, 200, 50);

	public void paint(Graphics g) {
		final Color LIGHT_BROWN = new Color(196, 121, 75);

		g.setColor(LIGHT_BROWN);
		g.fillRect(150, 75, 400, 250);

		g.setColor(Color.WHITE);
		g.drawRect(150, 75, 400, 250);

		Font fontOne = new Font("Comic Sans MS", Font.BOLD, 60);
		g.setFont(fontOne);

		g.drawString("Super Mario", 175, 175);
		g.drawString("Bros", 275, 260);

		Graphics2D g2D = (Graphics2D) g;

		Font fontTwo = new Font("Comic Sans MS", Font.PLAIN, 30);
		g.setFont(fontTwo);

		g2D.draw(playButton);
		g.drawString("Play", 320, 460);

		g2D.draw(instructionsButton);
		g.drawString("Instructions", 265, 535);

	}
}
