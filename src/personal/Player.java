package personal;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Person {

	private int count = 0;
	
	private Scanner scan = new Scanner(System.in);
	
	public Player() {}
	
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
