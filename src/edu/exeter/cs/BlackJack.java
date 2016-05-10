package edu.exeter.cs;

public class BlackJack extends GameLogic{

	public static void main(String[] args) {
		
		System.out.println("Welcome to blackjack. Have fun.");
		System.out.println();
		
		do {
			game();
			System.out.println();
			System.out.println("If you would like to play again enter \"y\".");
			string = scan.nextLine();
		} while (string.matches("y"));
		
		System.out.println("Thank you for playing.");
		scan.close();
		
	}

}
