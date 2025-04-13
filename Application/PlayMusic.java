/**
 * Will play different sound files when called upon
 * 
 * modified     20220620
 * date         20220620
 * @filename    PlayMusic.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {

	public static boolean play = true; // Boolean helps with muting sound effects based on the user's preferences

	public static void main(String[] args) {

	}

	public static void playMusic(String filepath)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File musicPath = new File(filepath);

		if (musicPath.exists()) {
			if (play == true) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				if (filepath.equals("theme.wav")) {
					// Citation for theme.wav: Super Mario Bros. Theme Song. (2008). [YouTube Video]. In YouTube. https://www.youtube.com/watch?v=NTa6Xbzfq1U
					// Will only loop the theme song continuously 
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}

	}

}
