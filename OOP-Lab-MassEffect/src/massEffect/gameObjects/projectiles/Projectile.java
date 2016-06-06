package massEffect.gameObjects.projectiles;

import massEffect.interfaces.IProjectile;
import massEffect.interfaces.IStarship;

public abstract class Projectile implements IProjectile {

	private int damage;
	
	protected Projectile(int damage) {
		
		this.setDamage(damage);
	}
	
	@Override
	public int getDamage() {

		return this.damage;
	}

	@Override
	public void setDamage(int damage) {

		this.damage = damage;
	}

	public abstract void hit(IStarship ship);
}
