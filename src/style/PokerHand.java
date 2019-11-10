package style;

public interface PokerHand {
	
	/* PokerHand
	 * Represents a collection of 5 cards which have a hand value and a hand rank
	 * depending on the relationship between the ranks and suits of the cards.
	 * 
	 * High card
	 * Hand not recognized as any other hand type
	 * Hand Value: 1
	 * Hand Rank: Highest ranked card in hand
	 * 
	 * One Pair
	 * Hand contains exactly 2 cards of same rank and 3 other non-matching cards
	 * Hand Value: 2
	 * Hand Rank: Rank of paired cards
	 * 
	 * Two pair
	 * Hand contains exactly two different pairs and a 5th non-matching card
	 * Hand Value: 3
	 * Hand Rank: Rank of the higher of the two pairs
	 * 
	 * Three of a Kind
	 * A hand that contains exactly three cards that match in rank and two additional cards that do not match that rank or each other
	 * Hand Value: 4
	 * Hand Rank:
	 *
	 * Straight
	 * A hand that contains five cards of consecutive rank
	 * Hand Value: 5
	 * Hand Rank: Rank of highest card in the sequence
	 * Note, there is a special straight called "the wheel" which is comprised of the sequence: Ace, 2, 3, 4, 5. 
	 * In this case, although the ace has a rank value of 14, it acts as if its rank was 1. 
	 * In the case of the wheel, hand rank should be 5.
	 * 
	 * Flush
	 * A hand with any five cards that match in suit
	 * Hand Value: 6
	 * Hand Rank: Rank of the highest ranked card in the hand
	 * 
	 * Full House
	 * A hand with three cards that match in rank as in a three of a kind with the other two cards in the hand also matching in rank as in a pair
	 * Hand Value: 7
	 * Hand Rank: Rank of the three cards that match.
	 * 
	 * Four of a Kind
	 * A hand that contains fours cards that all match in rank with an arbitrary fifth card.
	 * Hand Value: 8
	 * Hand Rank: Rank of four matching cards
	 * 
	 * Straight Flush
	 * A hand that is both a straight and a flush
	 * Hand Value: 9
	 * Hand Rank: Rank of the highest card in the hand (again with the caveat about the wheel)
	 *
	 *
	 */
	
	Card[] getCards();
	boolean contains(Card c);

	boolean isOnePair();
	boolean isTwoPair();
	boolean isThreeOfAKind();
	boolean isStraight();
	boolean isFlush();
	boolean isFullHouse();
	boolean isFourOfAKind();
	boolean isStraightFlush();

	int getHandTypeValue();
	int getHandRank();
	
	int compareTo(PokerHand other);
	
}
