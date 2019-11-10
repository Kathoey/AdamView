package style;

public class PokerHandImpl implements PokerHand {

	private Card[] cardHand;
	
	/* PokerHandImpl
	 * constructor checks for valid input hand and stores cards input 
	 * also resorts the internal cardHand field in rank order
	 */

	public PokerHandImpl(Card[] cards) {
		if (cards == null) {
			throw new RuntimeException("Null Card Input");
		}
		if (cards.length != 5) {
			throw new RuntimeException("Invalid Hand Size");
		}
		for (int i=0; i<5; i++) {
			if (cards[i] == null) {
				throw new RuntimeException("Contains a Null Card");
			}
		}

		cardHand = cards.clone();
		
		for (int i=0; i<5; i++) {			
			for (int j=i; j<5; j++) {
				if (cardHand[j].getRank() < cardHand[i].getRank()) {
					Card tmp = cardHand[i];
					cardHand[i] = cardHand[j];
					cardHand[j] = tmp;
				}
			}
		} 
	}

	public Card[] getCards() {
		return cardHand.clone();
	}

	// contains
	// Method that checks if parameter card is equal in suit and rank to a card in the hand
	// returns true if there is an equal card, false otherwise
	
	public boolean contains(Card otherCard) {
		for (int i=0; i<5; i++) {
			if (cardHand[i].equals(otherCard)) {
				return true;
			}
		}
		return false;
	}

	// See PokerHand interface for hand classifications 
	
	// isOnePair
	// Checks for the existence of one pair
	// Returns true if only one pair is found , false if otherwise
	
	public boolean isOnePair() {

		int pairIndex = findPairStartingAt(0);
		
		// checks no other pairs in hand 
		boolean noOtherPairs = (findPairStartingAt(pairIndex+1) == -1);
		
		return ((pairIndex != -1) && noOtherPairs);
		
	}
	
	// isTwoPair
	// Checks for the existence of two pairs
	// Returns true if only a pair of pairs is found, false if otherwise
	
	public boolean isTwoPair() {
		
		int firstPairIdx = findPairStartingAt(0);
		// check for second pair
		int secondPairIndex = findPairStartingAt(firstPairIdx+2);
		
		// returns false if this hand is actually a four of a kind or full house and not exclusively a two pair
		return ((firstPairIdx != -1) && (secondPairIndex != -1) && !isFourOfAKind() && !isFullHouse());
	}

	// isThreeOfAKind
	// Checks for the existence of 3 matching cards
	// Returns true if 3 matching cards are found, false if otherwise
	
	public boolean isThreeOfAKind() {
		int firstPairIndex = findPairStartingAt(0);
		
		if ((firstPairIndex == -1) || (firstPairIndex == 3)) {
			return false;
		}
		
		// returns false if this hand is actually a four of a kind or full house and not exclusively a 3 of a kind
		return ((cardHand[firstPairIndex].getRank() == cardHand[firstPairIndex+2].getRank()) &&
				!isFourOfAKind() && !isFullHouse());
	}
	
	// isStraight
	// Checks for the existence of a straight (cards in consecutive ranked order)
	// Returns true if 5 cards in consecutive ranked order or are in the wheel configuration, false if otherwise
	
	public boolean isStraight() {
		
		boolean straightCheck = true;
		// checks that the cards are in ranked order, returns false if they arent
		for (int i=0; i<4; i++) {
			if (cardHand[i].getRank()+1 != cardHand[i+1].getRank()) {
				straightCheck = false;
				break;
			}
		}
		// also checks for wheel exception
		return straightCheck || isTheWheel();
	}
	
	// isTheWheel
	// Checks if the hand is in the Wheel Configuration
	// Returns true if the hand is in the wheel configuration, false if otherwise
	
	private boolean isTheWheel() {
		
		if (cardHand[0].getRank() == 2) {
			if (cardHand[1].getRank() == 3) {
				if (cardHand[2].getRank() == 4) {
					if (cardHand[3].getRank() == 5) {
						if (cardHand[4].getRank() == 14) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	// isFlush
	// Checks if the hand is a flush (all cards have the same suit)
	// returns true if all cards of of the same suit, false otherwise
	
	public boolean isFlush() {
		for (int i=1; i<5; i++) {
			if (cardHand[i].getSuit() != cardHand[0].getSuit()) {
				return false;
			}
		}
		return true;
	}
	
	// isFullHouse
	// Checks for the combination of a 3 of a kind and a pair in both orders they could appear
	// Returns true if there is a 3 of a kind and a pair, false otherwise

	public boolean isFullHouse() {
		return (((cardHand[0].getRank() == cardHand[1].getRank()) &&
				 (cardHand[2].getRank() == cardHand[3].getRank()) &&
				 (cardHand[3].getRank() == cardHand[4].getRank())) ||
				((cardHand[0].getRank() == cardHand[1].getRank()) &&
				 (cardHand[1].getRank() == cardHand[2].getRank()) &&
				 (cardHand[3].getRank() == cardHand[4].getRank())));		
	}
	
	// isFourOfAKind
	// Checks for the existence of 4 of a kind in both orders it could appear
	// Returns true if a 4 of a kind exists, false otherwise

	public boolean isFourOfAKind() {
		return (((cardHand[0].getRank() == cardHand[1].getRank()) &&
				 (cardHand[1].getRank() == cardHand[2].getRank()) &&
				 (cardHand[2].getRank() == cardHand[3].getRank()))||
				((cardHand[1].getRank() == cardHand[2].getRank()) &&
				 (cardHand[2].getRank() == cardHand[3].getRank()) &&
				 (cardHand[3].getRank() == cardHand[4].getRank())));		
	}	
	
	// isStraightFlush
	// Checks if the hand is both a straight and a flush
	// Returns true if the hand is a straight and a flush
	
	public boolean isStraightFlush() {
		if (isStraight() == true) {
			if (isFlush() == true) {
				return true;
			}
		}
		return false;
	}
	
	// getHandRank
	// returns an integer hand rank depending on the type of hand (hand ranks found in interface)

	public int getHandRank() {
		if (isOnePair() == true) {
			return cardHand[findPairStartingAt(0)].getRank();
		} else if (isTwoPair() == true) {
			return cardHand[3].getRank();
		} else if (isThreeOfAKind() == true || isFourOfAKind() == true || isFullHouse() == true) {
			return cardHand[2].getRank();
		} else if (isTheWheel() == true) {
			return 5;
		} else {
			return cardHand[4].getRank();
		}
	}
	
	//compareTo
	// compares the PokerHand with a given other PokerHand
	// input: PokerHand other
	// output: -1 if other hand value is larger, 1 if other hand value is smaller
	// If the hand values are equal, returns -1 if hand rank is smaller, or returns 1 if hand rank is larger
	// If both hand values and hand ranks are the same returns 0

	public int compareTo(PokerHand other) {
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
		} else {
			if (getHandRank() < other.getHandRank()) {
				return -1;
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	// getHandTypeValue
	// Checks hand for various types and returns the corresponding type number
	// Returns 1 for high card case if not a different hand
	
	public int getHandTypeValue() {
		
		if (isStraightFlush()) return 9;
		
		if (isOnePair()) return 2;
		
		if (isTwoPair()) return 3;
		
		if (isThreeOfAKind()) return 4;
		
		if (isStraight()) return 5;
		
		if (isFlush()) return 6;
		
		if (isFullHouse()) return 7;
		
		if (isFourOfAKind()) return 8;
	return 1;
	}

	/* findPairStartingAt
	 * Helper Method gives calling function an index number
	 *  Input: non-negative integer num
	 *  Output: -1 or i, with I being the index containing the first card of a pair, and -1 being returned if there is no found pair
	 */
	
	
	private int findPairStartingAt(int num) {	
		
		if (num < 0){
			num = 0;
		}
		
		if (num >= 4) {
			return -1;
		}
		
		for (int i=num; i<4; i++) {
			if (cardHand[i].getRank() == cardHand[i+1].getRank()) {
				return i;
			}		
		}	
		
	return -1;
	}

}
