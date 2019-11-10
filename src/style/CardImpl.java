package style;

public class CardImpl implements Card {
	
	private static String[] possibleRanks = {null, null, "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;

	private int rank;
	private Card.Suit suit;
	
	// CardImpl
	//constructor stores given rank and suit values as internal fields
	
	public CardImpl(int rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	
	/* equals
	 * Checks for equality of card with given parameter card and returns true if both 
	 * rank and suit are the same and false otherwise
	 */
	
	public boolean equals(Card otherCard) {
		return (rank == otherCard.getRank()) && (suit == otherCard.getSuit());
	}
	
	/* toString
	 * Returns a string representation of the card. 
	 * Returns in form of: "(Card Rank)" + " of " + "(Card Suit)" with (Card Rank)
	 * and (Card Suit) being their respective attributes.
	 * Ex: "Queen of Hearts" or "Two of Clubs"
	 */
	
	public String toString() {
		return possibleRanks[getRank()] + " of " + Card.suitToString(getSuit());
	}

}
