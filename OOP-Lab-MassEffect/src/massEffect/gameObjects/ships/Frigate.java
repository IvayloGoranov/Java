package massEffect.gameObjects.ships;

import massEffect.gameObjects.locations.StarSystem;
import massEffect.gameObjects.projectiles.ShieldReaver;
import massEffect.interfaces.IProjectile;

public class Frigate extends Starship {

	private int projectilesFired; 
	
	public Frigate(String name, int health, int shields, int damage, int fuel, StarSystem location) {
		
		super(name, health, shields, damage, fuel, location);
	}

	public Frigate(String name, StarSystem location) {
		
		super(name, location);
	}
	
	@Override
	public IProjectile produceAttack() {

		this.projectilesFired++;
        
		return new ShieldReaver(this.getDamage());
	}

	@Override
	public String toString() {
		
		StringBuilder output = new StringBuilder();
        if (this.getHealth() > 0) {
        	
            output.append(super.toString());
            output.append(String.format("-Projectiles fired:  %d\n", this.projectilesFired));
        }
        
        return output.toString();
	}

	
}
