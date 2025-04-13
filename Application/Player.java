
/**
 * Controls the movement of the player and its interactions with obstacles
 * 
 * modified     20220620
 * date         20220620
 * @filename    Player.java
 * @author      Justin Mende, Michael Wang, Ridwanul Haque
 * @version     1.0
 * @see         ICS4U Content
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

public class Player {

	GamePanel panel;
	PlayMusic playMusic = new PlayMusic();

	int x, y, width, height;
	double xSpeed, ySpeed;

	Rectangle box;

	boolean keyLeft, keyRight, keyUp, keyDown;

	public Player(int x, int y, GamePanel panel) {

		this.panel = panel;
		this.x = x;
		this.y = y;

		width = 60;
		height = 75;
		box = new Rectangle(x, y, width, height);
	}

	public void set() {
		try {
			if ((keyLeft == true && keyRight == true) || (keyRight == false && keyLeft == false)) {
				xSpeed *= 0.8;
			} else if (keyLeft == true && keyRight == false) {
				xSpeed = xSpeed - 1;
			} else if (keyRight == true && keyLeft == false) {
				xSpeed = xSpeed + 1;
			}

			if (xSpeed > 0 && xSpeed < 0.75) {
				// Prevents the player from glidding to a stop, allows the player to stop right away once the key is released
				xSpeed = 0;
			}

			if (xSpeed < 0 && xSpeed > -0.75) {
				xSpeed = 0;
			}

			if (xSpeed > 5) {
				xSpeed = 5;
			}

			if (xSpeed < -5) {
				xSpeed = -5;
			}

			if (keyUp == true) {
				// Will only allow the player to jump if they are currently touching the ground

				box.y++;

				for (Obstacles ground : panel.brickGround) {
					if (ground.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
						// Citation for jumping.wav (will only be listed once) Mario “Yahoo” Sound Effect. (n.d.). Www.youtube.com. Retrieved June 20, 2022, from https://www.youtube.com/watch?v=bnendMAwkQQ

					}
				}
				for (Obstacles pipes : panel.pipes) {
					if (pipes.hitBox.intersects(box)) {
						playMusic.playMusic("jumping.wav");
						ySpeed = -9;
					}
				}
				for (Obstacles platform : panel.platforms) {
					if (platform.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsOne) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsTwo) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsThree) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsFour) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsFive) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsSix) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsSeven) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				for (Obstacles stairs : panel.stairsEight) {
					if (stairs.hitBox.intersects(box)) {
						ySpeed = -9;
						playMusic.playMusic("jumping.wav");
					}
				}

				box.y--;

			}

			ySpeed = ySpeed + 0.3;

			// Horizontal Collisions between the player and other obstacles
			box.x += xSpeed;
			for (Obstacles ground : panel.brickGround) {
				if (box.intersects(ground.hitBox)) {
					box.x -= xSpeed;
					while (!ground.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles ground : panel.pipes) {
				if (box.intersects(ground.hitBox)) {
					box.x -= xSpeed;
					while (!ground.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles platform : panel.platforms) {
				if (box.intersects(platform.hitBox)) {
					box.x -= xSpeed;
					while (!platform.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsOne) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsTwo) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsThree) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsFour) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsFive) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsSix) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsSeven) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;
				}
			}

			for (Obstacles bricks : panel.stairsEight) {
				if (box.intersects(bricks.hitBox)) {
					box.x -= xSpeed;
					while (!bricks.hitBox.intersects(box)) {
						box.x += Math.signum(xSpeed);
					}
					box.x -= Math.signum(xSpeed);
					panel.cameraX += x - box.x;
					xSpeed = 0;
					box.x = x;

				}
			}

			// Vertical Collisions
			box.y += ySpeed;
			for (Obstacles ground : panel.brickGround) {
				if (box.intersects(ground.hitBox)) {
					box.y -= ySpeed;
					while (!ground.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles ground : panel.pipes) {
				if (box.intersects(ground.hitBox)) {
					box.y -= ySpeed;
					while (!ground.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles platform : panel.platforms) {
				if (box.intersects(platform.hitBox)) {
					box.y -= ySpeed;
					while (!platform.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsOne) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsTwo) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsThree) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsFour) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsFive) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsSix) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsSeven) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			for (Obstacles stairs : panel.stairsEight) {
				if (box.intersects(stairs.hitBox)) {
					box.y -= ySpeed;
					while (!stairs.hitBox.intersects(box)) {
						box.y += Math.signum(ySpeed);
					}
					box.y -= Math.signum(ySpeed);
					ySpeed = 0;
					y = box.y;
				}
			}

			int antCounter = 0;
			for (Obstacles ant : panel.ants) {
				if (box.intersects(ant.hitBox)) {
					if (box.getBounds().intersects(ant.hitBox.getBounds())) {
						if (y + height <= ant.hitBox.getY() + ant.hitBox.getHeight() / 2) {
							panel.yCoordAnts[antCounter] = 10000;
							panel.score += 100;
							panel.playerScore += 100; // If the player kills the enemy, there sccore will increase by 100;
						} else {
							panel.playerDies = true;
							playMusic.playMusic("Byee.wav"); // Will play this sound if the playerDies
						}
					}
				}
				antCounter++;
			}

			int beeCounter = 0;
			for (Obstacles bee : panel.bees) {
				if (box.intersects(bee.hitBox)) {
					if (box.getBounds().intersects(bee.hitBox.getBounds())) {
						if (y + height <= bee.hitBox.getY() + bee.hitBox.getHeight() / 2) {
							panel.yCoordBees[beeCounter] = 10000;
							panel.score += 100;
							panel.playerScore += 100;
						} else {
							panel.playerDies = true;
							playMusic.playMusic("Byee.wav");
						}
					}
				}
				beeCounter++;
			}

			// Ends game if the player interacts with the Flag Pole
			for (Obstacles flag : panel.flagPole) {
				if (box.intersects(flag.hitBox)) {
					panel.playerScore = 1000;
					panel.playerScore = panel.playerScore + panel.score
							+ (int) ((flag.hitBox.getMaxY() - (y + 75)) * 5);
					panel.endTime = System.currentTimeMillis();
					panel.playerScore = panel.playerScore
							+ (600 - TimeUnit.MILLISECONDS.toSeconds(panel.endTime - panel.startTime)) 
							+ panel.lives * 50; // Adds to the player's score based on the timer it took them to complete the game and how many lives they have left
					panel.isFinished = true;
				}
			}

			if (x < 400 || keyLeft == true) {
				x += xSpeed;
			} else {
				panel.cameraX -= xSpeed;
			}
			y += ySpeed;

			if (x < 0) {
				x = 0;
			}

			box.x = x;
			box.y = y;

			// Reset after death
			if (y > 700) {
				// Will execute if you fall to your death
				panel.playerScore = panel.score;
				panel.reset();
				playMusic.playMusic("Byee.wav"); // Will play this file when you die
			}

		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D gtd) {

	}
}
