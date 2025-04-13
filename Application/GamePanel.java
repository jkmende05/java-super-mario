
/**
 * Controls most of the game, such as the timer and calling the other classes to be used
 * 
 * modified     20220620
 * date         20220620
 * @filename    GamePanel.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	Player myPlayer;
	Menu menu;
	Instructions instructions;
	FinishedGame finishedGame;
	Leaderboard leaderboard;
	PlayMusic playMusic;

	ArrayList<Obstacles> brickGround = new ArrayList<>();
	ArrayList<Obstacles> pipes = new ArrayList<>();
	ArrayList<Obstacles> platforms = new ArrayList<>();
	ArrayList<Obstacles> flagPole = new ArrayList<>();
	ArrayList<Obstacles> stairsOne = new ArrayList<>();
	ArrayList<Obstacles> stairsTwo = new ArrayList<>();
	ArrayList<Obstacles> stairsThree = new ArrayList<>();
	ArrayList<Obstacles> stairsFour = new ArrayList<>();
	ArrayList<Obstacles> stairsFive = new ArrayList<>();
	ArrayList<Obstacles> stairsSix = new ArrayList<>();
	ArrayList<Obstacles> stairsSeven = new ArrayList<>();
	ArrayList<Obstacles> stairsEight = new ArrayList<>();
	ArrayList<Obstacles> background = new ArrayList<>();
	ArrayList<Obstacles> ants = new ArrayList<>();
	ArrayList<Obstacles> bees = new ArrayList<>();

	int[] xCoordGround = { -186, 0, 186, 372, 558, 744, 930, 1116, 1302, 1488, 1774, 1960, 3500, 3686, 3872, 4058, 4244,
			4430, 4616, 4802, 4988, 5174, 5360, 5546, 5732, 5918, 7000, 7186, 7372, 7600, 7772, 7958, 8144, 8330, 8516,
			8702, 8888, 9074 };

	int[] xCoordPipe = { 350, 1627, 2099, 2550, 2743, 3500, 6056, 4900, 5212, 7000, 7510, 7600 };
	int[] yCoordPipe = { 490, 490, 490, 422, 422, 490, 490, 372, 372, 490, 490, 490 };

	int[] xCoordPlatform = { 550, 1170, 1290, 2300, 2550, 2670, 2875, 3100, 3325, 3700, 4100, 4500, 4900, 5020, 5140,
			6250, 6370, 6670, 6700 };
	int[] yCoordPlatform = { 420, 420, 420, 420, 470, 470, 410, 350, 290, 420, 420, 420, 420, 420, 420, 450, 450, 400,
			400 };

	// Although setting one value looks weird, it was to prepare for the potentially additional worlds
	// In order to add additional worlds, all we would have to do is change these values and potential increase the size of the arrays
	int[] xStairsOne = { 8375 };
	int[] xStairsTwo = { 8400 };
	int[] xStairsThree = { 8425 };
	int[] xStairsFour = { 8450 };
	int[] xStairsFive = { 8475 };
	int[] xStairsSix = { 8500 };
	int[] xStairsSeven = { 8525 };
	int[] xStairsEight = { 8550 };

	int[] yStairsOne = { 508 };
	int[] yStairsTwo = { 485 };
	int[] yStairsThree = { 462 };
	int[] yStairsFour = { 435 };
	int[] yStairsFive = { 414 };
	int[] yStairsSix = { 385 };
	int[] yStairsSeven = { 360 };
	int[] yStairsEight = { 335 };

	int[] xCoordBees = { 1577, 1170, 2693, 3795, 4285, 4775, 5265, 5755, 5050, 7250 };
	int[] yCoordBees = { 485, 370, 420, 485, 485, 485, 485, 485, 370, 485 };
	int[] originalYCoordBees = { 485, 370, 420, 485, 485, 485, 485, 485, 370, 485 };
	int[] beesMaxX = { 1627, 1410, 2690, 6000, 6000, 6000, 6000, 6000, 5150, 7400 };
	int[] beesMinX = { 398, 1170, 2598, 3550, 3550, 3550, 3550, 3550, 5050, 7050 };
	String[] beesDirection = { "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left" };

	int[] xCoordAnts = { 398, 987, 2598, 3550, 4040, 4530, 5020, 5510, 6000, 7050, 7460 };
	int[] yCoordAnts = { 485, 485, 420, 485, 485, 485, 485, 485, 485, 485, 485 };
	int[] originalYCoordAnts = { 485, 485, 420, 485, 485, 485, 485, 485, 485, 485, 485 };
	int[] antsMaxX = { 1627, 1627, 2690, 6000, 6000, 6000, 6000, 6000, 6000, 7400, 7400 };
	int[] antsMinX = { 398, 398, 2598, 3550, 3550, 3550, 3550, 3550, 3550, 7050, 7050 };
	String[] antsDirection = { "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left", "Left" };

	int cameraX;

	Timer gameTimer;

	Image facingLeft = new ImageIcon("facingLeft.png").getImage(); // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image facingRight = new ImageIcon("facingRight.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image runningLeftOne = new ImageIcon("runningLeftOne.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image runningLeftTwo = new ImageIcon("runningLeftTwo.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image runningRightOne = new ImageIcon("runningOne.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image runningRightTwo = new ImageIcon("runningTwo.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image lookingLeft = new ImageIcon("lookingLeft.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif
	Image lookingRight = new ImageIcon("lookingRight.png").getImage();  // Citation: (2022). Pixilart.com. https://art.pixilart.com/b0b0251ac487330.gif

	ImageIcon mario = new ImageIcon();

	ImageIcon brick = new ImageIcon("ground.jpeg"); // Citaion: (2022b). Digitaloceanspaces.com. http://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/de415a20aba57ef.png
	ImageIcon pipe = new ImageIcon("pipe.png"); // Citation: Super Mario Green Pipe. (n.d.). PNGEgg. https://e7.pngegg.com/pngimages/326/204/png-clipart-green-pipe-super-mario-bros-3-super-mario-bros-2-8-bit-angle-text.png
	ImageIcon platform = new ImageIcon("platform.jpeg"); // Citation: (2022c). Seekpng.com. https://www.seekpng.com/png/small/911-9114175_mario-brick-amber.png

	ImageIcon pole = new ImageIcon("flagPole.png"); // Citation: (2022d). Media-Amazon.com. https://m.media-amazon.com/images/I/21yjDJZ4XGL._AC_.jpg

	ImageIcon oneBrick = new ImageIcon("stairsOne.png"); // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon twoBricks = new ImageIcon("stairsTwo.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon threeBricks = new ImageIcon("stairsThree.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon fourBricks = new ImageIcon("stairsFour.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon fiveBricks = new ImageIcon("stairsFive.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon sixBricks = new ImageIcon("stairsSix.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon sevenBricks = new ImageIcon("stairsSeven.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU
	ImageIcon eightBricks = new ImageIcon("stairsEight.png");  // Citation: (2022). Gstatic.com. https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX7OBylMmHAyIY_aJ-f1_ZX7MiS7-t6cKSpWedopA4EJMiHrZwoq3cAI9cESXb_B0zWAU&usqp=CAU

	ImageIcon backgroundClouds = new ImageIcon("backgroundClouds.png"); // Citation: (2022). Pngitem.com. https://www.pngitem.com/pimgs/m/154-1543258_super-mario-cloud-png-transparent-png.png

	ImageIcon antLeft = new ImageIcon("antLeft.png"); // Citation: (2022). Digitaloceanspaces.com. http://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/e6857f4bfbf4d25.png
	ImageIcon antRight = new ImageIcon("antRight.png"); // Citation: (2022). Digitaloceanspaces.com. http://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/e6857f4bfbf4d25.png
	ImageIcon beeLeft = new ImageIcon("beeLeft.png"); // Citation:  (2022). Dreamstime.com. https://thumbs.dreamstime.com/b/bee-pixel-bee-image-vector-illustration-pixel-art-bee-pixel-bee-image-vector-illustration-pixel-art-221778062.jpg
	ImageIcon beeRight = new ImageIcon("beeRight.png"); // Citation:(2022). Dreamstime.com. https://thumbs.dreamstime.com/b/bee-pixel-bee-image-vector-illustration-pixel-art-bee-pixel-bee-image-vector-illustration-pixel-art-221778062.jpg

	int runningRight = 0;
	int runningLeft = 0;
	int lives = 4;

	long score = 0;
	long playerScore = 0;

	public static long startTime = 0;
	long endTime = 0;

	public static boolean isFinished = false;
	boolean playerDies = false;
	boolean showLeaderboard = false;

	public static enum gameState {
		MENU, GAME, INSTRUCTIONS, FINISHED_GAME, LEADERBOARD,
	};

	// Sets the opening game state to MENU;
	public static gameState state = gameState.MENU;

	public GamePanel() {

		menu = new Menu();
		this.addMouseListener(new MouseInput());

		playMusic = new PlayMusic();

		finishedGame = new FinishedGame();

		instructions = new Instructions();

		leaderboard = new Leaderboard();

		myPlayer = new Player(75, 75, this);
		mario.setImage(facingRight);
		reset();

		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			public void run() {

				myPlayer.set();

				if (playerDies == true) {
					reset();
				}

				if (lives <= 0) {
					state = gameState.FINISHED_GAME;
				}

				for (Obstacles ground : brickGround) {
					ground.set(cameraX);
				}
				for (Obstacles pipes : pipes) {
					pipes.set(cameraX);
				}
				for (Obstacles brickPlatform : platforms) {
					brickPlatform.set(cameraX);
				}
				for (Obstacles flag : flagPole) {
					flag.set(cameraX);
				}
				for (Obstacles stairs : stairsOne) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsTwo) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsThree) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsFour) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsFive) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsSix) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsSeven) {
					stairs.set(cameraX);
				}
				for (Obstacles stairs : stairsEight) {
					stairs.set(cameraX);
				}
				for (Obstacles background : background) {
					background.set(cameraX);
				}

				for (int i = 0; i < xCoordAnts.length; i++) {
					if (xCoordAnts[i] > antsMinX[i] && antsDirection[i].equals("Left")) {
						xCoordAnts[i]--;
					}
					if (xCoordAnts[i] < antsMaxX[i] && antsDirection[i].equals("Right")) {
						xCoordAnts[i]++;
					}
					if (antsDirection[i].equals("Left") && xCoordAnts[i] == antsMinX[i]) {
						antsDirection[i] = "Right";
					}
					if (antsDirection[i].equals("Right") && xCoordAnts[i] == antsMaxX[i]) {
						antsDirection[i] = "Left";
					}
				}

				makeAnts();

				for (int i = 0; i < xCoordBees.length; i++) {
					if (xCoordBees[i] > beesMinX[i] && beesDirection[i].equals("Left")) {
						xCoordBees[i]--;
					}
					if (xCoordBees[i] < beesMaxX[i] && beesDirection[i].equals("Right")) {
						xCoordBees[i]++;
					}
					if (beesDirection[i].equals("Left") && xCoordBees[i] == beesMinX[i]) {
						beesDirection[i] = "Right";
					}
					if (beesDirection[i].equals("Right") && xCoordBees[i] == beesMaxX[i]) {
						beesDirection[i] = "Left";
					}
				}

				makeBees();

				for (Obstacles bees : bees) {
					bees.set(cameraX);
				}

				for (Obstacles ants : ants) {
					ants.set(cameraX);
				}

				repaint();
			}

		}, 0, 15);

	}

	public void reset() {

		lives--;

		cameraX = 150;

		myPlayer.x = 312;
		myPlayer.y = 460;
		myPlayer.xSpeed = 0;
		myPlayer.ySpeed = 0;

		isFinished = false;
		playerDies = false;

		brickGround.clear();
		pipes.clear();
		platforms.clear();

		makeGround();
		makePipes();
		makePlatforms();
		makeFlag();
		makeStairs();
		makeBees();
		makeAnts();
		setBackground();

	}

	public void setBackground() {
		background.add(new Obstacles(0, 0, 10000, 600));
	}

	public void makeBees() {
		bees.clear();

		for (int i = 0; i < xCoordBees.length; i++) {
			bees.add(new Obstacles(xCoordBees[i], yCoordBees[i], 50, 50));
		}
	}

	public void makeAnts() {
		ants.clear();
		// Clears the ArrayList since the xCoordubates would have changed due to the
		// timer
		for (int i = 0; i < xCoordAnts.length; i++) {
			ants.add(new Obstacles(xCoordAnts[i], yCoordAnts[i], 50, 50));
		}
	}

	public void makeGround() {
		for (int i = 0; i < xCoordGround.length; i++) {
			brickGround.add(new Obstacles(xCoordGround[i], 535, 186, 50));
		}
	}

	public void makePipes() {
		for (int i = 0; i < xCoordPipe.length; i++) {
			pipes.add(new Obstacles(xCoordPipe[i], yCoordPipe[i], 47, 48));
		}
	}

	public void makePlatforms() {
		for (int i = 0; i < xCoordPlatform.length; i++) {
			platforms.add(new Obstacles(xCoordPlatform[i], yCoordPlatform[i], 120, 30));
		}
	}

	public void makeFlag() {
		flagPole.add(new Obstacles(8750, 230, 72, 300));
	}

	public void makeStairs() {
		for (int i = 0; i < xStairsOne.length; i++) {
			stairsOne.add(new Obstacles(xStairsOne[i], yStairsOne[i], 25, 27));
		}

		for (int i = 0; i < xStairsTwo.length; i++) {
			stairsTwo.add(new Obstacles(xStairsTwo[i], yStairsTwo[i], 25, 50));
		}

		for (int i = 0; i < xStairsThree.length; i++) {
			stairsThree.add(new Obstacles(xStairsThree[i], yStairsThree[i], 25, 73));
		}

		for (int i = 0; i < xStairsFour.length; i++) {
			stairsFour.add(new Obstacles(xStairsFour[i], yStairsFour[i], 25, 100));
		}

		for (int i = 0; i < xStairsFive.length; i++) {
			stairsFive.add(new Obstacles(xStairsFive[i], yStairsFive[i], 25, 121));
		}

		for (int i = 0; i < xStairsSix.length; i++) {
			stairsSix.add(new Obstacles(xStairsSix[i], yStairsSix[i], 25, 150));
		}

		for (int i = 0; i < xStairsSeven.length; i++) {
			stairsSeven.add(new Obstacles(xStairsSeven[i], yStairsSeven[i], 25, 175));
		}

		for (int i = 0; i < xStairsEight.length; i++) {
			stairsEight.add(new Obstacles(xStairsEight[i], yStairsEight[i], 50, 200));
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (state == gameState.GAME) {
			Graphics2D gtd = (Graphics2D) g;

			final Color LIGHT_BROWN = new Color(196, 121, 75);

			try {
				for (Obstacles ground : brickGround) {
					ground.draw(gtd);
					brick.paintIcon(this, g, ground.set(cameraX), 535);
				}

				for (int i = 0; i < brickGround.size(); i++) {
					Obstacles ground = new Obstacles(xCoordGround[i], 535, 186, 50);
				}

				int pipeCounter = 0;
				for (Obstacles pipes : pipes) {
					pipes.draw(gtd);
					pipe.paintIcon(this, g, pipes.set(cameraX), yCoordPipe[pipeCounter]);
					pipeCounter++;
				}

				for (Obstacles flag : flagPole) {
					flag.draw(gtd);
					pole.paintIcon(this, g, flag.set(cameraX), 235);
				}

				int platformCounter = 0;
				for (Obstacles brickPlatform : platforms) {
					brickPlatform.draw(gtd);
					platform.paintIcon(this, g, brickPlatform.set(cameraX), yCoordPlatform[platformCounter]);
					platformCounter++;
				}

				int brickOneCounter = 0;
				for (Obstacles stairs : stairsOne) {
					stairs.draw(gtd);
					if (brickOneCounter < yStairsOne.length)
						oneBrick.paintIcon(this, g, stairs.set(cameraX), yStairsOne[brickOneCounter]);
					brickOneCounter++;
				}

				int brickTwoCounter = 0;
				for (Obstacles stairs : stairsTwo) {
					stairs.draw(gtd);
					if (brickTwoCounter < yStairsTwo.length)
						twoBricks.paintIcon(this, g, stairs.set(cameraX), yStairsTwo[brickTwoCounter]);
					brickTwoCounter++;
				}

				int brickThreeCounter = 0;
				for (Obstacles stairs : stairsThree) {
					stairs.draw(gtd);
					if (brickThreeCounter < yStairsThree.length)
						threeBricks.paintIcon(this, g, stairs.set(cameraX), yStairsThree[brickThreeCounter]);
					brickThreeCounter++;
				}

				int brickFourCounter = 0;
				for (Obstacles stairs : stairsFour) {
					stairs.draw(gtd);
					if (brickFourCounter < yStairsFour.length)
						fourBricks.paintIcon(this, g, stairs.set(cameraX), yStairsFour[brickFourCounter]);
					brickFourCounter++;
				}

				int brickFiveCounter = 0;
				for (Obstacles stairs : stairsFive) {
					stairs.draw(gtd);
					if (brickFiveCounter < yStairsFive.length)
						fiveBricks.paintIcon(this, g, stairs.set(cameraX), yStairsFive[brickFiveCounter]);
					brickFiveCounter++;
				}

				int brickSixCounter = 0;
				for (Obstacles stairs : stairsSix) {
					stairs.draw(gtd);
					if (brickSixCounter < yStairsSix.length)
						sixBricks.paintIcon(this, g, stairs.set(cameraX), yStairsSix[brickSixCounter]);
					brickSixCounter++;
				}

				int brickSevenCounter = 0;
				for (Obstacles stairs : stairsSeven) {
					stairs.draw(gtd);
					if (brickSevenCounter < yStairsSeven.length)
						sevenBricks.paintIcon(this, g, stairs.set(cameraX), yStairsSeven[brickSevenCounter]);
					brickSevenCounter++;
				}

				int brickEightCounter = 0;
				for (Obstacles stairs : stairsEight) {
					stairs.draw(gtd);
					if (brickEightCounter < yStairsEight.length)
						eightBricks.paintIcon(this, g, stairs.set(cameraX), yStairsEight[brickEightCounter]);
					brickEightCounter++;
				}

				for (Obstacles background : background) {
					background.draw(gtd);
					backgroundClouds.paintIcon(this, g, background.set(cameraX), 0);
				}

				int antsCounter = 0;
				for (Obstacles ant : ants) {
					ant.draw(gtd);
					if (antsCounter < ants.size()) {
						if (antsDirection[antsCounter].equals("Left")) {
							antLeft.paintIcon(this, g, ant.set(cameraX), yCoordAnts[antsCounter]);
						}
						if (antsDirection[antsCounter].equals("Right")) {
							antRight.paintIcon(this, g, ant.set(cameraX), yCoordAnts[antsCounter]);
						}
					}
					antsCounter++;
				}

				int beesCounter = 0;
				for (Obstacles bee : bees) {
					bee.draw(gtd);
					if (beesCounter < bees.size()) {
						if (beesDirection[beesCounter].equals("Left")) {
							beeLeft.paintIcon(this, g, bee.set(cameraX), yCoordBees[beesCounter]);
						}
						if (beesDirection[beesCounter].equals("Right")) {
							beeRight.paintIcon(this, g, bee.set(cameraX), yCoordBees[beesCounter]);
						}
					}
					beesCounter++;
				}
			} catch (Exception e) {

			}

			myPlayer.draw(gtd);
			mario.paintIcon(this, g, myPlayer.x, myPlayer.y);

			if (isFinished == true) {
				try {
					playMusic.playMusic("BonusPoints.wav"); // If you finish the game and interact with the flag pole, this clip will play
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				restartGame();
				state = gameState.FINISHED_GAME;
			}

			g.setColor(LIGHT_BROWN);
			Font fontOne = new Font("Arial", Font.BOLD, 20);
			g.setFont(fontOne);
			;
			g.drawString("Lives Remaining: " + lives, 50, 50);

			g.drawString("Scores: " + score, 50, 100);
			playerScore = score;

			g.fillRect(500, 30, 195, 25);
			g.fillRect(500, 60, 195, 25);

			g.setColor(Color.WHITE);
			g.drawString("Press R to Restart", 510, 50);
			g.drawString("Press M to Mute", 510, 80);

		} else if (state == gameState.MENU) {
			menu.paint(g);
		} else if (state == gameState.INSTRUCTIONS) {
			instructions.paint(g);
		} else if (state == gameState.FINISHED_GAME) {
			finishedGame.paint(g, playerScore, lives);
			restartGame();
		} else if (state == gameState.LEADERBOARD) {
			leaderboard.paint(g);
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (state == gameState.GAME) {

			if (key == 65 || key == 37) {
				myPlayer.keyLeft = true;

				if (runningLeft == 0) {
					mario.setImage(lookingLeft);
				} else if (runningLeft == 1) {
					mario.setImage(runningLeftOne);
				} else if (runningLeft == 3) {
					mario.setImage(runningLeftTwo);
				} else if (runningLeft == 5) {
					runningLeft = 0;
				}

				runningLeft++;
			}
			if (key == 87 || key == 38) {
				myPlayer.keyUp = true;
			}
			if (key == 68 || key == 39) {
				myPlayer.keyRight = true;

				if (runningRight == 0) {
					mario.setImage(lookingRight);
				} else if (runningRight == 1) {
					mario.setImage(runningRightOne);
				} else if (runningRight == 3) {
					mario.setImage(runningRightTwo);
				} else if (runningRight == 5) {
					runningRight = 0;
				}

				runningRight++;
			}
			if (key == 83 || key == 40) {
				myPlayer.keyDown = true;
			}

			if (myPlayer.keyRight == true && myPlayer.keyUp == true) {
				mario.setImage(runningRightOne);
			}

			if (myPlayer.keyLeft == true && myPlayer.keyUp == true) {
				mario.setImage(runningLeftOne);
			}

			if (key == 82) {
				restartGame();
			}

			if (key == 77) {
				if (playMusic.play == true) {
					playMusic.play = false;
					// Will allow the User's to mute all flowers, except for the power
				} else {
					playMusic.play = true;
				}
			}
		}

		if (FinishedGame.enterScore) {
			// Add backspace
			if (FinishedGame.name.length() < 10) {
				if (e.getKeyCode() == 8) {
					if (FinishedGame.name.length() >= 1) {
						FinishedGame.name = FinishedGame.name.substring(0, FinishedGame.name.length() - 1);
					}
				} else if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {
					FinishedGame.name += e.getKeyText(e.getKeyCode());
				} else if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
					FinishedGame.name += e.getKeyText(e.getKeyCode());
				} else if (e.getKeyCode() == 32) {
					FinishedGame.name = FinishedGame.name + " ";
				}
			}
			if (FinishedGame.name.length() == 10) {
				if (e.getKeyCode() == 8) {
					FinishedGame.name = FinishedGame.name.substring(0, FinishedGame.name.length() - 1);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (state == gameState.GAME) {
			int key = e.getKeyCode();
			if (key == 65 || key == 37) {
				myPlayer.keyLeft = false;
				mario.setImage(lookingLeft);
				runningLeft = 0;
			}
			if (key == 87 || key == 38) {
				myPlayer.keyUp = false;
			}
			if (key == 68 || key == 39) {
				myPlayer.keyRight = false;
				mario.setImage(lookingRight);
				runningRight = 0;
			}
			if (key == 83 || key == 40) {
				myPlayer.keyDown = false;
			}
		}
	}

	public void restartGame() {
		for (int i = 0; i < yCoordBees.length; i++) {
			yCoordBees[i] = originalYCoordBees[i];
		}
		for (int i = 0; i < yCoordAnts.length; i++) {
			yCoordAnts[i] = originalYCoordAnts[i];
		}

		reset();

		lives = 3;
		score = 0;

		myPlayer.keyLeft = false;
		myPlayer.keyRight = false;
		myPlayer.keyUp = false;
		myPlayer.keyDown = false;

	}

}
