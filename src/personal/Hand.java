package personal;

import java.util.ArrayList;

public class Hand {
	
	private int handSize = 2;
	private int totalScore;
	private int count;
	private int split;
	private String cards;
	
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>(handSize);
		for (int i=0; i < handSize; i++) {
			hand.add(i,new Card());
		}
	}
	public void hit() {
		handSize++;
		hand.add(handSize-1,new Card());	
	}
	
	public Hand(Card card) {
		hand = new ArrayList<Card>(handSize-1);
		for (int i=0; i < handSize-1; i++) {
			hand.add(i,card);
		}
	}
	public void hit(int n) {
		hand.add(1,new Card());	
	}
	public Card card() {
		return hand.get(handSize-1);
	}
	
	public void remove() {
		hand.remove(handSize-1);
	}
	
	public void deal() {
		handSize = 2;
		Card card = new Card();
		card.shuffle();
		hand = new ArrayList<Card>(handSize);
		for (int i=0; i < handSize; i++) {
			hand.add(i,new Card());
		}
	}
	
	public boolean isBlackJack() {
		totalScore = 0;
		for (int i=0; i < 2; i++) {
			totalScore += hand.get(i).getRank();
		}
		if (totalScore == 21) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSplit() {
		split = hand.get(handSize-1).getRank();
		if (split == hand.get(handSize-2).getRank()) {
			return true;
		}
		return false;
	}
	
	public int totalScore() {
		totalScore = 0;
		count = 0;
		for (int i=0; i < handSize; i++) {
			if (hand.get(i).getRank() == 11) {
				count++;
			}
			totalScore += hand.get(i).getRank();
			while (totalScore > 21 && count > 0) {
				totalScore -= 10;
				count--;
			}
		}
		return totalScore;
	}
	
	public String getCards() {
		cards = "";
		for (int i=0; i < handSize-1; i++) {
			cards += hand.get(i).toString() + ", the ";
		}
		cards = cards.substring(0, cards.length()-6);
		cards += ", and the " + hand.get(handSize-1).toString();
		return cards;
	}
	public String getCard(int n) {
		return hand.get(n-1).toString();
	}
	public String getCard() {
		return hand.get(handSize-1).toString();
	}
	
}
