package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Stock {

	private final List<StockListener> investors;
    private final String symbol;
    private double price;

    protected Stock(String symbol, double price) {
    	
        this.symbol = symbol;
        this.price = price;
        this.investors = new ArrayList<StockListener>();
    }

    public double getPrice() {
		
    	return this.price;
	}

	public void setPrice(double price) {
		
		if (Math.abs(this.price - price) > 0.001) {
			
            this.price = price;
            this.notifyListener();
        }
	}

	public String getSymbol() {
		
		return this.symbol;
	}

	public void attach(StockListener investor) {
		
        this.investors.add(investor);
    }

    public void detach(StockListener investor) {
    	
        this.investors.remove(investor);
    }

    public void notifyListener() {
    	
        for (StockListener investor : this.investors) {
        	
            investor.update(this);
        }

        System.out.println("");
    }
}
