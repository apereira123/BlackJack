package edu.exeter.cs;

import java.util.Scanner;

public class BlackJack {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static int i = 0;
	public static double temp;
	public static int numPlayers;
	public static String string;
	
	public static String[] names;
	public static Player[] players;
	public static Dealer dealer = new Dealer();
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to blackjack. You can play with up to six people. Enter the number of people you want to play with below.");
		
		numPlayers = scan.nextInt();
		names = new String[numPlayers];
		players = new Player[numPlayers];
		
		for (int i = 0; i < numPlayers; i++) {
			names[i] = "Player " + (i+1);
			players[i] = new Player();
		}
		
		for (int i = 0; i < numPlayers; i++) {
			System.out.println(names[i] + ": Enter the amount of money you would like to play with.");
			players[i].setCash(scan.nextDouble());
		}
		
		string = "y";
		while (string.matches("y")) {
			for (int i = 0; i < numPlayers; i++) {
				bet(players[i], i);
			}
			System.out.println();
			for (int i = 0; i < numPlayers; i++) {
				printCards(players[i], 0, i);
				printTotal(players[i], 0, i);
			}
			for (int i = 0; i < numPlayers; i++) {
				split(players[i], i);
			}
			System.out.println("The dealer's hole card is the " + dealer.getCard(0) + ".");
			System.out.println();
			for (int i = 0; i < numPlayers; i++) {
				play(players[i], i);
			}
			dealer.turn();
			for (int i = 0; i < numPlayers; i++) {
				printCards(players[i], i);
			}
			System.out.println("The dealer had the " + dealer.getCards(0) + ".");
			System.out.println();
			for (int i = 0; i < numPlayers; i++) {
				winner(players[i], i);
			}
			for (int i = 0; i < numPlayers; i++) {
				reset(players[i]);
			}
			System.out.println("If you would like to play again enter \"y\".");
			string = scan.next();
			System.out.println();
		}
		
		System.out.println("Thank you for playing.");
		scan.close();
		
	}
	
	public static void bet(Player player, int n) {
		System.out.println(names[n] + ": Enter the amount of money you would like to bet");
		temp = scan.nextDouble();
		if (temp <= player.getCash()) {
			player.setBet(0, temp);
			double t = player.getCash();
			player.setCash(t-temp);
		} else {
			player.setBet(0, player.getCash());
			player.setCash(0);
		}
	}
	
	public static void printCards(Player player, int n, int m) {
		System.out.println(names[m] + ": Your cards are the " + player.getCards(n) + ".");
	}
	
	public static void printTotal(Player player, int n, int m) {
		System.out.println(names[m] + ": Your total is " + player.totalScore(n) + ".");
		System.out.println();
	}
	
	public static void split(Player player, int n) {
		string = "";
		while(i <= player.getCount() && player.notFour()) {
			if (player.isSplit(i)) {
				System.out.println(names[n] + ": If you want to split enter \"s\".");
				string = scan.next();
				if (string.matches("s")) {
					player.split(i);
					for (int j = 0; j <= player.getCount(); j++) {
							System.out.println(names[n] + ": Your cards are the " + player.getCards(j) + ".");
							System.out.println(names[n] + ": Your total is " + player.totalScore(j) + ".");
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
	
	public static void play(Player player, int n) {
		for (int i = 0; i <= player.getCount(); i++) {
			if (player.getCash() > 0) {
				System.out.println(names[n] + ": Enter the amount of money you would like to bet");
				temp = scan.nextDouble();
				if (temp <= player.getCash()) {
					double t = player.getBet(i);
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
	
	public static boolean blackJack(Player player) {
		for (int i = 0; i <= player.getCount(); i++) {
			if (player.isBlackJack(i)) {
				return true;
			}
		}
		return false;
	}
	
	public static void printCards(Player player, int n) {
		for (int i = 0; i <= player.getCount(); i++) {
			System.out.println(names[n] + ": You had the " + player.getCards(i) + ".");
		}
	}
	
	public static void winner(Player player, int n) {
		for (int i = 0; i <= player.getCount(); i++) {
			if (player.totalScore(i) > 21) {
				System.out.println(names[n] + ": The dealer won.");
			} else if (dealer.totalScore(0) > 21) {
				double t1 = 2*player.getBet(i);
				double t2 = player.getCash();
				player.setCash(t1 + t2);	
				System.out.println(names[n] + ": You won.");
			} else if (player.totalScore(i) == dealer.totalScore(0)) {
				if (player.isBlackJack(i) && dealer.isBlackJack(0)) {
					System.out.println(names[n] + ": The dealer won.");
				} else if (player.isBlackJack(i)) {
					double t1 = 2.5*player.getBet(i);
					double t2 = player.getCash();
					player.setCash(t1 + t2);	
					System.out.println(names[n] + ": You won.");
				} else {
					System.out.println(names[n] + ": The dealer won.");
				}
			} else if (player.totalScore(i) > dealer.totalScore(0)) {
				double t1 = 2*player.getBet(i);
				double t2 = player.getCash();
				player.setCash(t1 + t2);	
				System.out.println(names[n] + ": You won.");
			} else {
				System.out.println(names[n] + ": The dealer won.");
			}	
		}
		System.out.println(names[n] + ": You have " + player.getCash() + " remaining.");
		System.out.println();
	}
	
	public static void reset(Player player) {
		player.reset();
		dealer.reset();
		player.deal(player.getCount());
		dealer.deal();
		i = 0;
	}

}
