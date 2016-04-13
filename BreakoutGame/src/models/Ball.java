package models;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private Box ballBox;
	private Box previousBallBox; //Holds the previous ball position.
	
	private int positionX = 14; //Initial ball coordinates.
	private int positionY = 28;
	private Directions ballDirection = Directions.Up;
	
	public Ball() {
		
		this.ballBox = new Box(positionX, positionY);
		this.previousBallBox = this.ballBox;
	}
	
	public Directions getBallDirection() {
		
		return this.ballDirection;
	}

	public void setBallDirection(Directions ballDirection) {
		
		this.ballDirection = ballDirection;
	}

	public Box getBallBox() {
		
		return this.ballBox;
	}

	public void drawBall(Graphics graphics){
		
		graphics.setColor(Color.lightGray);
		graphics.fillOval(this.previousBallBox.getX() * Box.BOX_SIZE, this.previousBallBox.getY() * Box.BOX_SIZE, 
				Box.BOX_SIZE, Box.BOX_SIZE);
		
		graphics.setColor(Color.red);
		graphics.fillOval(this.ballBox.getX() * Box.BOX_SIZE, this.ballBox.getY() * Box.BOX_SIZE, 
				Box.BOX_SIZE, Box.BOX_SIZE);
	}
	
	public void changeBallPositions() {
		
		int newDirectionX = this.ballBox.getX();
		int newDirectionY = this.ballBox.getY();
		switch (ballDirection)
        {
            case Up:
                newDirectionY--;
                break;
            case UpAndLeft:
            	newDirectionX--;
            	newDirectionY--;
                break;
            case UpAndRight:
            	newDirectionX++;
            	newDirectionY--;
                break;
            case Down:
            	newDirectionY++;
                break;
            case DownAndRight:
            	newDirectionX++;
            	newDirectionY++;
                break;
            case DownAndLeft:
            	newDirectionX--;
            	newDirectionY++;
                break;
        }
		
		this.positionX = newDirectionX;
		this.positionY = newDirectionY;
		this.previousBallBox = this.ballBox;
		this.ballBox = new Box(this.positionX, this.positionY);
	}
}
