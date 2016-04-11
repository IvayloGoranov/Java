package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;

public class InputHandler implements KeyListener {

	private Engine engine;
	
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
		if (keyCode == KeyEvent.VK_UP && this.engine.getSnake().getDirectionY() != 1) {
			
			this.engine.getSnake().setDirectionX(0);
			this.engine.getSnake().setDirectionY(-1);
		} else if (keyCode == KeyEvent.VK_DOWN && this.engine.getSnake().getDirectionY() != -1) {
			
			this.engine.getSnake().setDirectionX(0);
			this.engine.getSnake().setDirectionY(1);
		} else if (keyCode == KeyEvent.VK_RIGHT && this.engine.getSnake().getDirectionX() != -1) {
			
			this.engine.getSnake().setDirectionX(1);
			this.engine.getSnake().setDirectionY(0);
		}else if (keyCode == KeyEvent.VK_LEFT && this.engine.getSnake().getDirectionX() != 1) {
			
			this.engine.getSnake().setDirectionX(-1);
			this.engine.getSnake().setDirectionY(0);
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
