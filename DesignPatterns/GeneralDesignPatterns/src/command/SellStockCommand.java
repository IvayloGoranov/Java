package command;

public class SellStockCommand implements Executable {

	private Stock stock;

	public SellStockCommand(Stock stock){
		
		this.stock = stock;
	}
	
	@Override
	public void execute() {
		
		this.stock.sell();
	}

}
