package com.flipcard.bean;

public class Student extends User{
	private String name;
	private String gender;
	public Student() {
		
	}
	public Student(String emailId, String name, String gender) {
		this.setEmailId(emailId);
		this.setName(name);
		this.setGender(gender);
	}
	public Student(String emailId, String password, int roleId, String name, String gender) {
		super(emailId, password, roleId);
		this.setName(name);
		this.setGender(gender);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}	

}
