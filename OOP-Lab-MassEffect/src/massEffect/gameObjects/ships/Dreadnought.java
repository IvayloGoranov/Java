package massEffect.gameObjects.ships;

import massEffect.gameObjects.locations.StarSystem;
import massEffect.gameObjects.projectiles.Laser;
import massEffect.interfaces.IProjectile;

public class Dreadnought extends Starship {

	public Dreadnought(String name, int health, int shields, int damage, int fuel, StarSystem location) {
		
		super(name, health, shields, damage, fuel, location);
	}

	public Dreadnought(String name, StarSystem location) {
		
		super(name, location);
	}
	
	@Override
	public IProjectile produceAttack() {

		return new Laser((this.getShields() / 2) + this.getDamage());
	}

	@Override
	public void respondToAttack(IProjectile attack) {
		
        this.setShields(this.getShields() + 50);
        super.respondToAttack(attack);
        this.setShields(this.getShields() - 50);
    }
}
