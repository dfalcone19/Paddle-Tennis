package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
	private Random rand;
	private int xVelocity;
	private int yVelocity;
	int initialSpeed = 1;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		rand = new Random();
		int randomXDir = rand.nextInt(2);
		if (randomXDir == 0) {
			randomXDir--;
		}
		setXDir(randomXDir * initialSpeed);
		
		int randomYDir = rand.nextInt(2);
		if (randomYDir == 0) {
			randomYDir--;
		}
		setYDir(randomYDir * initialSpeed);
	}

	public void setXDir(int randomXDir) {
		setxVelocity(randomXDir);
	}

	public void setYDir(int randomYDir) {
		setyVelocity(randomYDir);
	}

	public void move() {
		x += getxVelocity();
		y += getyVelocity();
	}

	public void draw(Graphics g) {
		g.setColor(new Color(223,255,79));
		g.fillOval(x, y, width, height);
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
}
