package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;

public class InputHandler implements KeyListener {

private Engine engine;
	
	private final int PADDLE_RIGHMOST_POSITION = 22;
	private final int PADDLE_LEFTMOST_POSITION = 1;
	
	
	public InputHandler(Engine engine) {
		
		this.engine = engine;
		this.engine.addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		int paddleOldPosition = this.engine.getPaddle().getPaddleLeftmostBoxPositionX();
		if (keyCode == KeyEvent.VK_LEFT && paddleOldPosition >= PADDLE_LEFTMOST_POSITION) {
			
			this.engine.getPaddle().setPaddleLeftmostBoxPositionX(paddleOldPosition - 1);
		} else if (keyCode == KeyEvent.VK_RIGHT && paddleOldPosition <= PADDLE_RIGHMOST_POSITION) {
			
			this.engine.getPaddle().setPaddleLeftmostBoxPositionX(paddleOldPosition + 1);
		}
		
		if (keyCode == KeyEvent.VK_ESCAPE) {
			
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
