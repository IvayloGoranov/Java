package simpleFactory;

public class SimpleFactoryExample {

	public static void main(String[] args) {
		
		Coffee macchiato = CoffeeFactory.GetCoffee(CoffeeType.MACCHIATO);
        Coffee regularCoffee = CoffeeFactory.GetCoffee(CoffeeType.REGULAR);

        System.out.printf("Macchiato - Milk content: %d ml, Coffee content: %d ml\n", 
        		macchiato.getMilkContent(), macchiato.getCoffeeContent());
        System.out.printf("Regular coffee - Milk content: %d ml, Coffee content: %d ml", 
        		regularCoffee.getMilkContent(), regularCoffee.getCoffeeContent());
	}
}
