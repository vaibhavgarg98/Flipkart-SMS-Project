package com.flipcard.service;

import com.flipcard.DAO.CheckIdentity;
//This service class provides service to perform login operation .
public class LogInAuthentication {
	public static String authenticate(String emailId, String password) {
		CheckIdentity checkIdentity = new CheckIdentity();
		String role = checkIdentity.verifyCredentials(emailId, password);
		return role;
	}
}
