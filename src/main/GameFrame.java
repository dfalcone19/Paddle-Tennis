package main;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private GamePanel panel;
	
	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Paddle Tennis");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
