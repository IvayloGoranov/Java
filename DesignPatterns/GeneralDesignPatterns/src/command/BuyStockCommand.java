package command;

public class BuyStockCommand implements Executable {

	private Stock stock;

	public BuyStockCommand(Stock stock){
		
		this.stock = stock;
	}
	
	@Override
	public void execute() {

		this.stock.buy();
	}

}
