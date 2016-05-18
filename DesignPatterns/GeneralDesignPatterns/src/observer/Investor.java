package observer;

public class Investor implements StockListener {

	private final String name;
	private Stock stock;

    public Investor(String name) {
    	
        this.name = name;
    }

    public Stock getStock() {
    	
		return this.stock;
	}

	public void setStock(Stock stock) {
		
		this.stock = stock;
	}

	@Override
	public void update(Stock stock) {
		
		System.out.printf("Notified %s of %s's " + "change to %.2f\n", this.name, stock.getSymbol(), stock.getPrice());
	}

}
