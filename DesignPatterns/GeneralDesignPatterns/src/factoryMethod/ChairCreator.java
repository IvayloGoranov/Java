package factoryMethod;

public class ChairCreator extends FactoryMethod {

	@Override
	public Product createProduct() {
		
		Chair chair = new Chair("A chair created by the chair creator");
        
		return chair;
	}

}
