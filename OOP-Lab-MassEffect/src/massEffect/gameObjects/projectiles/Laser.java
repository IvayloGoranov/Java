package massEffect.gameObjects.projectiles;

import massEffect.interfaces.IStarship;

public class Laser extends Projectile {

	public Laser(int damage) {
		
		super(damage);
	}

	@Override
	public void hit(IStarship ship) {

		int actualDamage = 0;
        ship.setShields(ship.getShields() - this.getDamage());
        if (ship.getShields() < 0) {
        	
            actualDamage = Math.abs(ship.getShields());
        }
        
        ship.setHealth(ship.getHealth() - actualDamage);
	}
}
