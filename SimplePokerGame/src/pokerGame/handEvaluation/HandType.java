package pokerGame.handEvaluation;

public enum HandType {

	Nothing(0), Pair(1), TwoPairs(2), ThreeOfAKind(3), 
	        Straight(4), Flush(5), FullHouse(6), FourOfAKind(7), StraightFlush(8);
	
	private int numValue;
	private HandType(int numValue) {
		
		this.numValue = numValue;
	}
	    
	public int getNumValue() {
		
		return this.numValue;
	}
}
