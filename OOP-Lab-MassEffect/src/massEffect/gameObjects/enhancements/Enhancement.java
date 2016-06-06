package massEffect.gameObjects.enhancements;

public class Enhancement {

	private String name;
	private int shieldBonus;
	private int damageBonus;
	private double fuelBonus;
	
	public Enhancement(String name, int shieldBonus, int damageBonus, int fuelBonus) {
		
        this.name = name;
        this.shieldBonus = shieldBonus;
        this.damageBonus = damageBonus;
        this.fuelBonus = fuelBonus;
    }

	public String getName() {
		
		return this.name;
	}

	public int getShieldBonus() {
		
		return this.shieldBonus;
	}

	public int getDamageBonus() {
		
		return this.damageBonus;
	}

	public double getFuelBonus() {
		
		return this.fuelBonus;
	}
}
