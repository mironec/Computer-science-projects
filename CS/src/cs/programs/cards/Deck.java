package cs.programs.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<Card> cards;
	
	public Deck(){
		cards = new ArrayList<Card>();
		for(byte i=2;i<16;i++){
			for(byte c=0;c<4;c++)
				cards.add(new Card(i,c));
		}
	}
	
	public Deck shuffle(){
		Collections.shuffle(cards);
		return this;
	}
	
	public String toString(){
		String s = "";
		for(Card c : cards)
			s+=c.toString()+"\n";
		return s;
	}
}
