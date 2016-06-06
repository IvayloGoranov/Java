package pokerGame.deck;

public enum CardValue {

	Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), 
	        Ten(10), Jack(11), Queen(12), King(13), Ace(14);
	
	private int numValue;
	private CardValue(int numValue) {
		
		this.numValue = numValue;
	}
	    
	public int getNumValue() {
		
		return this.numValue;
	}
}
