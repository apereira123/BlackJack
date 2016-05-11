package edu.exeter.cs;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Person {

	private ArrayList<Double> bet = new ArrayList<Double>(4);
	private double cash;
	private int count = 0;
	
	private Scanner scan = new Scanner(System.in);
	
	public Player() {
		for (int i = 0; i <= 3; i++) {
			bet.add(i, 0.);
		}
	}
	
	public void deal(int n) {
		hands = new ArrayList<Hand>(handsSize);
		for (int i=0; i < handsSize; i++) {
			hands.add(i,new Hand());
		}
		hands.get(n).deal();
	}
	
	public void split(int n) {
		Card temp = hands.get(n).card();
		hands.get(n).remove();
		hands.get(n).hit(0);
		hands.add(n+1, new Hand(temp));
		hands.get(n+1).hit(0);	
		count++;
	}
	
	public void turn(int n) {
		playing = true;
		stand = true;
		System.out.println("Your total is " + hands.get(n).totalScore() + ".");
		while (playing && stand && !hands.get(n).isBlackJack()) {
			System.out.println("Enter \"h\" to hit.");
			String s = scan.nextLine();
			if (s.matches("h")) {
				hands.get(n).hit();
				if (hands.get(n).totalScore() > 20) {
					playing = false;
				} 
				System.out.println("Your cards are the " + hands.get(n).getCards() + ".");
			} else {
				stand = false;
			}
			System.out.println("Your total is " + hands.get(n).totalScore() + ".");
			System.out.println();
		}
	}
	
	public double getCash() {
		return cash;
	}
	public void setCash(double n) {
		cash = n;
	}
	
	public double getBet(int n) {
		return bet.get(n);
	}
	public void setBet(int n, double m) {
		bet.set(n, m);
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean notFour() {
		if (count < 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public void reset() {
		handsSize = 1;
		count = 0;
		playing = true;
		stand = true;
	}
	
}
