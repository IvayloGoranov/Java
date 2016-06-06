package pokerGame;

import pokerGame.deck.Card;
import pokerGame.deck.CardSuit;
import pokerGame.handEvaluation.HandType;
import pokerGame.handEvaluation.HandValue;

public class HandEvaluator {

	private static final int NumberOfCardsInDeck = 52;
    
    private int heartsSum;
    private int clubsSum;
    private int diamondsSum;
    private int spadesSum;
    private Card[] cards; //5 cards in hand.
    private HandValue handValue;
    
    public HandEvaluator(Card[] sortedHand) {
    	
        this.heartsSum = 0;
        this.clubsSum = 0;
        this.diamondsSum = 0;
        this.spadesSum = 0;
        this.cards = sortedHand;
        this.handValue = new HandValue();
    }

    public HandValue getHandValue() { 
    	
    	return this.handValue; 
    } 

    public HandType evaluateHand() {
    	
        this.getSuitCountPerHand();

        if (this.isOnePair()) {
        	
            return HandType.Pair;
        } else if (this.isTwoPairs()) {
        	
            return HandType.TwoPairs;
        } else if (this.isThreeOfAKind()) {
        	
            return HandType.ThreeOfAKind;
        } else if (this.isStraight()) {
            
        	return HandType.Straight;
        } else if (this.isFlush()) {
        	
            return HandType.Flush;
        } else if (this.isFullHouse()) {
        	
            return HandType.FullHouse;
        } else if (this.isFourOfAKind()) {
        	
            return HandType.FourOfAKind;
        } else if (this.isStraightFlush()) {
        	
            return HandType.StraightFlush;
        } else {
            
        	this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue()); //High card wins.
            return HandType.Nothing;
        }
    }

    private void getSuitCountPerHand() {
    	
        for (Card card : this.cards) {
        	
            if (card.getCardSuit() == CardSuit.Hearts) {
            	
                this.heartsSum++;
            } else if (card.getCardSuit() == CardSuit.Clubs) {
            	
                this.clubsSum++;
            } else if (card.getCardSuit() == CardSuit.Diamonds) {
            	
                this.diamondsSum++;
            } else if (card.getCardSuit() == CardSuit.Spades) {
            	
                this.spadesSum++;
            }
        }
    }

    private boolean isFourOfAKind() {
    	
        boolean isFourOfAKind = false;
        
        //First four cards are the same.
        if (this.cards[0].getCardValue() == this.cards[1].getCardValue() && 
        		this.cards[0].getCardValue() == this.cards[2].getCardValue() &&
        		this.cards[0].getCardValue() == this.cards[3].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[0].getCardValue().getNumValue() * 4);
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isFourOfAKind = true;
        } //Last four cards are the same. 
        else if(this.cards[1].getCardValue() == this.cards[2].getCardValue() 
        		&& this.cards[1].getCardValue() == this.cards[3].getCardValue() &&
            this.cards[1].getCardValue() == this.cards[4].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[1].getCardValue().getNumValue() * 4);
            this.handValue.setHighCardValue(this.cards[0].getCardValue().getNumValue());
            isFourOfAKind = true;
        }

        return isFourOfAKind;
    }

    private boolean isFullHouse() {
    	
        boolean isFullHouse = false;
        boolean isThreeOfAkindAndPair = this.cards[0].getCardValue() == this.cards[1].getCardValue() && 
            this.cards[0].getCardValue() == this.cards[2].getCardValue() 
            && this.cards[3].getCardValue() == this.cards[4].getCardValue();
        boolean isPairAndThreeOfAKind = this.cards[0].getCardValue() == this.cards[1].getCardValue() &&
            this.cards[2].getCardValue() == this.cards[3].getCardValue() && 
            this.cards[2].getCardValue() == this.cards[4].getCardValue();

        if (isThreeOfAkindAndPair || isPairAndThreeOfAKind) {
        	
            this.handValue.setTotalValue(this.cards[0].getCardValue().getNumValue() + this.cards[1].getCardValue().getNumValue() +
                this.cards[2].getCardValue().getNumValue() + this.cards[3].getCardValue().getNumValue() 
                + (int)this.cards[4].getCardValue().getNumValue()); 
            
            isFullHouse = true;
        }
        
        return isFullHouse;
    }

    private boolean isFlush() {
    	
        boolean isFlush = false;
        if (this.heartsSum == 5 || this.spadesSum == 5 || this.diamondsSum == 5 || this.clubsSum == 5) {
        	
            //If more than one player has flush, the player with highest card wins.
            this.handValue.setTotalValue(this.cards[4].getCardValue().getNumValue());
            isFlush = true;
        }

        return isFlush;
    }

    private boolean isStraight() {
    	
        boolean isStraight = false;
        if (this.cards[0].getCardValue().getNumValue() + 1 == this.cards[1].getCardValue().getNumValue() && 
            this.cards[1].getCardValue().getNumValue() + 1 == this.cards[2].getCardValue().getNumValue() && 
            this.cards[2].getCardValue().getNumValue() + 1 == this.cards[3].getCardValue().getNumValue() &&
            this.cards[3].getCardValue().getNumValue() + 1 == this.cards[4].getCardValue().getNumValue())
        {
            //If more than one player has straight, the player with highest card wins.
            this.handValue.setTotalValue(this.cards[4].getCardValue().getNumValue());
            isStraight = true;
        }

        return isStraight;
    }

    private boolean isThreeOfAKind() {
    	
        boolean isThreeOfAKind = false;
        boolean areFirstThreeCardsSame = this.cards[0].getCardValue() == this.cards[1].getCardValue()
            && this.cards[0].getCardValue() == this.cards[2].getCardValue();
        boolean areMiddleThreeCardsSame = this.cards[1].getCardValue() == this.cards[2].getCardValue() 
            && this.cards[1].getCardValue() == this.cards[3].getCardValue();
        
        //First three cards are the same.
        if (areFirstThreeCardsSame || areMiddleThreeCardsSame) {
        	
            this.handValue.setTotalValue(this.cards[2].getCardValue().getNumValue() * 3);
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isThreeOfAKind = true;
        } //Last three cards are the same.
        else if (this.cards[2].getCardValue() == this.cards[3].getCardValue() 
        		&& this.cards[2].getCardValue() == this.cards[4].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[2].getCardValue().getNumValue() * 3);
            this.handValue.setHighCardValue(this.cards[1].getCardValue().getNumValue());
            isThreeOfAKind = true;
        }
        
        return isThreeOfAKind;
    }

    private boolean isTwoPairs() {
    	
        //With two pairs second card will always be part of first pair
        //and fourth card will always be part of second pair.
        
        boolean isTwoPairs = false;
        //First and second card form one pair, third and fourth card - another pair.
        if (this.cards[0].getCardValue() == this.cards[1].getCardValue() 
        		&& this.cards[2].getCardValue() == this.cards[3].getCardValue()) {
        	
            this.handValue.setTotalValue((this.cards[0].getCardValue().getNumValue() * 2) 
            + (this.cards[2].getCardValue().getNumValue() * 2));
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isTwoPairs = true;
        } //First and second card form one pair, last two cards - another pair.
        else if (this.cards[0].getCardValue() == this.cards[1].getCardValue() 
        		&& this.cards[3].getCardValue() == this.cards[4].getCardValue()) {
        	
            this.handValue.setTotalValue((this.cards[0].getCardValue().getNumValue() * 2) + 
            (this.cards[3].getCardValue().getNumValue() * 2));
            this.handValue.setHighCardValue(this.cards[2].getCardValue().getNumValue());
            isTwoPairs = true;
        } //Second and third card form one pair, last two cards - another pair.
        else if (this.cards[1].getCardValue() == this.cards[2].getCardValue() 
        		&& this.cards[3].getCardValue() == this.cards[4].getCardValue()) {
        	
            this.handValue.setTotalValue((this.cards[1].getCardValue().getNumValue() * 2) 
            + (this.cards[3].getCardValue().getNumValue() * 2));
            this.handValue.setHighCardValue(this.cards[0].getCardValue().getNumValue());
            isTwoPairs = true;
        }

        return isTwoPairs;
    }

    private boolean isOnePair() {
        
    	boolean isOnePair = false;
        //First and second cards form a pair, last card is high card.
    	
        if (this.cards[0].getCardValue() == this.cards[1].getCardValue()) {
            this.handValue.setTotalValue(this.cards[0].getCardValue().getNumValue() * 2);
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isOnePair = true;
        } //Second and third cards form one pair, last card is high card.
        else if (this.cards[1].getCardValue() == this.cards[2].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[1].getCardValue().getNumValue() * 2);
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isOnePair = true;
        } //Third and fourth cards form one pair, last card is high card.
        else if (this.cards[2].getCardValue() == this.cards[3].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[2].getCardValue().getNumValue() * 2);
            this.handValue.setHighCardValue(this.cards[4].getCardValue().getNumValue());
            isOnePair = true;
        } //Last two cards form one pair, third card is high card.
        else if (this.cards[3].getCardValue() == this.cards[4].getCardValue()) {
        	
            this.handValue.setTotalValue(this.cards[3].getCardValue().getNumValue() * 2);
            this.handValue.setHighCardValue(this.cards[3].getCardValue().getNumValue());
            isOnePair = true;
        }

        return isOnePair;
    }

    private boolean isStraightFlush() {
    	
        boolean isStraightFlush = false;
        boolean isStraight = this.cards[0].getCardValue().getNumValue() + 1 == this.cards[1].getCardValue().getNumValue() &&
            this.cards[1].getCardValue().getNumValue() + 1 == this.cards[2].getCardValue().getNumValue() &&
            this.cards[2].getCardValue().getNumValue() + 1 == this.cards[3].getCardValue().getNumValue() &&
            this.cards[3].getCardValue().getNumValue() + 1 == this.cards[4].getCardValue().getNumValue();
        boolean isFlush = this.heartsSum == 5 || this.spadesSum == 5 || this.diamondsSum == 5 || this.clubsSum == 5;
        
        if (isFlush && isStraight) {
        	
            //If more than one player has straight flush, the player with highest card wins.
            this.handValue.setTotalValue(this.cards[4].getCardValue().getNumValue());
            isStraightFlush = true;
        }

        return isStraightFlush;
    }
}
