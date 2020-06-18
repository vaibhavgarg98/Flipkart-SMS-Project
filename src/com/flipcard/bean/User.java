package com.flipcard.bean;

public class User {

	private String emailId;
	private String password;
	private int roleId;
	public User() {
		
	}	
	public User(String emailId, String password, int roleId) {
		this.setEmailId(emailId);
		this.setPassword(password);
		this.setRoleId(roleId);
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
