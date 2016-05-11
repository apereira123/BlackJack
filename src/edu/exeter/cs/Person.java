package edu.exeter.cs;

import java.util.ArrayList;

public class Person {

	public int handsSize;
	public boolean playing;
	public boolean stand;
	
	public ArrayList<Hand> hands;
	
	public Person() {
		handsSize = 1;
		playing = true;
		stand = true;
		
		hands = new ArrayList<Hand>(handsSize);
		for (int i=0; i < handsSize; i++) {
			hands.add(i,new Hand());
		}
	}
	
	public String getCard(int n) {
		return hands.get(n).getCard();
	}
	public String getCards(int n) {
		return hands.get(n).getCards();
	}
	public int totalScore(int n) {
		return hands.get(n).totalScore();
	}
	public boolean isBlackJack(int n) {
		return hands.get(n).isBlackJack();
	}
	public boolean isSplit(int n) {
		return hands.get(n).isSplit();
	}
	
}
