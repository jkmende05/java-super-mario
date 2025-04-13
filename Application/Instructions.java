/**
 * Displays the instructions page and the back button
 * 
 * modified     20220620
 * date         20220620
 * @filename    Instructions.java
 * @author      Justin Mende
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Instructions {

	public void paint(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;

		// The instructions were on a doc I made and then downloaded, so no citation is required.
		Image instructions = new ImageIcon("instructions.png").getImage();
		g.drawImage(instructions, 0, 0, null);

		g.setColor(Color.WHITE);
		g.fillRect(550, 525, 100, 25);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.setColor(Color.BLACK);
		g.drawString("Back", 580, 543);
	}
}
