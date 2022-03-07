package bank;

import java.util.Scanner;
import java.io.*;


public class Admin extends Employee implements Serializable{
	int accountType;
	String username;
	String password;
	
	public Admin(String user, String pass) {
		this.accountType = 3;
		this.username = user;
		this.password = pass;
	}
	
	public Admin() {
		//default constructor
	}
	
	public String getUserName(Admin a) { return a.username; }
	public String getPassword(Admin a) { return a.password; }
	
	public void userMenu(Admin a, String username, String password, boolean active, Database db) {
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome " + getUserName(a) + " (Admin Account)");
			System.out.println("1: Approve/Deny Accounts");
			System.out.println("2: Initiate a Withdrawal from an Account");
			System.out.println("3: Deposit to an Account");
			System.out.println("4: Transfer Money Between Accounts");
			System.out.println("5: Cancel an Account");
			System.out.println("6: View an Account");
			System.out.println("7: Logout");
			
			String sel = in.nextLine();
			int c = Integer.parseInt(sel);
			
			switch (c) {
				case 1: super.approveDeny(db);
						break;
				case 2: adminWithdrawal(db);
						break;
				case 3: adminDeposit(db);
						break;
				case 4: adminTransfer(db);
						break;
				case 5: cancel(db);
						break;
				case 6: super.viewAccount(db);
						break;
				case 7: active = false;
						break;
			}	
		} while (active);
		db.reSerializeCustomers();
		db.reSerializeAdmins();
		System.out.println("Logout Successful!");
		Menu main = new Menu();
		main.mainMenu();
	}	
	
	public void adminWithdrawal(Database db) {
		Scanner in = new Scanner(System.in);
		db.deserializeCustomersVerbose();
		System.out.println("Enter the key (username) of a user to initiate withdrawal process");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if (cus != null) {
			System.out.println("Enter an amount to withdraw from account: ");
			double amount = Double.parseDouble(in.nextLine());
			cus.withdraw(cus, amount, db);			
		} else System.out.println("Invalid input!");
	}
	
	public void adminDeposit(Database db) {
		Scanner in = new Scanner(System.in);
		db.deserializeCustomersVerbose();
		System.out.println("Enter the key (username) of a user to initiate deposit process");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if (cus != null) {
			System.out.println("Enter an amount to deposit to account: ");
			double amount = Double.parseDouble(in.nextLine());
			cus.deposit(cus, amount, db);			
		} else System.out.println("Invalid input!");		
	}
	
	public void adminTransfer(Database db) {
		Scanner in = new Scanner(System.in);
		db.deserializeCustomersVerbose();
		System.out.println("Enter the key (username) of a user to transfer money to");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if (cus != null) {
			System.out.println("Enter the key (username) of a user to transfer money from");
			String user1 = in.nextLine();
			Customer cus1 = new Customer();
			cus1 = (Customer) db.getCustomerAccount(user1);
			if (cus1 != null) {
				System.out.println("Enter an amount to transfer: ");
				double amount = Double.parseDouble(in.nextLine());
				cus.deposit(cus, amount, db);
				cus1.withdraw(cus1, amount, db);
			} else System.out.println("Invalid Input");
		} else System.out.println("Invalid input!");	
	}
	
	public void cancel(Database db) {
		Scanner in = new Scanner(System.in);
		db.deserializeCustomersVerbose();
		System.out.println("Enter the key (username) of a user account to cancel");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if (cus != null) {
			System.out.println("Are you sure you want to cancel this account (y/n)");
			if(in.nextLine().equals("y")) db.deleteCustomerAccount(user, cus);
			else if (in.nextLine().equals("n")) return;
			else System.out.println("Invalid Input!");
		} else System.out.println("Invalid input!");
	}
}

class AdminAccount {
	public Object addAccount(String user, String pass) {
		Admin a = new Admin(user, pass);
		return a;
	}
}
