/**
 * Displays the leaderboard of the game (the top 10 scores) in descending order.
 * 
 * modified     20220620
 * date         20220620
 * @filename    Leaderboard.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Leaderboard {

	public static ArrayList<String> leadersNames = new ArrayList<>();
	public static ArrayList<String> topScores = new ArrayList<>();

	public void paint(Graphics g) {
		GamePanel panel = new GamePanel();
		panel.playerScore = 0;
		long[] scores = new long[topScores.size()];
		String[] sortedNames = new String[leadersNames.size()];

		for (int i = 0; i < topScores.size(); i++) {
			scores[i] = Long.parseLong(topScores.get(i));
			sortedNames[i] = leadersNames.get(i);
		}

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 600);

		g.setColor(Color.RED);

		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Leaderboard", 200, 75);

		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Position", 100, 125);

		for (int i = 1; i <= 9; i++) {
			g.drawString(i + "", 125, 125 + 30 * i);
		}
		g.drawString("10", 120, 425);

		g.drawString("Name", 300, 125);
		g.drawString("Score", 500, 125);

		
		// Uses selection sort to sort the scores and the names
		for (int i = 0; i <= scores.length - 2; i++) {
			for (int j = i + 1; j <= scores.length - 1; j++) {
				if (scores[i] < scores[j]) {
					long temp = scores[i];
					scores[i] = scores[j];
					scores[j] = temp;

					String tempName = sortedNames[i];
					sortedNames[i] = sortedNames[j];
					sortedNames[j] = tempName;
				}
			}
		}

		// Displays the top ten scores
		if (topScores.size() <= 10) {
			for (int i = 0; i < topScores.size(); i++) {
				g.drawString(sortedNames[i], 300, 155 + 30 * i);
				g.drawString(scores[i] + "", 500, 155 + 30 * i);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				g.drawString(sortedNames[i], 300, 155 + 30 * i);
				g.drawString(scores[i] + "", 500, 155 + 30 * i);
			}
		}

		g.fillRect(500, 500, 100, 30);

		g.setColor(Color.WHITE);
		g.drawString("Replay", 520, 520);

	}

}
