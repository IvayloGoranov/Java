package massEffect.core.commands;

import massEffect.interfaces.IGameEngine;

public class OverCommand extends Command {

	public OverCommand(IGameEngine gameEngine) {
		
		super(gameEngine);
	}

	@Override
	public void execute(String[] commandArgs) {

		this.getGameEngine().setIsRunning(false);
	}
}
