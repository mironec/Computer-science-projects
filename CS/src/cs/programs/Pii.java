package cs.programs;

import java.util.Random;

import cs.CSProgram;

public class Pii extends CSProgram{

	private int numberOfStableDigits(double number, double destabil){
		String num = Double.toString(number);
		String numMax = Double.toString(number+destabil);
		String numMin = Double.toString(number-destabil);
		int stableDigits = 0;
		for(int i=0;true;i++){
			char numC, numMaxC;
			if(i<num.length()){numC=num.charAt(i);} else{numC='0';}
			if(i<numMax.length()){numMaxC=numMax.charAt(i);} else{numMaxC='0';}
			if(numC!=numMaxC){
				stableDigits=i;
				break;
			}
		}
		for(int i=0;true;i++){
			char numC, numMinC;
			if(i<num.length()){numC=num.charAt(i);} else{numC='0';}
			if(i<numMin.length()){numMinC=numMin.charAt(i);} else{numMinC='0';}
			if(numC!=numMinC){
				stableDigits=Math.min(stableDigits, i);
				break;
			}
		}
		return stableDigits;
	}
	
	public Pii(int id) {
		super("Pi", id);
	}

	public void start() {
		int n = IBIO.inputInt("Steps: ");
		//randomPi(n);
		progressionPi(n);
	}
	
	public void randomPi(int n){
		long ncircle=0;
		
		for(int i=0;i<n;i++){
			double x = new Random().nextDouble();
			double y = new Random().nextDouble();
			
			if(x*x+y*y<=1){
				ncircle++;
			}
		}
		
		IBIO.output((double)ncircle/n*4);
	}
	
	public void progressionPi(int n){
		double o = 0;
		int digits=0;
		
		for(int i=0;i<n;i++){
			int mult = -1;
			if(i%2==0) mult = 1;
			o+=mult * (double)4/(1+2*i);
			//System.out.println( i + ": "+numberOfStableDigits(o, (double)4/(1+2*i)) + ", " + Double.toString(o));
			
			if(numberOfStableDigits(o, (double)4/(1+2*i))>digits){
				digits++;
				System.out.print(Double.toString(o).charAt(digits-1));
			}
		}
		
		//IBIO.output(o);
	}

	public void help() {
		IBIO.output("Tries to calculate pi with different methods.");
	}

	
}
