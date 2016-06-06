package massEffect.gameObjects.projectiles;

import massEffect.interfaces.IStarship;

public class PenetrationShell extends Projectile {

	public PenetrationShell(int damage) {
		
		super(damage);
	}

	@Override
	public void hit(IStarship ship) {
		
		ship.setHealth(ship.getHealth() - this.getDamage());
	}
}
