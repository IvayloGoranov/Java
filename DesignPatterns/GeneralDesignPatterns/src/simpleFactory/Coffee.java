package simpleFactory;

public class Coffee {

	private int milkContent;
	private int coffeeContent;

    public Coffee(int milk, int coffee) {
    	
        this.setMilkContent(milk);
        this.setCoffeeContent(coffee);
    }

	public int getMilkContent() {
		return milkContent;
	}

	public void setMilkContent(int milkContent) {
		this.milkContent = milkContent;
	}

	public int getCoffeeContent() {
		return coffeeContent;
	}

	public void setCoffeeContent(int coffeeContent) {
		this.coffeeContent = coffeeContent;
	}
}
