package style;

public interface Deck {
	
	/* Deck
	 * Represents the deck of 52 cards
	 * 
	 */

	boolean hasHand();
	Card dealNextCard();
	PokerHand dealHand();
	void findAndRemove(Card c);

}