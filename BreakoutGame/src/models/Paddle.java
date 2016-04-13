package models;

import java.awt.Color;
import java.awt.Graphics;

import engine.Engine;

public class Paddle {
	
	private final int PADDLE_LENGTH = 7;	
	
	private Box[] body;
	private int paddleLeftmostBoxPositionX = 11;
	
	public Paddle() {
		
		this.body = new Box[PADDLE_LENGTH];
		this.assignPaddleBoxValues(); 
	}
	
	public int getPaddleLeftmostBoxPositionX() {
		
		return this.paddleLeftmostBoxPositionX;
	}

	public void setPaddleLeftmostBoxPositionX(int paddlePositionX) {
		
		this.paddleLeftmostBoxPositionX = paddlePositionX;
	}
	
	public void drawPaddle(Graphics graphics) {
		
		for (Box box : this.body) { //Clear the screen with the previous position of the paddle.
			
			graphics.setColor(Color.lightGray);
			graphics.fillRect(box.getX() * Box.BOX_SIZE, box.getY() * Box.BOX_SIZE, Box.BOX_SIZE, Box.BOX_SIZE);
		}
		
		this.assignPaddleBoxValues();
		for (Box box : this.body) { //Redraws the paddle with the new position.
			
			graphics.setColor(Color.green);
			graphics.fillRect(box.getX() * Box.BOX_SIZE, box.getY() * Box.BOX_SIZE, Box.BOX_SIZE, Box.BOX_SIZE);
		}
	}

	private void assignPaddleBoxValues() {

		for (int i = 0; i < PADDLE_LENGTH; i++) {
			
			this.body[i] = new Box(paddleLeftmostBoxPositionX + i, Engine.COLS_MAX_LENGTH - 1);
		}
	}
}
