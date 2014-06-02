package cs;

import cs.programs.BMI;
import cs.programs.Bank;
import cs.programs.BankGUI;
import cs.programs.DayOfTheWeek;
import cs.programs.FirstGui;
import cs.programs.InterestingAlgorithm;
import cs.programs.InterfaceCreator;
import cs.programs.Lists;
import cs.programs.Merging;
import cs.programs.Pii;
import cs.programs.TestClass;
import cs.programs.cards.CardsProgram;
import cs.programs.graphs.Graphs;

public abstract class CSProgram {
	
	private String name;
	private int id;
	protected int i;
	
	public CSProgram(String name, int id) {
		this.name = name;
		this.id = id;
		programs[id]=this;
	}
	
	public boolean amICalled(String s){
		if(s.trim().toLowerCase().equals(name.trim().toLowerCase())){return true;}
		try{if(Integer.parseInt(s) == id){return true;}}
		catch(NumberFormatException e){}
		return false;
	}
	
	public abstract void start();
	public abstract void help();
	
	public static CSProgram programs[] = new CSProgram[256];
	
	public static final DayOfTheWeek dayOfTheWeek = new DayOfTheWeek(0);
	public static final Bank bank = new Bank(1);
	public static final Pii pii = new Pii(2);
	public static final FirstGui gui = new FirstGui(3);
	public static final BMI bmi = new BMI(4);
	public static final InterfaceCreator ic = new InterfaceCreator(5);
	public static final BankGUI bgui = new BankGUI(6);
	public static final Lists lists = new Lists(7);
	public static final CardsProgram cards = new CardsProgram(8);
	public static final Graphs graphs = new Graphs(9);
	public static final InterestingAlgorithm alg = new InterestingAlgorithm(10);
	public static final Merging merg = new Merging(11);
	public static final TestClass test = new TestClass(64);

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
}
