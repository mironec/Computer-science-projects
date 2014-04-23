package cs.programs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
	private int accountNumber;
	private String password;
	private String firstName, secondName;
	private String personalNumber;
	private Date startDate;
	private String email;
	private String phone;
	private String address;
	private double balance;
	private String currency;
	
	public Account(int accountNumber, String password, String firstName, String secondName, String personalNumber, Date startDate,
			String email, String phone, String address, double balance, String currency) {
		this.accountNumber = accountNumber;
		if(password.length()<8){return;} this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.personalNumber = personalNumber;
		this.startDate = startDate;
		if(email.contains("@")&&email.contains(".")) this.email = email; else return;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
		this.currency = currency;
	}
	
	public void copyFrom(int accountNumber, String password, String firstName, String secondName, String personalNumber, Date startDate,
			String email, String phone, String address, double balance, String currency){
		this.accountNumber = accountNumber;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.personalNumber = personalNumber;
		this.startDate = startDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
		this.currency = currency;
	}
	
	public void copyFrom(Account a){
		copyFrom(a.getAccountNumber(),a.getPassword(),a.getFirstName(),a.getSecondName(),a.getPersonalNumber(),a.getStartDate(),a.getEmail(),a.getPhone(),a.getAddress(),a.getBalance(),a.getCurrency());
	}
	
	public void outputAllInfo(){
		IBIO.output("Account number: " + accountNumber);
		IBIO.output("Password: " + password);
		IBIO.output("First name: " + firstName);
		IBIO.output("Last name: " + secondName);
		IBIO.output("Personal number: " + personalNumber);
		IBIO.output("Start date: " + new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").format(startDate));
		IBIO.output("Email address: " + email);
		IBIO.output("Phone number: " + phone);
		IBIO.output("Address: " + address);
		IBIO.output("Balance: " + balance);
		IBIO.output("Currency: " + currency);
	}
	
	public void outputSomeInfo(){
		IBIO.output("Account number: " + accountNumber);
		IBIO.output("First name: " + firstName);
		IBIO.output("Last name: " + secondName);
		IBIO.output("Start date: " + new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").format(startDate));
		IBIO.output("Currency: " + currency);
	}
	
	public Account(int accountNumber, String password, String firstName, String secondName, String personalNumber, Date startDate,
			String email, String phone, String address, String currency) {
		this(accountNumber,password,firstName,secondName,personalNumber,startDate,email,phone,address,0.0D,currency);
	}
	
	public Account(int accountNumber, String password, String firstName, String secondName, String personalNumber,
			String email, String phone, String address, String currency) {
		this(accountNumber,password,firstName,secondName,personalNumber,new Date(),email,phone,address,0.0D,currency);
	}
	
	public Account(int accountNumber, String password, String firstName, String secondName, String personalNumber,
			String email, String phone, String address, double balance, String currency) {
		this(accountNumber,password,firstName,secondName,personalNumber,new Date(),email,phone,address,balance,currency);
	}
	
	public boolean withdraw(String password, double amount){
		if(password.equals(this.password) && amount > 0 && balance - amount > 0){
			balance -= amount;
			return true;
		}
		return false;
	}
	
	public boolean deposit (double amount){
		if(amount > 0)
			balance += amount;
		else
			return false;
		return true;
	}
	
	public boolean changePassword(String oldPass, String newPass){
		if(oldPass.equals(password)){
			password = newPass;
			return true;
		}
		else
			return false;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getCurrency() {
		return currency;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
