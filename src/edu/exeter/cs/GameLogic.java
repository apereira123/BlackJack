package edu.exeter.cs;

import java.util.Scanner;

public class GameLogic {
	
	static Scanner scan = new Scanner(System.in);
	static String string;
	private static Hand player = new Hand();
	private static Hand banker = new Hand();
	private static boolean playerPlaying;
	private static boolean playerStand;
	private static boolean bankerPlaying;
	private static boolean bankerStand;
	
	public static void game() {
		reset();
		player.deal();
		System.out.println("Your cards are the " + player.getCards() + ".");
		System.out.println("Your total is " + player.totalScore() + ".");
		System.out.println();
		banker.deal();
		System.out.println("The banker's card is the " + banker.getCard() + ".");
		System.out.println();
		while (playerPlaying && playerStand && !player.isBlackJack()) {
			playerTurn();
		}
		System.out.println("The banker's cards are the " + banker.getCards() + ".");
		System.out.println();
		while (playerPlaying && !player.isBlackJack() && bankerPlaying && bankerStand) {
			bankerTurn();
		}
		System.out.println("You had the " + player.getCards() + ".");
		System.out.println("The banker had the " + banker.getCards() + ".");
		System.out.println();
		winner();
	}
	
	private static void playerTurn() {
		System.out.println("Enter \"h\" to hit.");
		String s = scan.nextLine();
		if (s.matches("h")) {
			player.hit();
			if (player.totalScore() > 21) {
				playerPlaying = false;
			} 
			System.out.println("Your cards are the " + player.getCards() + ".");
		} else {
			playerStand = false;
		}
		System.out.println("Your total is " + player.totalScore() + ".");
		System.out.println();
	}
	
	private static void bankerTurn() {
		if (banker.totalScore() < 17 && bankerPlaying == true) {
			banker.hit();
			if (banker.totalScore() > 21) {
				bankerPlaying = false;
			}
			System.out.println("The banker hit.");
			System.out.println("The banker's cards are the " + banker.getCards() + ".");
			System.out.println();
		} else {
			bankerStand = false;
			System.out.println("The banker stood.");
			System.out.println();
		}
	}
	
	private static void winner() {
		if (playerPlaying == false) {
			System.out.println("The banker won.");
		} else if (bankerPlaying == false) {
			System.out.println("You won.");
		} else if (player.totalScore() == banker.totalScore()) {
			if (player.isBlackJack() && banker.isBlackJack()) {
				System.out.println("The banker won.");
			} else if (player.isBlackJack()) {
				System.out.println("You won.");
			} else {
				System.out.println("The banker won.");
			}
		} else if (player.totalScore() > banker.totalScore()) {
				System.out.println("You won.");
		} else {
			System.out.println("The banker won.");
		}
	}
	
	private static void reset() {
		playerPlaying = true;
		playerStand = true;
		bankerPlaying = true;
		bankerStand = true;
	}
	
//	private static void split() {
//		
//	}
	
}
