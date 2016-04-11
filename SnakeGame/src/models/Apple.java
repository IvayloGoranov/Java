package models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import engine.Engine;

public class Apple {
	
	private static final Random randomizer = new Random();
	
	private Box appleBox;
	
	public Apple(Snake snake) {
		
		this.appleBox = this.createApple(snake);
	}
	
	public Box getAppleBox() {
		
		return this.appleBox;
	}
	
	public void drawApple(Graphics graphics){
		
		graphics.setColor(Color.red);
		graphics.fillOval(this.appleBox.getX() * Box.BOX_SIZE, this.appleBox.getY() * Box.BOX_SIZE, 
				Box.BOX_SIZE, Box.BOX_SIZE);
	}
	
	private Box createApple(Snake snake) {
		
		int x = randomizer.nextInt(Engine.COLS_MAX_LENGTH);
		int y = randomizer.nextInt(Engine.ROWS_MAX_LENGTH);
		
		Box box = new Box(x, y);
		if (snake.getSnakeBody().contains(box)) {
			
			return this.createApple(snake);
		}
		
		return box;
	}
}
