package massEffect.gameObjects.ships;

import massEffect.gameObjects.locations.StarSystem;
import massEffect.gameObjects.projectiles.PenetrationShell;
import massEffect.interfaces.IProjectile;

public class Cruiser extends Starship {

	public Cruiser(String name, int health, int shields, int damage, int fuel, StarSystem location) {
		
		super(name, health, shields, damage, fuel, location);
	}

	public Cruiser(String name, StarSystem location) {
		
		super(name, location);
	}
	
	@Override
	public IProjectile produceAttack() {
		
		return new PenetrationShell(this.getDamage());
	}

}
