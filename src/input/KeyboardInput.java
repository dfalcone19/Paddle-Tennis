package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Paddle;
import main.GamePanel;

public class KeyboardInput implements KeyListener {
	private GamePanel panel;
	Paddle user;
	Paddle comp;
	
	public KeyboardInput(GamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getPaddle().setYDir(-16);
			panel.getPaddle().move();
			break;
		case KeyEvent.VK_S:
			panel.getPaddle().setYDir(16);
			panel.getPaddle().move();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getPaddle().setYDir(0);
			panel.getPaddle().move();
			break;
		case KeyEvent.VK_S:
			panel.getPaddle().setYDir(0);
			panel.getPaddle().move();
			break;
		}
	}

}
