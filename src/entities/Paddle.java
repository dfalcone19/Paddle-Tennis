package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends Rectangle {
	private int playerNum;
	private int yVelocity;

	public Paddle(int x, int y, int width, int height, int playerNum) {
		super(x, y, width, height);
		this.playerNum = playerNum;
	}

	public void setYDir(int yDir) {
		yVelocity = yDir;
	}

	public void move() {
		y = y + yVelocity;
	}

	public void draw(Graphics g) {
		if (playerNum == 1) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.MAGENTA);
		}
		
		g.fillRect(x, y, width, height);
	}

}
