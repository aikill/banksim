package bank;

import java.util.Scanner;
//import java.util.HashMap;
import java.io.*;

public class Employee implements Serializable{
	int accountType;
	String username;
	String password;
	
	public Employee(String user, String pass) {
		this.accountType = 2;
		this.username = user;
		this.password = pass;
	}
	
	public Employee () {
		//default constructor
	}
	
	public String getUserName(Employee emp) { return emp.username; }
	public String getPassword(Employee emp) { return emp.password; }
	
	
	public void userMenu(Employee emp, String username, String password, boolean active, Database db) {
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome " + getUserName(emp) + " (Employee Account)");	
			System.out.println("1: View Account Information");
			System.out.println("2: Approve/Deny Applications");
			System.out.println("3: Logout");
			String sel = in.nextLine();
			if (sel.equals("1")) {
				viewAccount(db);
			} else if (sel.equals("2")) {
				//System.out.println("pending implementation");
				approveDeny(db);
			} else if (sel.equals("3")) {
				active = false;
			}
			
		} while (active);
		db.reSerializeCustomers();
		db.reserializeEmployees();
		System.out.println("Logout Successful!");
		Menu main = new Menu();
		main.mainMenu();
	}

	public void viewAccount(Database db) {
		Scanner in = new Scanner(System.in);
		System.out.println("Accounts:");
		db.deserializeCustomersVerbose();
		System.out.println("Enter the key (username) of a user to view: ");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getCustomerAccount(user);
		if(cus != null) {
			System.out.println("Customer information: ");
			System.out.println("Customer Name: " + cus.getName(cus));
			System.out.println("Username: " + cus.getUserName(cus));
			System.out.println("Password: " + cus.getPassword(cus));
			if (cus.joint == true) System.out.println("This is a joint account");
			System.out.println("Balance: " + cus.getBalance(cus));
		} else System.out.println("Invalid input");
	}
	
	public void approveDeny(Database db) {
		Scanner in = new Scanner(System.in);
		System.out.println("Accounts:");
		db.deserializeAppsVerbose();
		System.out.println("Enter the key (username) of a application to approve/deny: ");
		String user = in.nextLine();
		Customer cus = new Customer();
		cus = (Customer) db.getAppAccount(user);
		if(cus != null) {
			System.out.println("Approve/Deny application for " + user + "?");
			if (in.nextLine().equals("Approve")) {
				db.processApproval(user, cus);
				db.deleteApplication(user, cus);
				System.out.println("Account application for " + user + " approved");
			} else if (in.nextLine().equals("Deny")) {
				db.deleteApplication(user, cus);
				System.out.println("Account application for " + user + " denied");
			} else System.out.println("Invalid Input");
		} else System.out.println("Invalid input");
	}
	

}

class EmployeeAccount {
	public Object addAccount(String user, String pass) {
		Employee emp = new Employee(user, pass);
		return emp;
	}
}
