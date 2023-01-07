package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Paddle;
import main.GamePanel;

public class KeyboardInput implements KeyListener {
	private GamePanel panel;
	
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
			panel.getUser().setYDir(-10);
			panel.getUser().move();
			break;
		case KeyEvent.VK_S:
			panel.getUser().setYDir(10);
			panel.getUser().move();
			break;
		case KeyEvent.VK_UP:
			panel.getComp().setYDir(-10);
			panel.getComp().move();
			break;
		case KeyEvent.VK_DOWN:
			panel.getComp().setYDir(10);
			panel.getComp().move();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getUser().setYDir(0);
			panel.getUser().move();
			break;
		case KeyEvent.VK_S:
			panel.getUser().setYDir(0);
			panel.getUser().move();
			break;
		case KeyEvent.VK_UP:
			panel.getComp().setYDir(0);
			panel.getComp().move();
			break;
		case KeyEvent.VK_DOWN:
			panel.getComp().setYDir(0);
			panel.getComp().move();
			break;
		}
	}

}
