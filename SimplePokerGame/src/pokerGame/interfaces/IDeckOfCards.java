package pokerGame.interfaces;

import pokerGame.deck.Card;

public interface IDeckOfCards {

	Card[] getDeck();
	
    void shuffleDeck();
}
