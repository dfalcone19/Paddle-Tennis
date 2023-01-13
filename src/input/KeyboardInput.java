package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Paddle;
import main.GamePanel;

/**
 * Gets keyboard input from the user.
 * 
 * @author Daniel Falcone
 *
 */
public class KeyboardInput implements KeyListener {
	/** GamePanel instance used to access the graphical elements. */
	private GamePanel panel;

	/**
	 * Constructor which accesses the GamePanel.
	 * 
	 * @param panel - Used to manipulate the graphical elements of the GamePanel
	 */
	public KeyboardInput(GamePanel panel) {
		this.panel = panel;
	}

	/**
	 * Unused keyTyped method
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Moves the paddles up or down based on which keys are pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// W and S control the paddle on the left
		// UP and DOWN control the paddle on the right
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getPaddle1().setYDir(-10);
			panel.getPaddle1().move();
			break;
		case KeyEvent.VK_S:
			panel.getPaddle1().setYDir(10);
			panel.getPaddle1().move();
			break;
		case KeyEvent.VK_UP:
			panel.getPaddle2().setYDir(-10);
			panel.getPaddle2().move();
			break;
		case KeyEvent.VK_DOWN:
			panel.getPaddle2().setYDir(10);
			panel.getPaddle2().move();
			break;
		}
	}

	/**
	 * Tells the paddles to stop moving when the key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getPaddle1().setYDir(0);
			panel.getPaddle1().move();
			break;
		case KeyEvent.VK_S:
			panel.getPaddle1().setYDir(0);
			panel.getPaddle1().move();
			break;
		case KeyEvent.VK_UP:
			panel.getPaddle2().setYDir(0);
			panel.getPaddle2().move();
			break;
		case KeyEvent.VK_DOWN:
			panel.getPaddle2().setYDir(0);
			panel.getPaddle2().move();
			break;
		}
	}

}
