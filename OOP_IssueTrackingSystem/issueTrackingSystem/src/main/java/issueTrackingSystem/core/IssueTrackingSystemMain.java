package issueTrackingSystem.core;

import issueTrackingSystem.interfaces.IEngine;
import issueTrackingSystem.interfaces.IInputHandler;
import issueTrackingSystem.interfaces.IRenderer;
import issueTrackingSystem.ui.ConsoleRenderer;
import issueTrackingSystem.ui.InputHandler;

public class IssueTrackingSystemMain {

	public static void main(String[] args) {
		
		IRenderer renderer = new ConsoleRenderer();
        IInputHandler inputHandler = new InputHandler();
        IEngine engine = new Engine(renderer, inputHandler);
        engine.run();
	}
}
