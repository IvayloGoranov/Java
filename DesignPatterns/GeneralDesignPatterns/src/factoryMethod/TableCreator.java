package factoryMethod;

public class TableCreator extends FactoryMethod {

	@Override
	public Product createProduct() {

		Table table = new Table("A table created by the table creator.");
        return table;
	}

}
