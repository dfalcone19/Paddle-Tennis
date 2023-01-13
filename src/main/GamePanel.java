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
 * Class to define the JPanel and run the majority of the code
 * 
 * @author Daniel Falcone
 *
 */
public class GamePanel extends JPanel implements Runnable {
	/** Integer to define width of the panel */
	private static final int GAME_WIDTH = 1000;
	/** Integer to define the height of the panel 
	 * 0.5555 is the ratio of the width to length of a standard ping pong table (5/9) 
	 * */
	private static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
	/** Dimension variable to store the dimensions of the JPanel */
	private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	/** Integer to store the diameter of the ball*/
	private static final int BALL_DIAMETER = 20;
	/** Integers to store the width of the paddles */
	private static final int PADDLE_WIDTH = 25;
	/** Integers to store the height of the paddles */
	private static final int PADDLE_HEIGHT = 100;
	/** Declare thread to be used by this game */
	private Thread gameThread;
	/** Declare new image to store the */
	private Image image;
	private Graphics graphics;
	private Random rand;
	private Paddle user;
	private Paddle comp;
	private Ball ball;
	private Score score;
	private Main main;

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

	public void createBall() {
		rand = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), rand.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	public void createPaddle() {
		user = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		comp = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		user.draw(g);
		comp.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	public void move() {
		user.move();
		comp.move();
		ball.move();
	}

	public void detectCollision() {

		// bounce ball of top and bottom of window
		if (ball.y <= 0) {
			ball.setYDir(-ball.getyVelocity());
		}

		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDir(-ball.getyVelocity());
		}

		// bounces ball of user paddle
		if (ball.intersects(user)) {
			ball.setxVelocity(Math.abs(ball.getxVelocity()));
			ball.setxVelocity(ball.getxVelocity() + 1);

			if (ball.getyVelocity() > 0) {
				ball.setyVelocity(ball.getyVelocity() + 1);
			} else {
				ball.setyVelocity(ball.getyVelocity() - 1);
			}

			ball.setXDir(ball.getxVelocity());
			ball.setYDir(ball.getyVelocity());
		}

		// bounces ball of computer paddle
		if (ball.intersects(comp)) {
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
		if (user.y <= 0) {
			user.y = 0;
		}

		if (user.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			user.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}

		if (comp.y <= 0) {
			comp.y = 0;
		}

		if (comp.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			comp.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}

		// give a player 1 point and creates new paddles & ball
		if (ball.x <= 0) {
			score.setComp(score.getComp() + 1);
			createPaddle();
			createBall();
			System.out.println("Computer Score: " + score.getComp());
		}

		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.setUser(score.getUser() + 1);
			createPaddle();
			createBall();
			System.out.println("User Score: " + score.getUser());
		}
	}

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

	public Paddle getUser() {
		return user;
	}

	public Paddle getComp() {
		return comp;
	}

}
