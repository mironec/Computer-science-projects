package cs.programs.cards;

public class Card {
	byte color;
	byte value;
	
	public static final byte COLOR_HEARTS = 0;
	public static final byte COLOR_SPADES = 1;
	public static final byte COLOR_CLUBS = 2;
	public static final byte COLOR_DIAMONDS = 3;
	
	public static final String[] COLORS = {"H","S","C","D"};
	
	public static final byte VALUE_2 = 2;
	public static final byte VALUE_3 = 3;
	public static final byte VALUE_4 = 4;
	public static final byte VALUE_5 = 5;
	public static final byte VALUE_6 = 6;
	public static final byte VALUE_7 = 7;
	public static final byte VALUE_8 = 8;
	public static final byte VALUE_9 = 9;
	public static final byte VALUE_10 = 10;
	public static final byte VALUE_J = 11;
	public static final byte VALUE_Q = 12;
	public static final byte VALUE_K = 13;
	public static final byte VALUE_ACE = 14;
	public static final byte VALUE_JOKER = 15;
	
	public static final String[] VALUES = {"0","1","2","3","4","5","6","7","8","9","10","J","Q","K","A","JOKER"};
	
	public Card(byte value, byte color){
		this.value = value;
		this.color = color;
	}
	
	public String toString(){
		return COLORS[color] + VALUES[value];
	}
}
