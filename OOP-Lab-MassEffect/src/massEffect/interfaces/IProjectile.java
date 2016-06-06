package massEffect.interfaces;

public interface IProjectile {

	int getDamage();
	
	void setDamage(int damage);

    void hit(IStarship ship);
}
