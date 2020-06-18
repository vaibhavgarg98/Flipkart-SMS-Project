package com.flipcard.exception;

@SuppressWarnings("serial")
public class LogInException extends Exception{
	public String getMessage() {
		return "Unauthorized User!";
	}

}
