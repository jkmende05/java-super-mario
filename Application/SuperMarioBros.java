/**
 * Acts as the main object for this game.
 * 
 * modified     20220620
 * date         20220620
 * @filename    SuperMarioBros.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class SuperMarioBros {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		MainFrame frame = new MainFrame();

		PlayMusic playMusic = new PlayMusic();

		frame.setSize(700, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Sets the frame to the middle of your screen
		frame.setLocation((int) (screenSize.getWidth() / 2 - frame.getSize().getWidth() / 2),
				(int) (screenSize.getHeight() / 2 - frame.getSize().getHeight() / 2));

		frame.setTitle("Super Mario Bros");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		playMusic.playMusic("theme.wav"); // Will play the theme song once the JFrame is opened and the Java Application is run
		// Citation for theme.wav: Super Mario Bros. Theme Song. (2008). [YouTube Video]. In YouTube. https://www.youtube.com/watch?v=NTa6Xbzfq1U

	}

}


