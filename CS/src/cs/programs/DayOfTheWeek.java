package cs.programs;

import java.util.Calendar;
import java.util.Locale;

import cs.CSProgram;

public class DayOfTheWeek extends CSProgram {

	public static final String name = "Day of the week";
	public static final int id = 0;
	
	public DayOfTheWeek(String name, int id) {
		super(name, id);
	}
	
	public DayOfTheWeek(int id) {
		super(name, id);
	}
	
	public DayOfTheWeek(String name) {
		super(name, id);
	}
	
	public DayOfTheWeek() {
		super(name, id);
	}

	public void start() {
		int d=1,m=1,y=1900;
		
		d = IBIO.inputInt("Enter a day    (DD): ");
		m = IBIO.inputInt("Enter a month  (MM): ");
		y = IBIO.inputInt("Enter a year (YYYY): ");
		
		Calendar c = Calendar.getInstance();
		c.set(y, m-1, d);
		System.out.println("The day of the week for that date is: " + c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
	}
	
	public void help(){
		IBIO.output("This program lets you enter a date and then tells you what day of the week corresponds to that date.");
	}

}
