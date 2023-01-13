package main;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * A class to define the JFrame for the game
 * 
 * @author Daniel Falcone
 *
 */
public class GameFrame extends JFrame {
	/** New GamePanel instance */
	private GamePanel panel;
	
	/**
	 * Constructor that initializes the JPanel and defines the JFrame
	 */
	public GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Paddle Tennis");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// resize the frame to fit the contents of the JPanel
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(108, 147, 92));
	}
}
