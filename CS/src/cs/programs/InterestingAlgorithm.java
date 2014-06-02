package cs.programs;

import java.util.ArrayList;
import java.util.Arrays;

import cs.CSProgram;

public class InterestingAlgorithm extends CSProgram {

	public InterestingAlgorithm(int id) {
		super("InterestingAlgorithm", id);
	}

	@Override
	public void start() {
		int x = IBIO.inputInt("Number:");
		int n = IBIO.inputInt("Number of numbers:");
		for(int[] intu : split(x,n,1)){
			System.out.println(Arrays.toString(intu));
		}
	}

	@Override
	public void help() {

	}
	
	public static ArrayList<int[]> split (int x, int n, int min){
		ArrayList<int[]> arr = new ArrayList<int[]>();
		
		if(n == 1){arr.add(new int[]{x}); return arr;}
		
		for(int i = min;i<x/2+1;i++){
			for(int[] intu : split(x-i,n-1,i)){
				int[] ints = new int[n];
				ints[0]=i;
				for(int u=0;u<intu.length;u++){
					ints[u+1]=intu[u];
				}
				arr.add(ints);
			}
		}
		
		return arr;
	}

}
