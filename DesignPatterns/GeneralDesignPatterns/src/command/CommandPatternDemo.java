package command;

public class CommandPatternDemo {
	   
	public static void main(String[] args) {
	      
		Stock abcStock = new Stock();

	    Executable buyStockOrder = new BuyStockCommand(abcStock);
	    Executable sellStockOrder = new SellStockCommand(abcStock);

	    Broker broker = new Broker();
	    broker.takeOrder(buyStockOrder);
	    broker.takeOrder(sellStockOrder);

	    broker.placeOrders();
	}	
}
