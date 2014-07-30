package cs.programs;

import java.util.ArrayList;

import cs.CSProgram;

public class Lists extends CSProgram {

	public Lists(int id) {
		super("lists", id);
	}

	public void start() {
		ArrayList<String> list = new ArrayList<String>();
		for(String s : System.getProperties().toString().substring(1).substring(0,System.getProperties().toString().length()-2).split(", ")){
			list.add(s);
		}
		for(String s : list){
			System.out.println(s);
		}
	}

	public void help() {
		
	}

}
