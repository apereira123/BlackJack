package edu.exeter.cs;

public class Card {
	
	private int suit;
	private int num;
	private int value;
	private String strSuit;
	private String strNum;
	
	public Card() {
		num = (int) (Math.random()*13+1);
		suit = (int) (Math.random()*4+1);
		setNumString(num);
		setSuitString(suit);
	}
	
	public int getValue(){
		return value;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int n) {
		num = n;
		setNumString(num);
	}
	public String getNumString() {
		return strNum;
	}
	private void setNumString(int n) {
		if (n == 1) {
			strNum = "Ace";
			value = 11;
		} else if (n == 11) {
			strNum = "Jack";
			value = 10;
		} else if (n == 12) {
			strNum = "Queen";
			value = 10;
		} else if (n == 13) {
			strNum = "King";
			value = 10;
		} else {
			strNum = Integer.toString(n);
			value = n;
		}
	}
	
	public int getSuit() {
		return suit;
	}
	public void setSuit(int n) {
		suit = n;
		setSuitString(suit);
	}
	public String getSuitString() {
		return strSuit;
	}
	private void setSuitString(int n) {
		if (n == 1) {
			strSuit = "Diamonds";
		} if (n == 2) {
			strSuit = "Spades";
		} if (n == 3) {
			strSuit = "Hearts";
		} if (n == 4) {
			strSuit = "Clubs";
		}
	}
	
	public String toString() {
		return strNum + " of " + strSuit;
	}

}
