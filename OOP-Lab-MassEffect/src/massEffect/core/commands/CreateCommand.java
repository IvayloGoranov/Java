package massEffect.core.commands;

import massEffect.exceptions.ShipException;
import massEffect.gameObjects.enhancements.Enhancement;
import massEffect.gameObjects.enhancements.EnhancementType;
import massEffect.gameObjects.locations.StarSystem;
import massEffect.gameObjects.ships.StarshipType;
import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IStarship;

public class CreateCommand extends Command {

	public CreateCommand(IGameEngine gameEngine) {
		
		super(gameEngine);
	}
	
	@Override
	public void execute(String[] commandArgs) throws ShipException {
		
	    String type = commandArgs[1];
	    String shipName = commandArgs[2];
	    String locationName = commandArgs[3];
	    boolean shipAlreadyExists = this.getGameEngine().getStarships().stream().
	    		filter(s -> s.getName().equals(shipName)).findFirst().isPresent();
	    if (shipAlreadyExists)
	    {
	        throw new ShipException("Ship with such name already exists");
	    }
	    
	    StarSystem location = this.getGameEngine().getGalaxy().getStarSystemByName(locationName);
	    StarshipType shipType = StarshipType.valueOf(type);
	    IStarship ship = this.getGameEngine().getShipFactory().createShip(shipType, shipName, location);
	    this.getGameEngine().getStarships().add(ship);
	    for (int i = 4; i < commandArgs.length; i++) {
	    	
	    	EnhancementType enhancementType = EnhancementType.valueOf(commandArgs[i]);
	        Enhancement enhancement = this.getGameEngine().getEnhancementFactory().create(enhancementType);
	        ship.addEnhancement(enhancement);
	    }
	    
	    System.out.printf("Created %s %s", shipType.toString(), shipName);
	}
}
