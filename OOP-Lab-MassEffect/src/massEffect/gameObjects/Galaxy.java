package massEffect.gameObjects;

import java.util.HashSet;

import massEffect.exceptions.InsufficientFuelException;
import massEffect.exceptions.LocationOutOfRangeException;
import massEffect.gameObjects.locations.StarSystem;
import massEffect.interfaces.IStarship;

public class Galaxy {

	private HashSet<StarSystem> starSystems;

    public Galaxy() {
    	
        this.starSystems = new HashSet<StarSystem>();
    }

    public HashSet<StarSystem> getStarSystems() {
		
    	return this.starSystems;
	}
    
    public StarSystem getStarSystemByName(String name) {
    	
        return this.starSystems.stream().
        		filter(s -> s.getName().equals(name)).findFirst().get();
    }

	public void travelTo(IStarship ship, StarSystem destination) 
			throws LocationOutOfRangeException, InsufficientFuelException {
		
        StarSystem startLocation = ship.getLocation();
        if (!startLocation.getNeighbourStarSystems().containsKey(destination)) {
        	
            throw new LocationOutOfRangeException(String.format(
                "Cannot travel directly from %s to %s",
                startLocation.getName(), destination.getName()));
        }

        double requiredFuel = startLocation.getNeighbourStarSystems().get(destination).doubleValue();
        if (ship.getFuel() < requiredFuel) {
        	
            throw new InsufficientFuelException(String.format(
                "Not enough fuel to travel to %s - %.2f/%.2f", 
                destination.getName(), ship.getFuel(), requiredFuel));
        }

        ship.setFuel(ship.getFuel() - requiredFuel);
        ship.setLocation(destination);
    }
}
