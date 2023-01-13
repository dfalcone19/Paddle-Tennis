package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents the tennis ball used in the game.
 * 
 * @author Daniel Falcone
 *
 */
public class Ball extends Rectangle {
	/**
	 * Random to be used for determining which initial X and Y direction to send the
	 * ball.
	 */
	private Random rand;
	/** Tracks the horizontal velocity of the ball. */
	private int xVelocity;
	/** Tracks the vertical velocity of the ball. */
	private int yVelocity;
	/** Sets the initial speed of the ball. */
	int initialSpeed = 1;

	/**
	 * Constructor for the ball.
	 * 
	 * @param x      - X location
	 * @param y      - Y location
	 * @param width  - Width of the ball
	 * @param height - Height of the ball
	 */
	public Ball(int x, int y, int width, int height) {
		// calls Rectangle
		super(x, y, width, height);
		rand = new Random();

		int randomXDir = rand.nextInt(2);
		if (randomXDir == 0) {
			// send ball left
			randomXDir--;
		}
		// sends ball to the left or right at the initial speed
		setXDir(randomXDir * initialSpeed);

		int randomYDir = rand.nextInt(2);
		if (randomYDir == 0) {
			// send ball up
			randomYDir--;
		}
		// sends ball up or down at initial speed
		setYDir(randomYDir * initialSpeed);
	}

	/**
	 * Sets X direction to the value passed from GamePanel
	 * 
	 * @param randomXDir - Determines the X direction
	 */
	public void setXDir(int randomXDir) {
		setxVelocity(randomXDir);
	}

	/**
	 * Sets Y direction to the value passed from GamePanel
	 * 
	 * @param randomYDir - Determines the Y direction
	 */
	public void setYDir(int randomYDir) {
		setyVelocity(randomYDir);
	}

	/**
	 * Moves the ball across the x and y axis.
	 */
	public void move() {
		x += getxVelocity();
		y += getyVelocity();
	}

	/**
	 * Draws the ball.
	 * 
	 * @param g - Graphics object used to create the tennis ball
	 */
	public void draw(Graphics g) {
		// Color is lime green
		g.setColor(new Color(223, 255, 79));
		g.fillOval(x, y, width, height);
	}

	/**
	 * Gets vertical velocity.
	 * 
	 * @return - yVelocity value
	 */
	public int getyVelocity() {
		return yVelocity;
	}

	/**
	 * Sets vertical velocity.
	 * 
	 * @param yVelocity - Value to be used for yVelocity
	 */
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	/**
	 * Gets horizontal velocity.
	 * 
	 * @return - xVelocity value
	 */
	public int getxVelocity() {
		return xVelocity;
	}

	/**
	 * Sets horizontal velocity.
	 * 
	 * @param xVelocity - Value to be used for xVelocity
	 */
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
}
