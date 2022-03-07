package bank;
import java.util.Scanner;

public class Main {
	
	
	
	public static void main(String[] args) {
		Database db = new Database();
		Customer blank = new Customer("blank", "123", "foo", "bar");
		db.addCustomerAccount("blank", blank);
		Admin blankAd = new Admin("blank", "123");
		db.addAdminAccount("blankAd", blankAd);
		Employee blankE = new Employee("blank", "123");
		db.addEmployeeAccount("blankE", blankE);
		Menu m = new Menu();
		m.mainMenu();
		
	}
}

class Menu {
//	Customer cus = new Customer("user", "pass", "foo", "bar");
//	String key = "user";
//	db.addCustomerAccount(key, cus);
	Database db = new Database();

	public void mainMenu() {
				System.out.println("1: Login");
		System.out.println("2: Register");
		System.out.println("Enter either 1 for login, or 2 for registration");
			
		Scanner in = new Scanner(System.in);
		String sel = in.nextLine();
		if (sel.equals("1")) {
			System.out.println("Loading database.....");
			db.deserializeCustomers();
			db.deserializeEmployees();
			db.deserializeAdmin();
			System.out.println("Loading Complete!");
			//in.close();
			login();
		} else if (sel.equals("2")) {
			//in.close();
			register();
		} else if (sel.equals("deserialize")) {
			db.deserializeCustomers();
			mainMenu();
		} else {
			try {
				//in.close();
				throw new IllegalArgumentException("Enter either '1' or '2' to proceed");
			} catch(IllegalArgumentException ex) {
				System.out.println("Invalid input, please enter a valid selection");
				//in.close();
				mainMenu();				
			}
		}
	}
	
	public void login() {
		Scanner in = new Scanner(System.in);
		String username;
		String password;
		System.out.println("Login");
		System.out.print("Enter your username: ");
		username = in.nextLine();
		System.out.print("\nEnter your password: ");
		password = in.nextLine();
		//in.close();
		System.out.println("Enter an account type: ");
		System.out.println("1: Customer");
		System.out.println("2: Employee");
		System.out.println("3: Administrator");
		System.out.println("Enter either '1', '2', or '3' to select your account type");
		String type = in.nextLine();
		if (type.equals("1")) {
			if(validate(username, password, Integer.parseInt(type))) {
				System.out.println("Login Successful!");
				Customer cus = (Customer) db.getCustomerAccount(username);
				cus.userMenu(cus, username, password, true, db);
			} else {
				System.out.println("Login Failure! Invalid Username/Password");
				mainMenu();
			}
		} else if (type.equals("2")) {
			if(validate(username, password, Integer.parseInt(type))) {
				System.out.println("Login Successful!");
				Employee emp = (Employee) db.getEmployeeAccount(username);
				emp.userMenu(emp, username, password, true, db);
			} else {
				System.out.println("Login Failure! Invalid Username/Password");
				mainMenu();
			}
		} else if (type.equals("3")) {
			if (validate(username, password, Integer.parseInt(type))) {
				System.out.println("Login Successful");
				Admin a = (Admin) db.getAdminAccount(username);
				a.userMenu(a, username, password, true, db);
			} else {
				System.out.println("Login Failure! Invalid Username/Password");
				mainMenu();
			}
		} else {
			try {
				//in.close();
				throw new IllegalArgumentException();
			} catch(IllegalArgumentException ex) {
				System.out.println("Invalid input, please enter a valid selection");
				mainMenu();	
			}
		}
	}
	public void register() {	
		Scanner in = new Scanner(System.in);
		String username;
		String password;
		String firstName;
		String lastName;
		String type;
		System.out.println("Register");
		System.out.print("Enter a new username: ");
		username = in.nextLine();
		System.out.print("\nEnter a new password: ");
		password = in.nextLine();
		System.out.println("Enter an account type: ");
		System.out.println("1: Customer");
		System.out.println("2: Employee");
		System.out.println("3: Administrator");
		System.out.println("Enter either '1', '2', or '3' to select the type of account to register");		
		type = in.nextLine();
		if (type.equals("1")) {
			System.out.println("Enter first name:");
			firstName = in.nextLine();
			System.out.println("Enter last name:");
			lastName = in.nextLine();
			//in.close();	
			//addCustomer(username, password, Integer.parseInt(type));
			//addCustomer(username, password,firstName, lastName);
			addApplication(username, password, firstName, lastName);
		} else if (type.equals("2")) {
			addEmployee(username, password);
		} else if (type.equals("3")) {
			addAdmin(username, password);
		} else {
			try {
				//in.close();
				throw new IllegalArgumentException();
			} catch(IllegalArgumentException ex) {
				System.out.println("Invalid input, please enter a valid selection");
				register();				
			}
		}
	}
		
	public boolean validate(String username, String password, int accType) {
		System.out.println("validating...");
		if (accType == 1) {
			return db.validateCustomerLogin(username, password);	
		} else if (accType == 2) {
			return db.validateEmployeeLogin(username, password);
		} else if (accType == 3) {
			return db.validateAdminLogin(username, password);
		} else {
			System.out.println("Something went wrong here please try again");
			return false;
		}
	}
	
	public void addCustomer(String username, String password, String firstName, String lastName) {
		System.out.println("Adding new user....");
		CustomerAccount cus = new CustomerAccount();
		db.addCustomerAccount(username, cus.addAccount(username, password, firstName, lastName));
		db.getCustomerAccount(username);
		mainMenu();
	}
	
	public void addApplication(String username, String password, String firstName, String lastName) {
		System.out.println("Adding new application....");
		CustomerAccount cus = new CustomerAccount();
		db.applyForCustomerAccount(username, cus.addAccount(username, password, firstName, lastName));
		db.getAppAccount(username);
		mainMenu();
	}
	
	
	public void addEmployee(String username, String password) {
		System.out.println("Adding new user....");
		EmployeeAccount emp = new EmployeeAccount();
		db.addEmployeeAccount(username, emp.addAccount(username, password));
		db.getEmployeeAccount(username);
		mainMenu();
	}
	
	public void addAdmin(String username, String password) {
		System.out.println("Adding new user....");
		AdminAccount a = new AdminAccount();
		db.addAdminAccount(username, a.addAccount(username, password));
		db.getAdminAccount(username);
		mainMenu();
	}
}