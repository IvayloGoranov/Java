package issueTrackingSystem.ui;

import java.util.Scanner;

import issueTrackingSystem.interfaces.IInputHandler;

public class InputHandler implements IInputHandler {

	@Override
	public String nextLine() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		return scanner.nextLine();
	}

}
