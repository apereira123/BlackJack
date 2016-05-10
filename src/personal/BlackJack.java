package personal;

import java.util.Scanner;

public class BlackJack {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static int i = 0;
	public static int temp;
	public static String string = "y";
	
	public static Player player1 = new Player();
	public static Dealer dealer = new Dealer();
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to blackjack. Have fun.");
		System.out.println();
		
		System.out.println("Enter the amount of money you would like to play with.");
		player1.setCash(scan.nextInt());
		
		
		while (string.matches("y")) {
			bet1(player1);
			printCards(player1, 0);
			printTotal(player1, 0);
			split(player1);
			System.out.println("The dealer's hole card is the " + dealer.getCard(0) + ".");
			System.out.println();
			play(player1);
			dealer(player1);
			printCards(player1);
			System.out.println("The dealer had the " + dealer.getCards(0) + ".");
			System.out.println();
			winner(player1);
			reset(player1);
			System.out.println();
			System.out.println("If you would like to play again enter \"y\".");
			string = scan.next();
		}
		
		System.out.println("Thank you for playing.");
		scan.close();
		
	}
	
	public static void bet1(Player player) {
		System.out.println("Enter the amount of money you would like to bet");
		temp = scan.nextInt();
		if (temp <= player.getCash()) {
			player.setBet(0, temp);
			int t = player.getCash();
			player.setCash(t-temp);
		} else {
			player.setBet(0, player.getCash());
			player.setCash(0);
		}
		System.out.println();
	}
	
	public static void printCards(Player player, int n) {
		System.out.println("Your cards are the " + player.getCards(n) + ".");
	}
	
	public static void printTotal(Player player, int n) {
		System.out.println("Your total is " + player.totalScore(n) + ".");
		System.out.println();
	}
	
	public static void split(Player player) {
		string = "";
		while(i <= player.getCount() && player.notFour()) {
			if (player.isSplit(i)) {
				System.out.println("If you want to split enter \"s\".");
				string = scan.next();
				if (string.matches("s")) {
					player.split(i);
					for (int j = 0; j <= player.getCount(); j++) {
							System.out.println("Your cards are the " + player.getCards(j) + ".");
							System.out.println("Your total is " + player.totalScore(j) + ".");
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
	}
	
	public static void play(Player player) {
		for (int i = 0; i <= player.getCount(); i++) {
			if (player.getCash() > 0) {
				System.out.println("Enter the amount of money you would like to bet");
				temp = scan.nextInt();
				if (temp <= player.getCash()) {
					int t = player.getBet(i);
					player.setBet(i, t+temp);
					t = player.getCash();
					player.setCash(t-temp);
				} else {
					player.setBet(i, player.getCash());
					player.setCash(0);
				}
			}
			player.turn(i);
		}
	}
	
	public static void printCards(Player player) {
		for (int i = 0; i <= player.getCount(); i++) {
			System.out.println("You had the " + player.getCards(i) + ".");
		}
	}
	
	public static void dealer(Player player) {
		System.out.println("The dealer's cards are the " + dealer.getCards(0) + ".");
		System.out.println();
		dealer.turn(player.isPlaying(), player.isStand());
	}
	
	public static void winner(Player player) {
		for (int i = 0; i <= player1.getCount(); i++) {
			if (player.totalScore(i) > 21) {
				System.out.println("The dealer won.");
			} else if (dealer.totalScore(0) > 21) {
				int t1 = 2*player.getBet(i);
				System.out.println(t1);
				int t2 = player.getCash();
				System.out.println(t2);
				player.setCash(t1 + t2);	
				System.out.println("You won.");
			} else if (player.totalScore(i) == dealer.totalScore(0)) {
				if (player.isBlackJack(i) && dealer.isBlackJack(0)) {
					System.out.println("The dealer won.");
				} else if (player.isBlackJack(i)) {
					int t1 = 2*player.getBet(i);
					System.out.println(t1);
					int t2 = player.getCash();
					System.out.println(t2);
					player.setCash(t1 + t2);	
					System.out.println("You won.");
				} else {
					System.out.println("The dealer won.");
				}
			} else if (player.totalScore(i) > dealer.totalScore(0)) {
				int t1 = 2*player.getBet(i);
				int t2 = player.getCash();
				player.setCash(t1 + t2);	
				System.out.println("You won.");
			} else {
				System.out.println("The dealer won.");
			}	
		}
		System.out.println("You have " + player.getCash() + " remaining.");
	}
	
	public static void reset(Player player) {
		player.reset();
		dealer.reset();
		player.deal(player.getCount());
		dealer.deal();
		i = 0;
	}

}
