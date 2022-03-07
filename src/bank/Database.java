package bank;

import java.io.*;
import java.util.*;

public class Database implements Serializable{
	
	static HashMap<String, Object> customerAccounts = new HashMap<String, Object>();
	static HashMap<String, Object> appliedFor = new HashMap<String, Object>();
	static HashMap<String, Object> employeeAccounts = new HashMap<String, Object>();
	static HashMap<String, Object> adminAccounts = new HashMap<String, Object>();
	
	
	String customerFile = "customers.ser";
	String employeeFile = "employees.ser";
	String appFile = "applications.ser";
	String adminFile = "admins.ser";
	
	public void addCustomerAccount(String key, Object cus) {
		customerAccounts.put(key, cus);
		try {
			FileOutputStream file = new FileOutputStream(customerFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(customerAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
			Customer cus1 = new Customer();
			cus1 = (Customer) getCustomerAccount(key);
			System.out.println("New Account created: " + cus1.getUserName(cus1) + " for " + cus1.getName(cus1));	
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
			ex.printStackTrace();
		}
		//reSerializeCustomers();
	}
	
	public void deleteCustomerAccount(String key, Object cus) {
		customerAccounts.remove(key);
		reSerializeCustomers();
	}
	
	public void deleteApplication(String key, Object cus) {
		appliedFor.remove(key);
		reserializeApplications();
	}
	
	public void applyForCustomerAccount(String key, Object cus) {
		appliedFor.put(key, cus);
		try {
			FileOutputStream file = new FileOutputStream(appFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(appliedFor);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
			Customer cus1 = new Customer();
			cus1 = (Customer) getAppAccount(key);
			System.out.println("New Account created: " + cus1.getUserName(cus1) + " for " + cus1.getName(cus1));	
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
			ex.printStackTrace();
		}
	}
	
	public void processApproval(String key, Object app) {
		addCustomerAccount(key, app);
		deleteApplication(key, app);
		reSerializeCustomers();
		reserializeApplications();
	}
	
	public void addEmployeeAccount(String key, Object emp) {
		employeeAccounts.put(key, emp);
		try {
			FileOutputStream file = new FileOutputStream(employeeFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(employeeAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
			Employee emp1 = new Employee();
			emp1 = (Employee) getEmployeeAccount(key);
			System.out.println("New Employee Account created: " + emp1.getUserName(emp1));	
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");			
		}
	}	
	
	public void addAdminAccount(String key, Object a) {
		adminAccounts.put(key, a);
		try {
			FileOutputStream file = new FileOutputStream(adminFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(adminAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
			Admin a1 = new Admin();
			a1 = (Admin) getAdminAccount(key);
			System.out.println("New admin account created " + a1.getUserName(a1));
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reSerializeAdmins(String key, Object a) {
		try {
			FileOutputStream file = new FileOutputStream(adminFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			adminAccounts.put(key, a);
			out.writeObject(adminAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reSerializeAdmins() {
		try {
			FileOutputStream file = new FileOutputStream(adminFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			//adminAccounts.put(key, a);
			out.writeObject(adminAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reSerializeCustomers(String key, Object cus) {
		try {
			FileOutputStream file = new FileOutputStream(customerFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			customerAccounts.put(key, cus);
			out.writeObject(customerAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reSerializeCustomers() {
		try {
			FileOutputStream file = new FileOutputStream(customerFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			//customerAccounts.put(key, cus);
			out.writeObject(customerAccounts);
			out.close();
			file.close();
			System.out.println("Data Saved Successfully");
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reserializeEmployees(String key, Object emp) {
		try {
			FileOutputStream file = new FileOutputStream(employeeFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			employeeAccounts.put(key, emp);
			out.writeObject(employeeAccounts);
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reserializeEmployees() {
		try {
			FileOutputStream file = new FileOutputStream(employeeFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			//employeeAccounts.put(key, emp);
			out.writeObject(employeeAccounts);
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reserializeApplications(String key, Object emp) {
		try {
			FileOutputStream file = new FileOutputStream(appFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			appliedFor.put(key, emp);
			out.writeObject(appliedFor);
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public void reserializeApplications() {
		try {
			FileOutputStream file = new FileOutputStream(appFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			//employeeAccounts.put(key, emp);
			out.writeObject(appliedFor);
			out.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Data Serialization Failure");
		}
	}
	
	public Object getAdminAccount(String key) {	
		Admin a = new Admin();
		a = (Admin) adminAccounts.get(key);
		return a;
	}
	
	public boolean validateAdminLogin(String user, String pass) {	
		Admin a = new Admin();
		a = (Admin) getAdminAccount(user);
		if(a != null && a.getPassword(a).equals(pass))
			if (a.getPassword(a).equals(pass)) return true; 
		return false;
	}
	
	public Object getAppAccount(String key) {	
		Customer cus = new Customer();
		cus = (Customer) appliedFor.get(key);
		return cus;
	}
	
	public Object getCustomerAccount(String key) {	
		Customer cus = new Customer();
		cus = (Customer) customerAccounts.get(key);
		return cus;
	}
	
	public boolean validateCustomerLogin(String user, String pass) {	
		Customer cus = new Customer();
		cus = (Customer) getCustomerAccount(user);
		if(cus != null && cus.getPassword(cus).equals(pass))
			if (cus.getPassword(cus).equals(pass)) return true; 
		return false;
	}
	
	public Object getEmployeeAccount(String key) {	
		Employee emp = new Employee();
		emp = (Employee) employeeAccounts.get(key);
		return emp;
	}
	
	public boolean validateEmployeeLogin(String user, String pass) {	
		Employee emp = new Employee();
		emp = (Employee) getEmployeeAccount(user);
		if(emp != null && emp.getPassword(emp).equals(pass))
			if (emp.getPassword(emp).equals(pass)) return true; 
		return false;
	}
	
	public void deserializeAdmin() {
		System.out.println("Deserializing Admins....");
		adminAccounts = null;
		try {
			FileInputStream file = new FileInputStream(adminFile);
			ObjectInputStream in = new ObjectInputStream(file);
			adminAccounts = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
	}
	
	public void deserializeCustomers() {
		System.out.println("Deserializing Customers....");
		customerAccounts = null;
		try {
			FileInputStream file = new FileInputStream(customerFile);
			ObjectInputStream in = new ObjectInputStream(file);
			customerAccounts = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
//		Set set = customerAccounts.entrySet();
//		Iterator iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry cAentry = (Map.Entry) iterator.next();
//			System.out.print("key " + cAentry.getKey() + " & Value: ");
//			System.out.println(cAentry.getValue());
//		}	
	}
	
	public void deserializeApps() {
		System.out.println("Deserializing Applications....");
		adminAccounts = null;
		try {
			FileInputStream file = new FileInputStream(appFile);
			ObjectInputStream in = new ObjectInputStream(file);
			appliedFor = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
	}
	
	public void deserializeAppsVerbose() {
		System.out.println("Deserializing Applications....");
		adminAccounts = null;
		try {
			FileInputStream file = new FileInputStream(appFile);
			ObjectInputStream in = new ObjectInputStream(file);
			appliedFor = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
		Set set = appliedFor.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry cAentry = (Map.Entry) iterator.next();
			System.out.print("key " + cAentry.getKey() + " & Value: ");
			System.out.println(cAentry.getValue());
		}
	}
	
	public void deserializeEmployees() {
		System.out.println("Deserializing Employees....");
		employeeAccounts = null;
		try {
			FileInputStream file = new FileInputStream(employeeFile);
			ObjectInputStream in = new ObjectInputStream(file);
			employeeAccounts = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
	}
	
	public void deserializeCustomersVerbose() {
		System.out.println("Deserializing....");
		customerAccounts = null;
		try {
			FileInputStream file = new FileInputStream(customerFile);
			ObjectInputStream in = new ObjectInputStream(file);
			customerAccounts = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (ClassNotFoundException cex) {
			System.out.println("Class not found");
			cex.printStackTrace();
			return;
		}
		System.out.println("Deserialization Successful");
		Set set = customerAccounts.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry cAentry = (Map.Entry) iterator.next();
			System.out.print("key " + cAentry.getKey() + " & Value: ");
			System.out.println(cAentry.getValue());
		}
		
		
	}	
}
