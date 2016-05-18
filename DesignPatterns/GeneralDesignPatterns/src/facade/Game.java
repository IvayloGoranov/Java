package facade;

/*
 * Facade class!!
 */

public class Game {
	
	private InputSystem input = new InputSystem();
	private GameObject objects = new GameObject();
	private GameConsole screen = new GameConsole();

	public void update() {
		// Input
		input.getInput();

		// Update game objects (player, bad guys, etc)
		objects.update(input);

		// Draw
		screen.draw(objects);
	}
}
