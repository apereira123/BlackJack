package personal;

public class Deck {

	private int suit = suits.length;
	private int rank = ranks.length;
	private int num = suit*rank;
	private int card = 0;
	private String string;
	
	private static String[] suits = {"Diamonds", "Spades", "Clubs", "Hearts"};
	private static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	private String[] deck = new String[num];
	
	public Deck() {
		for (int i = 0; i < rank; i++) {
			for (int j = 0; j < suit; j++) {
				deck[suit*i + j] = ranks[i] + " of " + suits[j];
			}
		}
		for (int i = 0; i < num; i++) {
			int r = i + (int) (Math.random() * (num - i));
			String t = deck[r];
			deck[r] = deck[i];
			deck[i] = t;
		}
	}
	
	public void shuffle() {
		card = 0;
		for (int i = 0; i < num; i++) {
			int r = i + (int) (Math.random() * (num - i));
			String t = deck[r];
			deck[r] = deck[i];
			deck[i] = t;
		}
	}
	
	public String card() {
		if (card < num) {
			string = deck[card];
			card++;
			return string;
			
		} else {
			return string = "The deck is empty.";
		}
	}
	
	public String getCard() {
		return string;
	}
	
	public void deck() {
		for (int i = 0; i < num; i++) {
		System.out.println(deck[i]);	
		}
	}
	
}
