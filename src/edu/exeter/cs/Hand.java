package edu.exeter.cs;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int handSize = 2;
	private int totalScore;
	private int count;
	private int split;
	private String cards;
	
	public Hand() {
		hand = new ArrayList<Card>(handSize);
		for (int i=0; i < handSize; i++) {
			hand.add(i,new Card());
		}
	}
	
	public void deal() {
		handSize = 2;
		hand = new ArrayList<Card>(handSize);
		for (int i=0; i < handSize; i++) {
			hand.add(i,new Card());
		}
	}
	public void hit() {
		handSize++;
		hand.add(handSize-1,new Card());	
	}
	
	public boolean isBlackJack() {
		totalScore = 0;
		for (int i=0; i < 2; i++) {
			totalScore += hand.get(i).getValue();
		}
		if (totalScore == 21) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSplit() {
		
		split = hand.get(handSize-1).getValue();
		for (int i=0; i < handSize; i++) {
			if (split == hand.get(i).getNum()) {
				return true;
			}
		}
		return false;
	}
	
	public int totalScore() {
		totalScore = 0;
		count = 0;
		for (int i=0; i < handSize; i++) {
			if (hand.get(i).getNum() == 1) {
				count++;
			}
			totalScore += hand.get(i).getValue();
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
