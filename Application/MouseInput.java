/**
 * Will perform different actions depending on the current state of the game when the mouse is pressed down
 * 
 * modified     20220620
 * date         20220620
 * @filename    MouseInput.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MouseInput implements MouseListener {

	GamePanel panel;
	Player myPlayer;
	MainFrame frame;
	FinishedGame finishedGame;
	PlayMusic playMusic;

	String name = "Guest";

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		panel = new GamePanel();
		myPlayer = new Player(75, 75, panel);
		frame = new MainFrame();
		finishedGame = new FinishedGame();
		playMusic = new PlayMusic();

		int mouseX = e.getX();
		int mouseY = e.getY();

		if (GamePanel.state == GamePanel.gameState.MENU) {
			if (mouseX >= 250 && mouseY <= 550) {
				if (mouseX <= 450 && mouseY >= 500) {
					// The location of the instructions "button"
					GamePanel.state = GamePanel.gameState.INSTRUCTIONS;
				}
			}

			if (mouseX >= 250 && mouseY <= 475) {
				if (mouseX <= 450 && mouseY >= 425) {
					// Location of the play button

					GamePanel.state = GamePanel.gameState.GAME;
					
					try {
						playMusic.playMusic("Let's Go.wav"); // Will play this file when the user presses Play from the menu page
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {

						e1.printStackTrace();
					}

					GamePanel.startTime = System.currentTimeMillis();

				}
			}
		}

		if (GamePanel.state == GamePanel.gameState.INSTRUCTIONS) {
			if (mouseX >= 550 && mouseX <= 650) {
				if (mouseY >= 525 && mouseY <= 550) {
					// Location of the back button on the instructions page
					GamePanel.state = GamePanel.gameState.MENU;
				}
			}
		}

		if (GamePanel.state == GamePanel.gameState.FINISHED_GAME) {
			if (mouseX >= 200 && mouseX <= 300) {
				if (mouseY >= 280 && mouseY <= 305) {
					FinishedGame.enterScore = true;
					FinishedGame.name = "";
				}
			}

			if (mouseX >= 250 && mouseX <= 450) {
				if (mouseY >= 470 && mouseY <= 500) {
					GamePanel.state = GamePanel.gameState.LEADERBOARD;
					Leaderboard.topScores.add(finishedGame.enterIntScore + "");
					if (FinishedGame.name.equals("")) {
						// Sets the default name to GUEST
						FinishedGame.name = "GUEST";
					}
					FinishedGame.enterScore = false;
					Leaderboard.leadersNames.add(FinishedGame.name);
					finishedGame.enterIntScore = 0;
					panel.score = 0;
					panel.playerScore = 0;
					
					try {
						File xmlFile = new File("Scores.xml");
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse(xmlFile);
						Element documentElement = document.getDocumentElement();
						Element textNode = document.createElement("name");
						textNode.setTextContent(FinishedGame.name);
						Element textNode1 = document.createElement("score");
						textNode1.setTextContent(FinishedGame.enterIntScore + "");
						Element nodeElement = document.createElement("scores");
						nodeElement.appendChild(textNode);
						nodeElement.appendChild(textNode1);
						documentElement.appendChild(nodeElement);
						document.replaceChild(documentElement, documentElement);
						Transformer tFormer =
						TransformerFactory.newInstance().newTransformer();
						tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
						Source source = new DOMSource(document);
						Result result = new StreamResult(xmlFile);
						tFormer.transform(source, result);


						// Will add the score and the name to the Scores.xml file
						
						} catch (Exception ex) {
						System.out.println(ex);
						} 
				}
			}

			if (mouseX >= 400 && mouseX <= 500) {
				if (mouseY >= 280 && mouseY <= 305) {
					panel.restartGame();
					FinishedGame.enterScore = false;
					GamePanel.state = GamePanel.gameState.GAME;
					panel.playerScore = 0;
				}
			}
		}

		if (GamePanel.state == GamePanel.gameState.LEADERBOARD) {
			if (mouseX >= 500 && mouseX <= 600) {
				if (mouseY >= 500 && mouseY <= 530) {
					panel.restartGame();
					finishedGame.enterIntScore = 0;
					panel.playerScore = 0;
					GamePanel.state = GamePanel.gameState.GAME;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
