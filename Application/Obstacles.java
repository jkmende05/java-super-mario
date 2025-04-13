/**
 * Uses JPanel Graphics to print out the different obstacles, such as platforms, pipes, ants and bees.
 * 
 * modified     20220620
 * date         20220620
 * @filename    Obstacles.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Obstacles {
	int x;
	int y;
	int width;
	int height;
	int startX;

	Rectangle hitBox;

	public Obstacles(int x, int y, int width, int height) {
		this.x = x;
		startX = x;
		this.y = y;
		this.width = width;
		this.height = height;

		hitBox = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics2D gtd) {
		final Color LIGHT_BLUE = new Color(102, 178, 255);
		gtd.setColor(LIGHT_BLUE);
		gtd.drawRect(x, y, width, height);
	}

	public int set(int cameraX) {
		x = startX + cameraX;
		hitBox.x = x;

		return x;
	}
}
