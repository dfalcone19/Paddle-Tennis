package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents the paddles used in the game.
 * 
 * @author Daniel Falcone
 *
 */
public class Paddle extends Rectangle {
	/** Determines which paddle is being requested. */
	private int playerNum;
	/** Represents the vertical velocity. */
	private int yVelocity;

	/**
	 * Constructor for the paddles.
	 * 
	 * @param x         - X location
	 * @param y         - Y location
	 * @param width     - Width of paddles
	 * @param height    - Height of paddles
	 * @param playerNum - Which paddle is being used
	 */
	public Paddle(int x, int y, int width, int height, int playerNum) {
		super(x, y, width, height);
		this.playerNum = playerNum;
	}

	/**
	 * Sets the vertical direction.
	 * 
	 * @param yDir - yDir value
	 */
	public void setYDir(int yDir) {
		yVelocity = yDir;
	}

	/**
	 * Moves the paddle along the vertical axis.
	 */
	public void move() {
		y += yVelocity;
	}

	/**
	 * Draws the paddle.
	 * 
	 * @param g - Graphics object used to draw the rectangles
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
