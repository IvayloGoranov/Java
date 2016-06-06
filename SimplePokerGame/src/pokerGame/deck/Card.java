package pokerGame.deck;

public class Card implements Comparable<Card> {

	private CardSuit cardSuit;
	private CardValue cardValue;
	
	public Card() {
	}
	
	public Card(CardSuit cardSuit, CardValue cardValue) {
		
		this.cardSuit = cardSuit;
		this.cardValue = cardValue;
	}
	
	public CardSuit getCardSuit() {
		
		return this.cardSuit;
	}

	public void setCardSuit(CardSuit cardSuit) {
		
		this.cardSuit = cardSuit;
	}

	public CardValue getCardValue() {
		
		return this.cardValue;
	}

	public void setCardValue(CardValue cardValue) {
		
		this.cardValue = cardValue;
	}

	@Override
	public int compareTo(Card card) {

		if (this.getCardValue().getNumValue() < (int)card.getCardValue().getNumValue()) {
			
            return -1;
        } else if (this.getCardValue().getNumValue() < (int)card.getCardValue().getNumValue()) {
        	
            return 0;
        } else {
        	
            return 1;
        }
	}
}
