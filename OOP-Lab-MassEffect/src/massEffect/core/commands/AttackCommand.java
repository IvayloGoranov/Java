package massEffect.core.commands;

import java.util.Optional;

import massEffect.exceptions.ShipException;
import massEffect.interfaces.IGameEngine;
import massEffect.interfaces.IProjectile;
import massEffect.interfaces.IStarship;

public class AttackCommand extends Command {

	public AttackCommand(IGameEngine gameEngine){
		
		super(gameEngine);
    }

	@Override
	public void execute(String[] commandArgs) throws ShipException {
		
	    String attackerName = commandArgs[1];
	    String targetName = commandArgs[2];
	    Optional<IStarship> attackingShip = this.getGameEngine().getStarships().stream().
	    		filter(s -> s.getName().equals(attackerName)).findFirst();
	    Optional<IStarship> targetShip = this.getGameEngine().getStarships().stream().
	    		filter(s -> s.getName().equals(targetName)).findFirst();
	    if (!attackingShip.isPresent()) {
			
	    	throw new IllegalArgumentException("Attacking ship is not present in ship database");
		}
	    
	    if (!targetShip.isPresent()) {
			
	    	throw new IllegalArgumentException("Target ship is not present in ship database");
		}
	    
	    this.processStarshipbattle(attackingShip.get(), targetShip.get());
	}

	private void processStarshipbattle(IStarship attackingShip, IStarship targetShip) throws ShipException {
		
	    super.validateAlive(attackingShip);
	    super.validateAlive(targetShip);
	    
	    if (!attackingShip.getLocation().getName().equals(targetShip.getLocation().getName())) {
	    	
	        throw new ShipException("No such ship in star system");
	    }
	    
	    IProjectile attack = attackingShip.produceAttack();
	    targetShip.respondToAttack(attack);
	    System.out.printf("%s attacked %s", attackingShip.getName(), targetShip.getName());
	    if (targetShip.getShields() < 0) {
	    	
	        targetShip.setShields(0);
	    }
	    
	    if (targetShip.getHealth() < 0) {
	    	
	        targetShip.setHealth(0);
	        System.out.printf("%s has been destroyed", targetShip.getName());
	    }
	}
}
