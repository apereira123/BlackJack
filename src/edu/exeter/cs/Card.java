package edu.exeter.cs;

import java.util.Scanner;

public class Card {
	
	private Scanner scan;
	
	private int rank;
	private String card;
	private String temp;
	
	private static Deck deck = new Deck();
	
	public Card() {
		card = deck.card();
		scan = new Scanner(card);
		temp = scan.next();
		if (temp.matches("Ace")) {
			rank = 11;
		} else if (temp.matches("Jack") || temp.matches("Queen") || temp.matches("King")) {
			rank = 10;
		} else {
			try {
			    rank = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println(card);
			}
		}
	}
	
	public void shuffle() {
		deck.shuffle();
	}
	
	public int getRank() {
		return rank;
	}
	
	public String toString() {
		return card;
	}
	
}
