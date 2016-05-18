package facade;

public class Application {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		// Game loop
		while(true) {
			
			game.update();
		}
	}
}
