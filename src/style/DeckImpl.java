package style;

public class DeckImpl implements Deck {
	
	//Instance fields
	
	private Card[] deckOfCards;			
	private int numLeftToDeal;
	
	// DeckImpl
	/* Constructor for DeckImpl
	 */
	
	public DeckImpl() {
		numLeftToDeal = 52;
		deckOfCards = new Card[numLeftToDeal];
		
		// Calls CardImpl to construct all 52 different cards from all suits and ranks
		int cIndex = 0;
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 2; rank <= CardImpl.ACE; rank++) {
				
				deckOfCards[cIndex] = new CardImpl(rank, s);
				cIndex += 1;
			}
		}
		
		// Shuffles all cards into random order
		for (int i=0; i<deckOfCards.length; i++) {
			int swapIndex = i + ((int) (Math.random() * (deckOfCards.length - i)));
			Card tmp = deckOfCards[i];
			
			deckOfCards[i] = deckOfCards[swapIndex];
			
			deckOfCards[swapIndex] = tmp;
		}		
	}

	// hasHand
	// Checks to see if there are still enough cards left in deck to deal another hand returns true if there is, false otherwise
	
	public boolean hasHand() {
		return (numLeftToDeal >= 5);
	}

	// dealNextCard
	// Returns the next card in the deck, throws exception if there are no more cards to deal
	// decreases the number of cards left to deal every time it is called
	
	public Card dealNextCard() {
		if (numLeftToDeal== 0) {
			throw new RuntimeException();
		}
		Card dealtCard = deckOfCards[nextUndealtIndex()];
		numLeftToDeal -= 1;
		return dealtCard;
	}

	// dealHand
	// Creates a new PokerHand by using the next 5 dealt cards and returns it
	// Throws exception if there are not enough cards left to deal
	
	public PokerHand dealHand() {
		if (hasHand()) {
			throw new RuntimeException("Deck does not have enough cards to deal another hand");
		}
		
		Card[] hand_cards = new Card[5];
		for (int i=0; i<hand_cards.length; i++) {
			
			hand_cards[i] = dealNextCard();
		}
		PokerHand h = new PokerHandImpl(hand_cards);
		return h;
	}	

	// findAndRemove
	// Returns void (does not return a value)
	// Instead it finds and removes in the deck the card that equals the given card; if there are no more cards, then it does not remove a card and does nothing
	
	public void findAndRemove(Card givenCard) {
		if (numLeftToDeal == 0) {
			return;
		}
		
		for (int i=nextUndealtIndex(); i<52; i++) {
			if (deckOfCards[i].equals(givenCard)) {
				Card tmp = deckOfCards[i];
				deckOfCards[i] = deckOfCards[nextUndealtIndex()];
				deckOfCards[nextUndealtIndex()] = tmp;
				dealNextCard();
				return;
			}
		}
		return;
	}
	
	// nextUndealtIndex
	// Helper method that returns the next index that contains a card
	
	private int nextUndealtIndex() {
		return 52-numLeftToDeal;
	}
}
