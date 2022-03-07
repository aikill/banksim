package bank;

import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class Customer implements Serializable{	
	int accountType;
	String firstName;
	String lastName;
	boolean joint;
	String username;
	String password;
	double balance;
	
	
	public Customer(String user, String pass, String firstname, String lastname) {
		this.accountType = 1;
		this.username = user;
		this.password = pass;
		this.firstName = firstname;
		this.lastName = lastname;	
		this.joint = false;
	}
	
	public Customer() {
		//default constructor 
	}

	public String getName(Customer cust) {
		String name = cust.firstName + " " + cust.lastName;
		return name;		
	}
	
	public double getBalance(Customer cust) { return cust.balance; }
	
	public void setBalance(double amount) { this.balance = amount; }
	
	public String getUserName(Customer cust) { return cust.username; }
	
	public String getPassword(Customer cust) { return cust.password; }
	
	public void userMenu(Customer cus, String username, String password, boolean active, Database db) {
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome " + getName(cus));
			System.out.println("Balance: " + getBalance(cus));
			System.out.println("Joint: " + joint);
			System.out.println("1: Initiate Withdrawal");
			System.out.println("2: Deposit Funds");
			System.out.println("3: Transfer Funds");
			System.out.println("4: Make account joint");
			System.out.println("5: Logout");
			String sel = in.nextLine();
			if (sel.equals("1")) {
				System.out.println("Enter an amount to withdraw: ");
				String amt = in.nextLine();
				try {
					Double.parseDouble(amt);
				} catch (Exception e) {
					System.out.println("Invalid Input!");
				}
				double amount = Double.parseDouble(amt);
				if (amount >= 0) withdraw(cus, amount, db);
				else System.out.println("Invalid Input!");
			} else if (sel.equals("2")) {
				System.out.println("Enter an amount to deposit: ");
				String amt = in.nextLine();
				try {
					Double.parseDouble(amt);
				} catch (Exception e) {
					System.out.println("Invalid Input!");
				}
				double amount = Double.parseDouble(amt);
				if (amount >= 0) deposit(cus, amount, db);
			} else if (sel.equals("3")) {
				transfer(db, username);
			} else if (sel.equals("4")) {
				System.out.println("Make account joint? (y/n)");
				if(in.nextLine().equals("y"))
					joint = true;
				else if (in.nextLine().equals("n"));
				else System.out.println("Invalid Input");
			} else if (sel.equals("5")) {
				active = false;
			} else System.out.println("Invalid Input");
			
		} while (active);
		db.reSerializeCustomers(username, cus);
		System.out.println("Logout Successful!");
		Menu main = new Menu();
		main.mainMenu();
	}
	
	//arithmetic issue (cleared?)
	public void withdraw(Customer cus, double amount, Database db) {
		double tmpBal = cus.getBalance(cus);
		if(amount <= tmpBal) tmpBal -= amount;
		//System.out.println(tmpBal);
		setBalance(tmpBal);
		System.out.println("New Balance: " + getBalance(cus));
		db.reSerializeCustomers();
	}
	
	//arithmetic issue (cleared?)
	public void deposit(Customer cus, double amount, Database db) {
		double tmpBal = cus.getBalance(cus);
		if (amount >= 0) tmpBal += amount;
		//System.out.println(tmpBal);
		setBalance(tmpBal);
		System.out.println("New Balance: " + getBalance(cus));
		db.reSerializeCustomers();
	}
	
	public void transfer(Database db, String user) {
		Scanner in = new Scanner(System.in);
		db.deserializeCustomersVerbose();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if (cus != null) {
			System.out.println("Enter the key (username) of a user to transfer money to");
			String user1 = in.nextLine();
			Customer cus1 = new Customer();
			cus1 = (Customer) db.getCustomerAccount(user1);
			if (cus1 != null) {
				System.out.println("Enter an amount to transfer: ");
				double amount = Double.parseDouble(in.nextLine());
				cus1.withdraw(cus, amount, db);
				cus.deposit(cus1, amount, db);
			} else System.out.println("Invalid Input");
		} else System.out.println("Invalid input!");	
	}
	
	public static void main(String[] args) {
		
	}
}

class CustomerAccount {
	public Object addAccount(String user, String pass, String fName, String lName) {
		//Scanner in = new Scanner(System.in);
		//System.out.println("New Account:");
		//System.out.println("Enter first name:");
		//String fName = in.nextLine();
		//System.out.println("Enter last name:");
		//String lName = in.nextLine();
		//System.out.println("Enter username: ");
		
		Customer cust = new Customer(user, pass, fName, lName);
		return cust;
		//in.close();
		
	}
}
