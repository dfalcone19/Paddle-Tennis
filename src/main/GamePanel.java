package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

import entities.Ball;
import entities.Paddle;
import input.KeyboardInput;

public class GamePanel extends JPanel implements Runnable {
	private static final int GAME_WIDTH = 1000;
	private static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
	private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	private static final int BALL_DIAMETER = 20;
	private static final int PADDLE_WIDTH = 25;
	private static final int PADDLE_HEIGHT = 100;
	private Thread gameThread;
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

	}

	public void createPaddle() {
		user = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		comp = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
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
	}

	public void move() {
		user.move();
		comp.move();
	}

	public void detectCollision() {
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
