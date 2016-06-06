package pokerGame.deck;

import java.util.Random;

import pokerGame.interfaces.IDeckOfCards;

public class DeckOfCards implements IDeckOfCards {

	private static final int NumberOfCardsInDeck = 52;
    private Card[] deckOfCards;

    private static final Random randomizer = new Random();

    public DeckOfCards() {
    	
        this.deckOfCards = new Card[NumberOfCardsInDeck];
        this.setUpDeck();
    }

    public Card[] getDeck() {
    	
    	return this.deckOfCards;
    }

    //Create deck of 52 values: 13 values each with 4 suits.
    private void setUpDeck() {
    	
        int i = 0;
        for (CardSuit cardSuit : CardSuit.values()) {
        	
            for (CardValue cardValue : CardValue.values())
            {
                deckOfCards[i] = new Card(cardSuit, cardValue );
                i++;
            }
        }

        this.shuffleDeck();
    }

    //Shuffle deck of cards using FisherYatesShuffle.
    public void shuffleDeck() {
    	
        for (int i = 0; i < NumberOfCardsInDeck; i++) {
        	
            int randomIndex = i + randomizer.nextInt(NumberOfCardsInDeck - i);
            Card temp = this.deckOfCards[i];
            this.deckOfCards[i] = this.deckOfCards[randomIndex];
            this.deckOfCards[randomIndex] = temp;
        }
    }
}
