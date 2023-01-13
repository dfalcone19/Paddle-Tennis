package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.JPanel;

import entities.Ball;
import entities.Paddle;
import input.KeyboardInput;

/**
 * Class to define the JPanel and run the majority of the code.
 * 
 * @author Daniel Falcone
 *
 */
public class GamePanel extends JPanel implements Runnable {
	/** Integer to define width of the panel. */
	private static final int GAME_WIDTH = 1000;
	/**
	 * Integer to define the height of the panel. 0.5555 is the ratio of the width
	 * to length of a standard ping pong table (5/9).
	 * 
	 */
	private static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
	/** Add dimensions to the JPanel. */
	private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	/** Diameter of the ball in pixels. */
	private static final int BALL_DIAMETER = 20;
	/** Width of the paddles in pixels. */
	private static final int PADDLE_WIDTH = 25;
	/** Height of the paddles in pixels. */
	private static final int PADDLE_HEIGHT = 100;
	/** Thread to be used by this game. */
	private Thread gameThread;
	/** Image object to be used by the graphics. */
	private Image image;
	/** Graphics object used to draw elements to the JPanel. */
	private Graphics graphics;
	/** Random used for spawning the ball. */
	private Random rand;
	/** Paddle on the left side of the screen. */
	private Paddle paddle1;
	/** Paddle on the right side of the screen. */
	private Paddle paddle2;
	/** Ball to be used. */
	private Ball ball;
	/** Score to keep track of the game. */
	private Score score;

	/**
	 * GamePanel constructor which creates the ball, paddles, score, key listener,
	 * and the thread for the game.
	 */
	public GamePanel() {
		createPaddle();
		createBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		addKeyListener(new KeyboardInput(this));
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Creates the ball and spawns it at a random Y value along the middle of the
	 * screen.
	 */
	public void createBall() {
		rand = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), rand.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	/**
	 * Creates the paddles at opposite ends of the screen.
	 */
	public void createPaddle() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	/**
	 * Paint method used to display the graphics onto the screen.
	 */
	public void paint(Graphics g) {
		// creates an image of width and height of the screen
		image = createImage(getWidth(), getHeight());
		// creates a graphic
		graphics = image.getGraphics();
		// draws all components
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	/**
	 * Draws the paddles, ball, and score.
	 * 
	 * @param g - Graphics object used to draw
	 */
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	/**
	 * Calls move methods for the paddles and the ball
	 */
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	/**
	 * Detects collision between the paddles and the screen edges, and the paddles
	 * and the ball.
	 */
	public void detectCollision() {

		// bounce ball of top and bottom of window and then reverse its Y direction
		if (ball.y <= 0) {
			ball.setYDir(-ball.getyVelocity());
		}

		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDir(-ball.getyVelocity());
		}

		// bounces ball off of paddle1
		if (ball.intersects(paddle1)) {
			// initial value is 0
			ball.setxVelocity(Math.abs(ball.getxVelocity()));

			ball.setxVelocity(ball.getxVelocity() + 1);

			// increase the ball velocity by 1 if it has hit a paddle
			if (ball.getyVelocity() > 0) {
				ball.setyVelocity(ball.getyVelocity() + 1);
			} else {
				ball.setyVelocity(ball.getyVelocity() - 1);
			}

			ball.setXDir(ball.getxVelocity());
			ball.setYDir(ball.getyVelocity());
		}

		// bounces ball of paddle2uter paddle
		if (ball.intersects(paddle2)) {
			ball.setxVelocity(Math.abs(ball.getxVelocity()));
			ball.setxVelocity(ball.getxVelocity() + 1);

			if (ball.getyVelocity() > 0) {
				ball.setyVelocity(ball.getyVelocity() + 1);
			} else {
				ball.setyVelocity(ball.getyVelocity() - 1);
			}

			ball.setXDir(-ball.getxVelocity());
			ball.setYDir(ball.getyVelocity());
		}

		// stops paddles at top and bottom of window
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}

		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}

		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}

		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}

		// give a player 1 point and creates new paddles & ball
		if (ball.x <= 0) {
			score.setPaddle2(score.getPaddle2() + 1);
			createPaddle();
			createBall();
		}

		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.setPaddle1(score.getPaddle1() + 1);
			createPaddle();
			createBall();
		}
	}

	/**
	 * Method from runnable that executes the game loop.
	 */
	public void run() {
		// game loop
		double timePerFrame = 1000000000.0 / 120;
		long previousTime = System.nanoTime();
		int frames = 0;
		double deltaF = 0;
		long timeSinceLastCheck = System.currentTimeMillis();

		while (true) {
			long currentTime = System.nanoTime();

			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaF >= 1) {
				move();
				detectCollision();
				this.repaint();
				deltaF--;
				frames++;
			}

			if (System.currentTimeMillis() - timeSinceLastCheck >= 1000) {
				timeSinceLastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
	}

	public Paddle getPaddle1() {
		return paddle1;
	}

	public Paddle getPaddle2() {
		return paddle2;
	}

}
