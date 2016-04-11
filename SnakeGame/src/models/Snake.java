package models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Snake {
	
	private LinkedList<Box> snakeBody;
	private Box snakeHead;
	private boolean isDead;
	
	private int directionX;
	private int directionY;
	
	public Snake() {

		this.snakeBody = new LinkedList<Box>();
		Collections.addAll(snakeBody, new Box(1, 2), new Box(2, 2), new Box(3, 2), new Box(4, 2));
		this.snakeHead = this.snakeBody.peekLast();
		this.isDead = false;
		this.directionX = 1;
		this.directionY = 0;
	}
	
	public Collection<Box> getSnakeBody() {
		
		return this.snakeBody;
	}
	
	public Box getSnakeHead() {
		
		return this.snakeHead;
	}
	
	public boolean checkIsDead() {
		
		return this.isDead;
	}
	
	public int getDirectionX() {
		
		return this.directionX;
	}

	public void setDirectionX(int directionX) {
		
		this.directionX = directionX;
	}

	public int getDirectionY() {
		
		return this.directionY;
	}

	public void setDirectionY(int directionY) {
		
		this.directionY = directionY;
	}
	
	public void draw(Graphics graphics) {
		
		for (Box box : snakeBody) {
			
			graphics.setColor(Color.green);
			graphics.fillRect(box.getX() * Box.BOX_SIZE, box.getY() * Box.BOX_SIZE, Box.BOX_SIZE, Box.BOX_SIZE);
			graphics.setColor(Color.black);
			graphics.drawRect(box.getX() * Box.BOX_SIZE, box.getY() * Box.BOX_SIZE, Box.BOX_SIZE, Box.BOX_SIZE);
		}
	}
	
	public void tick() {
		
		Box nextPosition = new Box(this.snakeHead.getX() + this.getDirectionX(), 
				this.snakeHead.getY() + this.getDirectionY());
		if (this.snakeBody.contains(nextPosition)) {
			
			this.isDead = true;
			return;
		}
		
		for (int i = 0; i < this.snakeBody.size() - 1; i++) {
			
			this.snakeBody.set(i, this.snakeBody.get(i + 1));
		}
		
		this.snakeBody.set(this.snakeBody.size() - 1, nextPosition);
		this.snakeHead = nextPosition;
	}
	
	public void eatApple(Apple apple) {
		
		this.snakeBody.add(apple.getAppleBox());
		this.snakeHead = this.snakeBody.peekLast();
	}
}
