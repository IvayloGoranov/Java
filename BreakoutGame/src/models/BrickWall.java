package models;

import java.awt.Color;
import java.awt.Graphics;

import engine.Engine;

public class BrickWall {
	
	public static final int BRICK_WALL_HEIGHT = 4;
	
	private Box[][] brickWall;
	
	public BrickWall() {
		
		this.brickWall = new Box[Engine.COLS_MAX_LENGTH][BRICK_WALL_HEIGHT];
	}
	
	public void drawBrickWall(Graphics graphics) {
		
		for (int row = 0; row < this.brickWall.length; row++) {
			
			for (int col = 0; col < this.brickWall[0].length; col++) {
				
				if (this.brickWall[row][col] == null) {
					
					this.brickWall[row][col] = new Box(row, col + BRICK_WALL_HEIGHT);
				}
				
				if (this.brickWall[row][col].isVisible()) {
					
					graphics.setColor(Color.orange);
					graphics.fillRect(this.brickWall[row][col].getX() * Box.BOX_SIZE, this.brickWall[row][col].getY() * Box.BOX_SIZE, 
							Box.BOX_SIZE, Box.BOX_SIZE);
					graphics.setColor(Color.black);
					graphics.drawRect(this.brickWall[row][col].getX() * Box.BOX_SIZE, this.brickWall[row][col].getY() * Box.BOX_SIZE, 
							Box.BOX_SIZE, Box.BOX_SIZE);
				} else {
				
					graphics.setColor(Color.lightGray);
					graphics.fillRect(this.brickWall[row][col].getX() * Box.BOX_SIZE, this.brickWall[row][col].getY() * Box.BOX_SIZE, 
							Box.BOX_SIZE, Box.BOX_SIZE);
					graphics.setColor(Color.lightGray);
					graphics.drawRect(this.brickWall[row][col].getX() * Box.BOX_SIZE, this.brickWall[row][col].getY() * Box.BOX_SIZE, 
							Box.BOX_SIZE, Box.BOX_SIZE);
				}
			}
		}
	}
	
	public boolean detectCollisionWithBall(Ball ball) {
		
		boolean collided = false;
		for (int row = 0; row < this.brickWall.length; row++) {
			
			for (int col = 0; col < this.brickWall[0].length; col++) {
				
				if (ball.getBallBox().getX() == this.brickWall[row][col].getX() && 
						ball.getBallBox().getY() - 1 == this.brickWall[row][col].getY() &&
						this.brickWall[row][col].isVisible()) {
					
					this.brickWall[row][col].setVisible(false);
					collided = true;
				}
			}
		}
		
		return collided;
	}
}
