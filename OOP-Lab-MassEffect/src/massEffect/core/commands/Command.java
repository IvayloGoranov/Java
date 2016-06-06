package massEffect.core.commands;

import massEffect.exceptions.ShipException;
import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IStarship;

public abstract class Command {
	
    private IGameEngine gameEngine;
	
	protected Command(IGameEngine gameEngine) {
    	
        this.setGameEngine(gameEngine);
    }

	public IGameEngine getGameEngine() {
		
		return this.gameEngine;
	}

	public void setGameEngine(IGameEngine gameEngine) {
		
		this.gameEngine = gameEngine;
	}

	protected void validateAlive(IStarship ship) throws ShipException {
		
        if (ship.getHealth() <= 0) {
        	
            throw new ShipException("Ship is destroyed");
        }
    }

    public abstract void execute(String[] commandArgs) throws ShipException;
}
