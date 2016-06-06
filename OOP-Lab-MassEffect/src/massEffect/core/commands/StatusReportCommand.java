package massEffect.core.commands;

import java.util.Optional;

import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IStarship;

public class StatusReportCommand extends Command {

	public StatusReportCommand(IGameEngine gameEngine) {
		
		super(gameEngine);
	}

	@Override
	public void execute(String[] commandArgs) {
		
		String shipName = commandArgs[1];
        Optional<IStarship> ship = this.getGameEngine().getStarships().stream().
	    		filter(s -> s.getName().equals(shipName)).findFirst();
        if (!ship.isPresent()) {
			
	    	throw new IllegalArgumentException("Ship is not present in ship database");
		}
        
        System.out.println(ship.get().toString());
	}

}
