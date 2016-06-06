package massEffect.core.commands;

import java.util.Optional;

import massEffect.exceptions.ShipException;
import massEffect.gameObjects.locations.StarSystem;
import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IStarship;

public class PlotJumpCommand extends Command {

	public PlotJumpCommand(IGameEngine gameEngine) {
		
		super(gameEngine);
	}

	@Override
	public void execute(String[] commandArgs) throws ShipException {
		
		String shipName = commandArgs[1];
        String destinationName = commandArgs[2];
        Optional<IStarship> ship = this.getGameEngine().getStarships().stream().
	    		filter(s -> s.getName().equals(shipName)).findFirst();
        if (!ship.isPresent()) {
			
	    	throw new IllegalArgumentException("Ship is not present in ship database");
		}

        super.validateAlive(ship.get());
        StarSystem previousLocation = ship.get().getLocation();
        StarSystem destination = this.getGameEngine().getGalaxy().getStarSystems().stream().
	    		filter(s -> s.getName().equals(destinationName)).findFirst().get();
        if (previousLocation.getName() == destinationName) {
        	
            throw new ShipException(String.format("Ship is already in %s", destinationName));
        }
        
        this.getGameEngine().getGalaxy().travelTo(ship.get(), destination);
        System.out.printf("%s jumped from %s to %s", shipName, previousLocation.getName(), destinationName);
	}
}
