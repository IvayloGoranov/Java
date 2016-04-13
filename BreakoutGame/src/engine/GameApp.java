package engine;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class GameApp extends Applet {
	
	private Engine engine;
	
	public void init() {
		
		this.engine = new Engine();
		this.engine.setBackground(Color.lightGray);
		this.engine.setPreferredSize(new Dimension(Engine.GAME_SCREEN_WIDTH, Engine.GAME_SCREEN_HEIGHT));
		this.engine.setVisible(true);
		this.engine.setFocusable(true);
		this.add(engine);
		this.setVisible(true);
	}
	
	public void paint(Graphics graphics){
	 
		this.setSize(new Dimension(800, 650));
	}
}
