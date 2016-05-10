package personal;

import java.util.Scanner;

public class GameLogic {
	
	static Scanner scan = new Scanner(System.in);
	private static int i = 0;
	static String string = "";
	private static Player player1 = new Player();
	private static Dealer dealer = new Dealer();
	
	public static void game() {
		System.out.println("Your cards are the " + player1.getCards(player1.getCount()) + ".");
		System.out.println("Your total is " + player1.totalScore(player1.getCount()) + ".");
		System.out.println();
		while(i <= player1.getCount() && player1.notFour()) {
			if (player1.isSplit(i)) {
				System.out.println("If you want to split enter \"s\".");
				string = scan.nextLine();
				if (string.matches("s")) {
					player1.split(i);
					for (int j = 0; j <= player1.getCount(); j++) {
							System.out.println("Your cards are the " + player1.getCards(j) + ".");
							System.out.println("Your total is " + player1.totalScore(j) + ".");
							System.out.println();
					}
				}
			}
			if (string.matches("s")) {
				i = 0;
				string = "";
			} else {
				i++;
			}
		}
		System.out.println("The dealer's card is the " + dealer.getCard(0) + ".");
		System.out.println();
		for (int i = 0; i <= player1.getCount(); i++) {
			player1.turn(i);
		}
		System.out.println("The dealer's cards are the " + dealer.getCards(0) + ".");
		System.out.println();
		dealer.turn(false, true);
		for (int i = 0; i <= player1.getCount(); i++) {
			System.out.println("You had the " + player1.getCards(i) + ".");
		}
		System.out.println("The dealer had the " + dealer.getCards(0) + ".");
		System.out.println();
		winner();
		player1.reset();
		dealer.reset();
		player1.deal(player1.getCount());
		dealer.deal();
	}
	
//	private static void count() {
//		
//	}
	
	private static void winner() {
		for (int i = 0; i <= player1.getCount(); i++) {
			if (player1.totalScore(i) > 21) {
				System.out.println("The dealer won.");
			} else if (dealer.totalScore(0) > 21) {
				System.out.println("You won.");
			} else if (player1.totalScore(i) == dealer.totalScore(0)) {
				if (player1.isBlackJack(i) && dealer.isBlackJack(0)) {
					System.out.println("The dealer won.");
				} else if (player1.isBlackJack(i)) {
					System.out.println("You won.");
				} else {
					System.out.println("The dealer won.");
				}
			} else if (player1.totalScore(i) > dealer.totalScore(0)) {
					System.out.println("You won.");
			} else {
				System.out.println("The dealer won.");
			}	
		}
	}
	
}
