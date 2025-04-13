/**
 * Displays the score after the game is finished and gives them the option to add the score to the leaderboard
 * 
 * modified     20220620
 * date         20220620
 * @filename    FinishedGame.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FinishedGame implements ActionListener{
	
	GamePanel panel;
	
	public static boolean enterScore = false;
	
	public static String name  = "";
	
	public static ArrayList<String> leadersNames = new ArrayList<>();
	public static ArrayList<String> topScores = new ArrayList<>();
	
	public static long enterIntScore = 0;
	
	public void paint (Graphics g, long score, int lives) {		
		panel = new GamePanel();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 600);

		g.setColor(Color.RED);
		
		g.setFont(new Font("Arial", Font.BOLD, 50));
	

		g.drawString("Congratulations!", 140, 100);
		
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Your final score was: " + score, 240, 175);
		g.drawString("Would you like to add this score to the leaderboard?", 120, 210);
		g.drawString("Please click on either yes or no below.", 180, 245);
		
		g.fillRect(200, 280, 100, 25);
		g.fillRect(400, 280, 100, 25);
		
		g.setColor(Color.WHITE);
		g.drawString("Yes", 230, 300);
		g.drawString("No", 438, 300);		
		
		if (enterScore == true) {
			g.drawString("Please type out your name now.", 205, 350);
			g.drawString("Once finished, press continue.", 215, 375);
			
			g.drawString("Name: " + name, 15, 410);
			enterIntScore = score;
			
			if (name.length() == 10) {
				g.drawString("Max characters for your name is 10. Press continue when done.", 15, 430);
			}
			
			g.fillRect(250, 470, 200, 30);
			
			g.setColor(Color.BLACK);
			g.drawString("Continue", 310, 492);
			
		}
		
		
		
		Graphics2D g2D = (Graphics2D) g;
		
	}

	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
