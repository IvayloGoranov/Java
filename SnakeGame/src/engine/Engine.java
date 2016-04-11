package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import UI.InputHandler;
import models.*;

@SuppressWarnings("serial")
public class Engine extends Canvas implements Runnable {

	public static final int ROWS_MAX_LENGTH = 30;
	public static final int COLS_MAX_LENGTH = 30;
	public static final int GAME_SCREEN_WIDTH = COLS_MAX_LENGTH * Box.BOX_SIZE;
	public static final int GAME_SCREEN_HEIGHT = ROWS_MAX_LENGTH * Box.BOX_SIZE;
	
	private final int INITIAL_GAME_SPEED = 100;
	
	private Snake snake;
	private Apple apple;
	private int score;
	private int gameSpeed;
	
	private Thread runThread;
	private Graphics graphics;
	private boolean isRunning;
	private InputHandler inputHandler;
	
	public Engine() {
		
		this.snake = new Snake();
		this.apple = new Apple(this.snake);
		this.gameSpeed = INITIAL_GAME_SPEED; 
		this.inputHandler = new InputHandler(this);
	}
		
	public Snake getSnake() {
		return snake;
	}

	public void render() {
		
		this.graphics.clearRect(0, 0, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		this.snake.draw(this.graphics);
		this.apple.drawApple(this.graphics);
		this.drawScore();
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

			this.snake.tick();
			
			boolean outOfBounds = this.snake.getSnakeHead().getX() == COLS_MAX_LENGTH - 1 ||
					this.snake.getSnakeHead().getY() == ROWS_MAX_LENGTH - 1 ||
							this.snake.getSnakeHead().getY() == 0 ||
							this.snake.getSnakeHead().getX() == 0;
			
			if (this.snake.checkIsDead()) {
				
				this.isRunning = false;
			} else if (outOfBounds) {
				
				this.isRunning = false;
			} else if (this.snake.getSnakeHead().equals(this.apple.getAppleBox())) {
				
				this.snake.eatApple(this.apple);
				this.apple = new Apple(this.snake);
				this.score = this.score + 10;
				if (this.score != 0 && this.score % 50 == 0) { //Increase speed at each five eaten apples.
					
					this.gameSpeed = this.gameSpeed - 10;
				}
			}
			
			this.render();
			try {
				
				Thread.sleep(this.gameSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
	
	public void drawScore() {
		
		this.graphics.setColor(Color.gray);
		this.graphics.fillRect(0, GAME_SCREEN_HEIGHT, GAME_SCREEN_WIDTH, 25);
		this.graphics.setColor(Color.white);
		this.graphics.drawString("Score = " + this.score, 10, 10);
	}
}
