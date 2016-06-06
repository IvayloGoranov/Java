package massEffect.core.factories;

import massEffect.gameObjects.locations.StarSystem;
import massEffect.gameObjects.ships.Cruiser;
import massEffect.gameObjects.ships.Dreadnought;
import massEffect.gameObjects.ships.Frigate;
import massEffect.gameObjects.ships.StarshipType;
import massEffect.interfaces.IStarship;

public class ShipFactory {

	public IStarship createShip(StarshipType type, String name, StarSystem location) {
		
        switch (type) {
        
            case Frigate:
                return new Frigate(name, location);
            case Cruiser:
                return new Cruiser(name, location);
            case Dreadnought:
                return new Dreadnought(name, location);
            default:
                throw new UnsupportedOperationException("Starship type not supported.");
        }
    }
}
