package cs.programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cs.CSProgram;

public class Bank extends CSProgram{

	private ArrayList<Account> accs;
	
	public Bank(int id) {
		super("bank", id);
	}
	
	private void readFromFile(){
		if(!new File("Bank").exists()){
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader("Bank"));
			String s = "";
			while((s=br.readLine())!=null){
				if(s.trim().replaceAll(" ", "").equals("")) continue;
				String accArgs[] = s.split(";");
				createAccount(accArgs);
			}
			br.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private void writeToFile(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Bank"));
			for(Account acc : accs){
				bw.write(
						acc.getAccountNumber()+";"+
						acc.getPassword()+";"+
						acc.getFirstName()+";"+
						acc.getSecondName()+";"+
						acc.getPersonalNumber()+";"+
						new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").format(acc.getStartDate())+";"+
						acc.getEmail()+";"+
						acc.getPhone()+";"+
						acc.getAddress()+";"+
						acc.getBalance()+";"+
						acc.getCurrency()+"\n"
						);
			}
			bw.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	private void createAccount(String[] args){
		
		try {
			addAccount(new Account(
					Integer.parseInt(args[0]),
					args[1],
					args[2],
					args[3],
					args[4],
					(new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")).parse(args[5]),
					args[6],
					args[7],
					args[8],
					Double.parseDouble(args[9]),
					args[10]
					));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void lightStart(){
		accs = new ArrayList<Account>();
		readFromFile();
	}
	
	public void lightEnd(){
		writeToFile();
	}
	
	public void start() {
		accs = new ArrayList<Account>();
		
		readFromFile();
		Menu();
		writeToFile();
	}
	
	private void Menu(){
		String s = IBIO.inputString("Whattya gonna do?");
		if(s.trim().toLowerCase().startsWith("help")){
			IBIO.output("");
		}
		if(s.trim().toLowerCase().startsWith("create")||s.trim().toLowerCase().startsWith("new")){
			addAccount();
		}
		if(s.trim().toLowerCase().startsWith("find")){
			String info = IBIO.inputString("Enter some identifying info about the account: ");
			info = info.trim().toLowerCase();
			for(Account acc : accs){
				try{
				if(Integer.parseInt(info)==acc.getAccountNumber()){
					IBIO.output("Account found with the same account number (" + acc.getAccountNumber() + "): ");
					acc.outputSomeInfo();
				}
				} catch(NumberFormatException e) {}
				if(info.equals(acc.getFirstName().toLowerCase())||info.equals(acc.getSecondName().toLowerCase())){
					IBIO.output("Account found with the same name (" + acc.getFirstName() + " " + acc.getSecondName() + "):");
					acc.outputSomeInfo();
				}
			}
		}
		if(s.trim().toLowerCase().startsWith("list")){
			if(s.length()>5&&s.trim().toLowerCase().substring(5).equals("names")){
				ArrayList<Account> tried = new ArrayList<>();
				for(Account acc : accs){
					int i = 0;
					for(Account acc2 : tried){
						if(acc.getFirstName().compareToIgnoreCase(acc2.getFirstName())<0){
							tried.add(i, acc);
						}
						else{
							i++;
						}
					}
					tried.add(acc);
				}
				for(Account acc : tried){
					System.out.println("Account number " + acc.getAccountNumber() + ": " + acc.getFirstName() + " " + acc.getSecondName());
				}
			}
			else{
				for(Account acc : accs){
					System.out.println("Account number " + acc.getAccountNumber() + ": " + acc.getFirstName() + " " + acc.getSecondName());
				}
			}
		}
		if(s.trim().toLowerCase().startsWith("credit")){
			//findAccount(new Method(){depositAccount();});
			depositAccount();
		}
		if(s.trim().toLowerCase().startsWith("delete")){
			deleteAccount();
		}
		if(!(s.trim().toLowerCase().startsWith("end")||s.trim().toLowerCase().startsWith("quit"))){
			Menu();
		}
	}
	
	public ArrayList<Account> getAccounts(){
		return accs;
	}
	
	public Account addAccount(Account acc) throws Exception{
		for(Account test : accs){
			if(test.getAccountNumber() == acc.getAccountNumber()){
				throw new Exception("Account with the same account number already exists!");
			}
		}
		accs.add(acc);
		return acc;
	}
	
	/*private void findAccount(Method func){
		String info = IBIO.inputString("Enter some identifying info about the account: ");
		info = info.trim().toLowerCase();
		for(Account acc : accs){
			int id = -1;
			try{id=Integer.parseInt(info);} catch(NumberFormatException e) {}
			if(id==acc.getAccountNumber()){
				try {
					func.invoke(acc);
				} catch (Exception e) {e.printStackTrace();}
			}
			else if(info.equals(acc.getFirstName().toLowerCase())||info.equals(acc.getSecondName().toLowerCase())){
				try {
					func.invoke(acc);
				} catch (Exception e) {e.printStackTrace();}
			}
		}
	}*/
	
	private void depositAccount(){
		String info = IBIO.inputString("Enter some identifying info about the account: ");
		info = info.trim().toLowerCase();
		for(Account acc : accs){
			try{
			if(Integer.parseInt(info)==acc.getAccountNumber()){
				acc.deposit(IBIO.inputInt("Account with the account number " + acc.getAccountNumber() + " will be credited with: "));
			}
			} catch(NumberFormatException e) {}
			if(info.equals(acc.getFirstName().toLowerCase())||info.equals(acc.getSecondName().toLowerCase())){
				acc.deposit(IBIO.inputInt("Account with the name " + acc.getFirstName() + " " + acc.getSecondName() + " will be credited with: "));
			}
		}
	}
	
	public void deleteAccount(int id){
		for(Account a : new ArrayList<Account>(accs)){
			if(a.getAccountNumber() == id){
				accs.remove(a);
			}
		}
	}
	
	private void deleteAccount(){
		String info = IBIO.inputString("Enter some identifying info about the account: ");
		info = info.trim().toLowerCase();
		for(Account acc : new ArrayList<Account>(accs)){
			try{
			if(Integer.parseInt(info)==acc.getAccountNumber()){
				acc.outputSomeInfo();
				if(IBIO.inputString("Delete this account? (Y/N): ").equalsIgnoreCase("y")){
					accs.remove(acc);
				}
			}
			} catch(NumberFormatException e) {}
			if(info.equals(acc.getFirstName().toLowerCase())||info.equals(acc.getSecondName().toLowerCase())){
				acc.outputSomeInfo();
				if(IBIO.inputString("Delete this account? (Y/N): ").equalsIgnoreCase("y")){
					accs.remove(acc);
				}
			}
		}
	}
	
	public void addAccount() {
		try {
			addAccount(new Account(getFreeId(), IBIO.inputString("Enter password: "),
					IBIO.inputString("Enter first name: "), IBIO.inputString("Enter last name: "),
					IBIO.inputString("Enter personal number: "), new Date(), 
					IBIO.inputString("Enter e-mail: "), IBIO.inputString("Enter phone number: "),
					IBIO.inputString("Enter address: "),IBIO.inputString("Enter currency: ")));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public Account addAccount(String password, String fname, String lname, String perNum, String email, String phone, String add, String curr) {
		try {
			return addAccount(new Account(getFreeId(), password, fname, lname, perNum, new Date(), email, phone, add, curr));
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	private int getFreeId(){
		for(int i = 1;;i++){
			if(!accountExists(i)){return i;}
		}
	}
	
	private boolean accountExists(Account acc){
		return accs.contains(acc);
	}
	
	private boolean accountExists(int id){
		for(Account test : accs){
			if(test.getAccountNumber() == id) return true;
		}
		return false;
	}
	
	public Account getAccountFromId(int id){
		for(Account test : accs){
			if(test.getAccountNumber() == id)
				return test;
		}
		return null;
	}
	
	public void depositMoneyToAccount(Account acc, double amount) throws Exception{
		if(accountExists(acc))
			acc.deposit(amount);
		else
			throw new Exception("No such account.");
	}
	
	public void depositMoneyToAccount(int id, double amount) throws Exception{
		Account acc = getAccountFromId(id);
		if(accountExists(acc))
			acc.deposit(amount);
		else
			throw new Exception("No such account.");
	}
	
	public void withdrawMoneyFromAccount(Account acc, String pass, double amount) throws Exception{
		if(accountExists(acc))
			acc.withdraw(pass,amount);
		else
			throw new Exception("No such account.");
	}
	
	public void withdrawMoneyFromAccount(int id, String pass, double amount) throws Exception{
		Account acc = getAccountFromId(id);
		if(accountExists(acc))
			acc.withdraw(pass,amount);
		else
			throw new Exception("No such account.");
	}
	
	public static boolean checkFName(String s){
		return s.matches("[A-Za-z]+");
	}
	
	public static boolean checkSName(String s){
		return s.matches("[A-Za-z]+");
	}
	
	public static boolean checkPassword(String s){
		return s.length() >= 8 && s.matches("^.*\\d.*$") && s.matches("^.*[A-Za-z].*$") && s.matches("^.*\\W.*$");
	}
	
	public static boolean checkPerNum(String s){
		return s.matches("\\d{6,}");
	}
	
	public static boolean checkEmail(String s){
		return s.matches("\\w+@\\w+[.]\\w+");
	}
	
	public static boolean checkPhone(String s){
		return s.matches("(.*\\d.*){8}");
	}
	
	public static boolean checkAddress(String s){
		return s.matches("[A-Za-z0-9 ]+");
	}

	public void help() {
		
	}

}
