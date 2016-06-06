package massEffect.gameObjects.projectiles;

import massEffect.interfaces.IStarship;

public class ShieldReaver extends Projectile {

	public ShieldReaver(int damage) {
		
		super(damage);
	}

	@Override
	public void hit(IStarship ship) {
		
		ship.setHealth(ship.getHealth() - this.getDamage());
        ship.setShields(ship.getShields() - 2 * this.getDamage());
	}
}
