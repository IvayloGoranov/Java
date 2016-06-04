package issueTrackingSystem.ui;

import issueTrackingSystem.interfaces.IRenderer;

public class ConsoleRenderer implements IRenderer {

	@Override
	public void println(String message) {

		System.out.println(message);
	}

}
