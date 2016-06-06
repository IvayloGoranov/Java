package pokerGame;

import java.util.Arrays;

import pokerGame.deck.Card;
import pokerGame.handEvaluation.HandType;
import pokerGame.interfaces.IDeckOfCards;

public class CardDealer {

	private static final int NumberOfCardsPerHand = 5;
    
    private Card[] playerHand;
    private Card[] computerHand;
    private Card[] sortedPlayerHand;
    private Card[] sortedComputerHand;
    private IDeckOfCards deckOfCards;

    public CardDealer(IDeckOfCards deckOfCards) {
    	
        this.playerHand = new Card[NumberOfCardsPerHand];
        this.computerHand = new Card[NumberOfCardsPerHand];
        this.sortedPlayerHand = new Card[NumberOfCardsPerHand];
        this.sortedComputerHand = new Card[NumberOfCardsPerHand];
        this.setDeckOfCards(deckOfCards);
    }

    public IDeckOfCards getDeckOfCards() {
		
    	return this.deckOfCards;
	}

	public void setDeckOfCards(IDeckOfCards deckOfCards) {
		
		this.deckOfCards = deckOfCards;
	}

	public void dealCards() {
		
        this.getPlayersHands();
        this.sortPlayersHands();
        this.displayCards();
        this.evaluateWinnigHand();
    }

    private void getPlayersHands() {
    	
        for (int i = 0, j = 0; i < NumberOfCardsPerHand; i++, j = j + 2) {
        	
            this.playerHand[i] = this.getDeckOfCards().getDeck()[j];
            this.computerHand[i] = this.getDeckOfCards().getDeck()[j + 1];
        }
    }

    private void sortPlayersHands() {
    	
        for (int i = 0; i < NumberOfCardsPerHand; i++) {
        	
            this.sortedPlayerHand[i] = this.playerHand[i];
        }

        for (int i = 0; i < NumberOfCardsPerHand; i++) {
        	
            this.sortedComputerHand[i] = this.computerHand[i];
        }

        Arrays.sort(this.sortedPlayerHand);
        Arrays.sort(this.sortedComputerHand);
    }

    private void displayCards() {
    	
        throw new UnsupportedOperationException();
    }

    private void evaluateWinnigHand() {
    	
        HandEvaluator playerHandEvaluator = new HandEvaluator(sortedPlayerHand);
        HandEvaluator computerHandEvaluator = new HandEvaluator(sortedComputerHand);

        //get the player's and computer's hand
        HandType playerHand = playerHandEvaluator.evaluateHand();
        HandType computerHand = computerHandEvaluator.evaluateHand();

        //display each hand
        System.out.println("Player's Hand: " + playerHand.toString());
        System.out.println("Computer's Hand: " + computerHand.toString());

        //evaluate hands
        if (playerHand.getNumValue() > computerHand.getNumValue()) {
        	
            System.out.println("Player WINS!");
        } else if (playerHand.getNumValue() < computerHand.getNumValue()) {
        	
            System.out.println("Computer WINS!");
        } else //if the hands are the same, evaluate the values {
            //first evaluate who has higher value of poker hand
            if (playerHandEvaluator.getHandValue().getTotalValue() 
            		> computerHandEvaluator.getHandValue().getTotalValue()) {
                
            	System.out.println("Player WINS!");
            } else if (playerHandEvaluator.getHandValue().getTotalValue() 
            		< computerHandEvaluator.getHandValue().getTotalValue()) {
                
            	System.out.println("Computer WINS!");
            } //if both have the same poker hand (for example, both have a pair of queens), 
            //than the player with the next higher card wins
            else if (playerHandEvaluator.getHandValue().getHighCardValue() > 
            computerHandEvaluator.getHandValue().getHighCardValue()) {
            	
                System.out.println("Player WINS!");
            } else if (playerHandEvaluator.getHandValue().getHighCardValue() < 
            computerHandEvaluator.getHandValue().getHighCardValue()) {
            	
                System.out.println("Computer WINS!");
            } else {
            	
                System.out.println("DRAW, no one wins!");
            }
    }
}
