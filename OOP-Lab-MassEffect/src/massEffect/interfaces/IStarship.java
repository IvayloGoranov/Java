package massEffect.interfaces;

import massEffect.gameObjects.locations.StarSystem;

public interface IStarship extends IEnhanceable {

	String getName();
	
	void setName(String name);

    int getHealth();
    
    void setHealth(int health);

    int getShields();
    
    void setShields(int shields);

    int getDamage();
	
	void setDamage(int damage);

    double getFuel();
    
    void setFuel(double fuel);

    StarSystem getLocation();
    
    void setLocation(StarSystem location);

    IProjectile produceAttack();

    void respondToAttack(IProjectile attack);
}
