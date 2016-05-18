package simpleFactory;

public class CoffeeFactory {

	public static Coffee GetCoffee(CoffeeType coffeeType)
    {
        switch (coffeeType)
        {
            case REGULAR:
                return new Coffee(0, 150);
            case DOUBLE:
                return new Coffee(0, 200);
            case CAPPUCINO:
                return new Coffee(100, 100);
            case MACCHIATO:
                return new Coffee(200, 100);
            default:
                throw new IllegalArgumentException();
        }
    }
}
