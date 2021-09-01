package com.cognixia.jump.project;
//
//Mihir Patel


import java.io.Serializable;

public class Employees implements Serializable {
	
	private static final long serialVersionUID = 210;
	
	public enum Department{
		SALES,
		HR,
		IT,
		MARKETING
	}
	private String name;
	private int employeeId;
	private Department department;
	
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public int getId() {
		return employeeId;
	}
	public void setId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Department  getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void updateList() {
		System.out.println("(1) First Name\n(2) ID\n (3)  Department" );
		
	}
	@Override
	public String toString() {
		return "Employees [name=" + name + ", employeeId=" + employeeId + ", department=" + department + "]";
	}
	
	
}
