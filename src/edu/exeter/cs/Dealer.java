package edu.exeter.cs;

import java.util.ArrayList;

public class Dealer extends Person{
	
	public Dealer() {}
	
	public void deal() {
		hands = new ArrayList<Hand>(handsSize);
		for (int i=0; i < handsSize; i++) {
			hands.add(i,new Hand());
		}
		hands.get(handsSize-1).deal();
	}
	
	public void turn() {
		while (playing && stand) {
			if (hands.get(handsSize-1).totalScore() < 17 && playing == true) {
				hands.get(handsSize-1).hit();
				if (hands.get(handsSize-1).totalScore() > 20) {
					playing = false;
				}
				System.out.println("The dealer hit.");
				System.out.println("The dealer's cards are the " + hands.get(handsSize-1).getCards() + ".");
				System.out.println();
			} else {
				stand = false;
				System.out.println("The dealer stood.");
				System.out.println();
			}
		}
	}
	
	public void reset() {
		playing = true;
		stand = true;
	}
	
}
