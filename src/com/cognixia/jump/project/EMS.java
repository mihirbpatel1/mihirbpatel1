package com.cognixia.jump.project;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.jump.project.Employees.Department;

// Mihir Patel



public class EMS {
	
	 static ArrayList<Employees> employeeList = new ArrayList<Employees>();
	 static File file = new File("employees.txt");

	public static void main(String[] args) throws Exception {
		boolean active = true;
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loadEmployees();
		}
		
		Scanner input = new Scanner(System.in);
		System.out.println("<====Welcome To The Employee Managment System====>\n");
		System.out.println("Please Choose from the Options below\n");
		while (active) {

			System.out.println("\nType 0 to Save and Exit");
			System.out.println("Type 1 to List All Employees");
			System.out.println("Type 2 to Add a New Employee");
			System.out.println("Type 3 to Remove a Employee");
			System.out.println("Type 4 to view Employees by Department");
			System.out.println("Type 5 to update");

			int options = -1;
			
		
			while (options < 0 || options > 5) {
				try {
					System.out.println("Enter options: \n");
					options = input.nextInt();
				} catch (Exception e) {
					System.out.println("Please Enter a Number form 0 to 5");					
					input.next();
				}
			}

			
			input.nextLine();

			
			switch (options) {
			case 0:
				
				active = false;
				System.out.println("<=== Saving..... Good Bye ===>");
				break;
			case 1:
				System.out.println("\n");
				for (Employees employee : employeeList) {
					System.out.println(employee);
				}
				break;

			case 2:
				addEmployee(input);
				break;
			case 3:
				removeEmployee(input);
				break;
			case 4:
				loadDepartments(input);
				break;
				
			case 5:
				for (Employees employee : employeeList) {
					System.out.println(employee);
				}
			
					System.out.println("Search an ID: ");
					Scanner sc = new Scanner(System.in);
					int id = sc.nextInt();
					
					int key =  checkOptions(id);
					
					updatEmployee(key, input);
					
					break;
			
			default:
				
				break;
			}
		}

		input.close();
		
		writeData(file);
	}

	
	public static int checkOptions(int id) {

		int option = 0;
		boolean userInput = false;
		
		while(userInput == false) {
			try {
				
				userInput = true;
				return option;		

			}
			catch(Exception e) {
				System.out.println("\n<======= ERROR: Please enter a Valid input ======>");
			}
			finally {

				
			}
		}
		return option;		
	}

	

	public static void loadEmployees() {
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
			
			while (true) {
				employeeList.add((Employees) reader.readObject());
			}
		} catch (EOFException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeData(File file) {
		File csv = new File("employees.csv");
	
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file, false))) {
			employeeList.forEach(emp -> {
				try {
					writer.writeObject(emp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csv, false))) {
			employeeList.forEach(emp -> {
				try {
					csvWriter.write(emp.toString() + ('\n'));;
				} catch (IOException error) {
					error.printStackTrace();
				}
			}); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private static void updatEmployee(int temp, Scanner input){
		@SuppressWarnings("resource")
		Scanner temp1 = new Scanner(System.in);
		
		Employees udateEmployee = employeeList.get(temp);
		
		System.out.println(udateEmployee.toString());
		
		System.out.println("What would you like to U ");
		udateEmployee.updateList();
		
		int option = temp1.nextInt();
		checkOptions(option);
		
		
		String updateString = "";
		int userID = 0;
		
		
		switch(option) {
		
			case(1):
				System.out.println("Enter New Name: ");

				updateString = temp1.next();
				
				udateEmployee.setName(updateString);
				System.out.println("SUCCESS NAME CHANGED");
				break;
				
			case(2):
				System.out.println("Enter new ID: ");
			    userID = temp1.nextInt();
			    
			    udateEmployee.setId(userID);
			    System.out.println("SUCCESS ID CHANGED");
				break;
			case(3):
				
				
				Department[] departList = Department.values();
				for (int i = 0; i < departList.length; i++) {
					System.out.println(i + " = " + departList[i]);
				}
				int departmentUpdate = -1;
				
				while (departmentUpdate < 0 || departmentUpdate >= 4) {
					try {
						System.out.println("\nSelect Department Number:");
						departmentUpdate = input.nextInt();
					
					} catch (Exception e) {
						System.out.println("Invalid input");
						
						input.next();
					}
				}
				udateEmployee.setDepartment(departList[departmentUpdate]);
				System.out.println("SUCCESS Deparment CHANGED");
			    
				break;	
				
				
		}
		
	}
	public static void addEmployee(Scanner input)  {
	
		Employees temp = new Employees();
		try {
		
			System.out.println("Enter Employee Name: ");
			temp.setName(input.nextLine());

			System.out.println("\nEnter your ID: ");
			temp.setId(input.nextInt());

			System.out.println("\nSelect Department Number:");
			
			Department[] departList = Department.values();
			for (int i = 0; i < departList.length; i++) {
				System.out.println(i + " = " + departList[i]);
			}
			int options = -1;
			
			while (options < 0 || options >= 4) {
				try {
					System.out.println("\nSelect Department Number:");
					options = input.nextInt();
				
				} catch (Exception e) {
					System.out.println("Invalid input");
					
					input.next();
				}
			}

			temp.setDepartment(departList[options]);

			
			employeeList.add(temp);

		} catch (Exception e) {
			System.out.println("e");
			
			input.next();
		}
	}

	public static void removeEmployee(Scanner input) {
		String name;
		boolean found = false;
		try {
			System.out.println("Enter Employee Name:");
			name = input.nextLine();
			System.out.println("Searching for " + name);
			
			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i).getName().equals(name)) {
					employeeList.remove(i);
					found = true;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("e");
		
			input.next();
		}

		if (found)
			System.out.println("<===== Employee Removed ======>");
		else
			System.out.println("<==== ERROR: Employee Not Found =====>");
	}
	
	private static void loadDepartments(Scanner input) {
		System.out.println("Select Department Number:");
		
		Department[] departList = Department.values();
		for (int i = 0; i < departList.length; i++) {
			System.out.println(i + " = " + departList[i]);
		}
		int options = -1;
		
		while (options < 0 || options >= departList.length) {
			try {
				options = input.nextInt();
			} catch (Exception e) {
				System.out.println("Please Enter a Vaild Number");
				
				input.next();
			}
		}
		
		Department depart = departList[options];
		
		employeeList.stream()
		.filter(emp -> emp.getDepartment() == depart)
		.forEach(System.out::println);
		
	}
	

}
