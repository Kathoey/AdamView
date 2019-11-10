package style;



public interface Card {
	
	/* Card
	 * Represents a card from a standard deck of 52 cards
	 * Each card has a rank and suit attribute
	 */
	
	public enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS};

	int getRank();
	Card.Suit getSuit();
	String toString();
	boolean equals(Card other);
	
	/* CaseSwitch default method that converts Suit enumeration into strings 
	 * Input: 1 of 4 suit enumeration values
	 * Output: suit string
	 */
	
	public static String suitToString(Card.Suit s) {switch (s) {
		case SPADES:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";}
		return null;
		}

}