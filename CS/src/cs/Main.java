package cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public Main () {}
	
	public void start(){
		System.out.println("Type 'SHOW' to see a list of programs, or enter a program's name or id to start it.");
		System.out.println("You can also type 'HELP' or 'HELP program_name' to get help.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		while(true){
			try {
				s = br.readLine();
			} catch (IOException e) {e.printStackTrace();}
			if(s.trim().toLowerCase().equals("show")){
				for(CSProgram pro : CSProgram.programs){
					if(pro == null) continue;
					System.out.println(pro.getId() + ": " + pro.getName());
				}
			}
			if(s.trim().toLowerCase().equals("help")){
				System.out.println("Help is not complete... sorry.");
			}
			else if(s.trim().toLowerCase().startsWith("help")){
				String call = s.trim().toLowerCase().substring(5);
				for(CSProgram pro : CSProgram.programs){
					if(pro == null) continue;
					if(pro.amICalled(call)){pro.help(); break;}
				}
			}
			for(CSProgram pro : CSProgram.programs){
				if(pro == null) continue;
				if(pro.amICalled(s)){pro.start(); System.out.println("END OF MAIN"); return;}
			}
		}
	}

}
