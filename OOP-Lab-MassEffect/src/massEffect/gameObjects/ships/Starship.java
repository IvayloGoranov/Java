package massEffect.gameObjects.ships;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import massEffect.gameObjects.enhancements.Enhancement;
import massEffect.gameObjects.locations.StarSystem;
import massEffect.interfaces.IProjectile;
import massEffect.interfaces.IStarship;

public abstract class Starship implements IStarship {

	private Collection<Enhancement> enhancements;
	private String name;
    private int health;
    private int shields;
    private int damage;
    private double fuel;
    private StarSystem location;
    
	protected Starship(String name, int health, int shields, int damage, int fuel, StarSystem location) {
		
        this.setName(name);
        this.setHealth(health);
        this.setShields(shields);
        this.setDamage(damage);
        this.setFuel(fuel);
        this.setLocation(location);
        this.enhancements = new ArrayList<Enhancement>();
    }
    
	protected Starship(String name, StarSystem location) {
		
        this.setName(name);
        this.setLocation(location);
        this.enhancements = new ArrayList<Enhancement>();
    }
	
	@Override
	public Iterable<Enhancement> getEnhancements() {
		
		return this.enhancements;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public void setName(String name) {

		this.name = name;
	}

	@Override
	public int getHealth() {

		return this.health;
	}

	@Override
	public void setHealth(int health) {

		this.health = health;
	}

	@Override
	public int getShields() {

		return this.shields;
	}

	@Override
	public void setShields(int shields) {

		this.shields = shields;
	}

	@Override
	public int getDamage() {

		return this.damage;
	}

	@Override
	public void setDamage(int damage) {
		
		this.damage = damage;
	}

	@Override
	public double getFuel() {

		return this.fuel;
	}

	@Override
	public void setFuel(double fuel) {
		
		this.fuel = fuel;
	}

	@Override
	public StarSystem getLocation() {

		return this.location;
	}

	@Override
	public void setLocation(StarSystem location) {

		this.location = location;
	}

	public abstract IProjectile produceAttack();

	@Override
	public void respondToAttack(IProjectile attack) {
		
		attack.hit(this);
	}
	
	@Override
	public void addEnhancement(Enhancement enhancement) {
		
		if (enhancement == null) {
			
            throw new IllegalArgumentException("Enhancement cannot be null!");
        }
		
        this.enhancements.add(enhancement);
        if (enhancement.getName().equals("ExtendedFuelCells")) {
        	
            this.setFuel(this.getFuel() + 200);
        } else if (enhancement.getName().equals("KineticBarrier")) {
        	
            this.setShields(this.getShields() + 100);
        } else if (enhancement.getName().equals("ThanixCannon")) {
        	
            this.setDamage(this.getDamage() + 50);
        }
	}

	@Override
	public String toString() {
		
		StringBuilder output = new StringBuilder();
        output.append(String.format("--%s - %s\n", this.getName(), this.getClass().getName()));
        if (this.getHealth() <= 0) {
        	
            output.append("(Destroyed)");
        } else {
        	
            output.append(String.format("-Location %s\n", this.getLocation().getName()));
            output.append(String.format("-Health %d\n", this.getHealth()));
            output.append(String.format("-Shields %d\n", this.getShields()));
            output.append(String.format("-Damage %d\n", this.getDamage()));
            output.append(String.format("-Fuel %.2f\n", this.getFuel()));
            output.append(String.format("-Enhancements %s\n", 
            		this.enhancements == null ? StringUtils.join(this.enhancements, ", ") : "N\\A"));
        }
        return output.toString();
	}

}
