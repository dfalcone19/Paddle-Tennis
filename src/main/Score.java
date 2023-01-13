package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Keeps track of the score of the game and creates the score displayed on
 * screen.
 * 
 * @author Daniel Falcone
 *
 */
public class Score extends Rectangle {
	/** Constant for the width of the screen. */
	private static int GAME_WIDTH;
	/** Constant for the height of the screen. */
	private static int GAME_HEIGHT;
	/** Score for the paddle on the left. */
	private int paddle1;
	/** Score for the paddle on the right. */
	private int paddle2;

	/**
	 * Constructor which sets the width and height of the GamePanel.
	 * 
	 * @param width  - Width of the JPanel
	 * @param height - Height of the JPanel
	 */
	public Score(int width, int height) {
		Score.GAME_WIDTH = width;
		Score.GAME_HEIGHT = height;
	}

	/**
	 * Draws the score text.
	 * 
	 * @param g - Used to put the text onto the JPanel
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 62));
		// draw a line in the middle of the screen
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
		// displays the scores separated by their first and second digit values
		g.drawString(String.valueOf(paddle1 / 10) + String.valueOf(paddle1 % 10), (GAME_WIDTH / 2) - 85, 50);
		g.drawString(String.valueOf(paddle2 / 10) + String.valueOf(paddle2 % 10), (GAME_WIDTH / 2) + 20, 50);
	}

	/**
	 * Gets the score for the paddle on the left.
	 * 
	 * @return - Integer value of paddle1's score
	 */
	public int getPaddle1() {
		return paddle1;
	}

	/**
	 * Sets the score for the paddle on the left.
	 * 
	 * @param paddle1 - Value of paddle1's score
	 */
	public void setPaddle1(int paddle1) {
		this.paddle1 = paddle1;
	}

	/**
	 * Gets the score for the paddle on the right.
	 * 
	 * @return - Integer value of paddle2's score
	 */
	public int getPaddle2() {
		return paddle2;
	}

	/**
	 * Sets the score for the paddle on the right.
	 * 
	 * @param paddle2 - Value of paddle2's score
	 */
	public void setPaddle2(int paddle2) {
		this.paddle2 = paddle2;
	}
}
