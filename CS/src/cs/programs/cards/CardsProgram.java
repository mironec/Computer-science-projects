package cs.programs.cards;

import cs.CSProgram;

public class CardsProgram extends CSProgram {

	public CardsProgram(int id) {
		super("CardsProgram", id);
	}

	@Override
	public void start() {
		Deck d = new Deck();
		System.out.println(d.toString());
		d.shuffle();
		System.out.println(d.toString());
	}

	@Override
	public void help() {

	}

}
