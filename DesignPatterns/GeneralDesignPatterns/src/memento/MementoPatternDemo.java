package memento;

public class MementoPatternDemo {

	public static void main(String[] args) {
		
		SalesProspect sale = new SalesProspect("Noel van Halen", "(412) 256-0990", 25000.0);

	    // Store internal state
	    Memento memento = sale.saveStateToMemento();

	    // Continue changing originator
	    sale.setName("Leo Welch");
	    sale.setPhone("(310) 209-7111");
	    sale.setBudget(1000000.0);
	
	    // Restore saved state
	    sale.getStateFromMemento(memento);
	}
}
