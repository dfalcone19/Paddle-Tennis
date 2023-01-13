package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {
	private static int GAME_WIDTH;
	private static int GAME_HEIGHT;
	private int user;
	private int comp;
	

	public Score(int width, int height) {
		Score.GAME_WIDTH = width;
		Score.GAME_HEIGHT = height;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 62));
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
		// displays both the first and second digits of each score
		g.drawString(String.valueOf(user / 10) + String.valueOf(user % 10), (GAME_WIDTH / 2) - 85, 50);
		g.drawString(String.valueOf(comp / 10) + String.valueOf(comp % 10), (GAME_WIDTH / 2) + 20, 50);
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getComp() {
		return comp;
	}

	public void setComp(int comp) {
		this.comp = comp;
	}
}
