package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import models.*;
import ui.InputHandler;

@SuppressWarnings("serial")
public class Engine extends Canvas implements Runnable {

	public static final int ROWS_MAX_LENGTH = 30;
	public static final int COLS_MAX_LENGTH = 30;
	public static final int GAME_SCREEN_WIDTH = COLS_MAX_LENGTH * Box.BOX_SIZE;
	public static final int GAME_SCREEN_HEIGHT = ROWS_MAX_LENGTH * Box.BOX_SIZE;
	
	private Thread runThread;
	private Graphics graphics;
	private boolean isRunning;
	@SuppressWarnings("unused")
	private InputHandler inputHandler;
	
	private Paddle paddle;
	private Ball ball;
	private BrickWall brickWall;
	
	public Engine() {
		
		this.paddle = new Paddle();
		this.ball = new Ball();
		this.brickWall = new BrickWall();
		this.inputHandler = new InputHandler(this);
	}
		
	public Paddle getPaddle() {
		
		return this.paddle;
	}

	@Override
	public void paint(Graphics graphics) {
		
		this.graphics = graphics.create();
		if (this.runThread ==  null) {
			
			this.runThread = new Thread(this);
			this.runThread.start();
			this.isRunning = true;
		}
	}

	@Override
	public void run() {
		
		while (isRunning) {

			this.brickWall.drawBrickWall(this.graphics);
			this.paddle.drawPaddle(this.graphics);
			this.ball.changeBallPositions();
			this.ball.drawBall(this.graphics);
			this.detectCollisionsWithPaddleOrScreenBoundaries();
			this.detectCollisionsWithBrickWall();
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	private void detectCollisionsWithBrickWall() {
		
		if (this.ball.getBallBox().getY() <= BrickWall.BRICK_WALL_HEIGHT + 4 &&
				this.ball.getBallBox().getY() >= BrickWall.BRICK_WALL_HEIGHT) {
			
			boolean collided = this.brickWall.detectCollisionWithBall(this.ball);
			if (collided) {
				
				this.ballDirectionAfterWallCollision();
			}
		}
	}
	
	private void ballDirectionAfterWallCollision()
    {
        if (this.ball.getBallDirection() == Directions.Up) {
            
        	// From upward direction the ball bounces off downward.
            this.ball.setBallDirection(Directions.Down);
        } else if (this.ball.getBallDirection() == Directions.Down) {
            
        	// From downward direction the ball bounces off upwards.
        	this.ball.setBallDirection(Directions.Up);
        } else if (this.ball.getBallDirection() == Directions.UpAndRight) {
            
        	// From upward right direction the ball bounces off downward right.
        	this.ball.setBallDirection(Directions.DownAndRight);
        } else if (this.ball.getBallDirection() == Directions.UpAndLeft) {
            
        	this.ball.setBallDirection(Directions.DownAndLeft);
        } else if (this.ball.getBallDirection() == Directions.DownAndLeft) {
            
        	// From downward left direction the ball bounces off upward left.
        	this.ball.setBallDirection(Directions.UpAndLeft);
        }
        else if (this.ball.getBallDirection() == Directions.DownAndRight) {
            
        	// From downward right direction the ball bounces off upward right.
        	this.ball.setBallDirection(Directions.UpAndRight);
        }
    }
	
	private void detectCollisionsWithPaddleOrScreenBoundaries() {

		if (this.ball.getBallBox().getY() == COLS_MAX_LENGTH - 2) { //Detects collision with the floor or paddle.
			
			if ((this.ball.getBallBox().getX() >= this.paddle.getPaddleLeftmostBoxPositionX() + 2) &&
                    (this.ball.getBallBox().getX() <= this.paddle.getPaddleLeftmostBoxPositionX() + 4)) { // The middle 3 paddle boxes.
                    
					this.ball.setBallDirection(Directions.Up); // Bouncing up.
                } else if ((this.ball.getBallBox().getX() >= this.paddle.getPaddleLeftmostBoxPositionX()) &&
                    (this.ball.getBallBox().getX() <= this.paddle.getPaddleLeftmostBoxPositionX() + 1)) { // The left 2 paddle boxes.

                	this.ball.setBallDirection(Directions.UpAndLeft); // Bouncing up and to the left.
                } else if ((this.ball.getBallBox().getX() >= this.paddle.getPaddleLeftmostBoxPositionX() + 5) &&
                    (this.ball.getBallBox().getX() <= this.paddle.getPaddleLeftmostBoxPositionX() + 6)) { // The right 2 paddle boxes.

                	this.ball.setBallDirection(Directions.UpAndRight); // Bouncing up and to the right.
                } else {
                    
                	System.exit(0); // Terminates the main thread, without any exception thrown, i.e. exits the program.
                }
		}
		
		if (this.ball.getBallBox().getY() == 0) { // When the ball hits the ceiling.
            if (this.ball.getBallDirection() == Directions.Up) {
                
            	this.ball.setBallDirection(Directions.Down); // From upward direction the ball bounces off downward.
            } else if (this.ball.getBallDirection() == Directions.UpAndRight) {

            	this.ball.setBallDirection(Directions.DownAndRight); // From upward right direction the ball bounces off downward right.
            } else if (this.ball.getBallDirection() == Directions.UpAndLeft) {
                
            	this.ball.setBallDirection(Directions.DownAndLeft); // From upward left direction the ball bounces off downward left.
            }
        }
		
		if (this.ball.getBallBox().getX() == 0) { // When the ball hits the left wall.
            if (this.ball.getBallDirection() == Directions.UpAndLeft) {
            	
            	this.ball.setBallDirection(Directions.UpAndRight); // From upward left direction the ball bounces off upward right.
            } else if (this.ball.getBallDirection() == Directions.DownAndLeft) {

            	this.ball.setBallDirection(Directions.DownAndRight); // From downward left direction the ball bounces off downward right.
            }
        }
		
		if (this.ball.getBallBox().getX() == COLS_MAX_LENGTH) { // When the ball hits the right wall.
            if (this.ball.getBallDirection() == Directions.UpAndRight) {

            	this.ball.setBallDirection(Directions.UpAndLeft); // From upward right direction the ball bounces off upward left.
            } else if (this.ball.getBallDirection() == Directions.DownAndRight) {

            	this.ball.setBallDirection(Directions.DownAndLeft); // From downward right direction the ball bounces off downward left.
            }
        }
	}
}
