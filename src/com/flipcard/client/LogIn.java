package com.flipcard.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.flipcard.constants.UserConstants;
import com.flipcard.dateandtime.DateAndTime;
import com.flipcard.exception.LogInException;
import com.flipcard.service.LogInAuthentication;
//This Client class enables user to enter credentials to login inside the system
public class LogIn {
	//This logger object is used to write the output console. 
	private static Logger log = Logger.getLogger(LogIn.class);
	//This Scanner object is used to take input from the user.
	private static Scanner sc = new Scanner(System.in);
	//The main method to run he application.
	public static void main(String []args) {
		/*
		 * The user will be shown two options:
		 * Exit from the system by pressing 0.
		 * OR
		 * Enabling user to enter credential information(Email Id and Password) by pressing 1.
		 * If user enters any other key Invalid choice will be displayed and again user will be given two choices.
		 * */
		boolean run = true;
		while(run) {
			viewOptions();
			try {
				int option = sc.nextInt();
				switch(option) {
					case 0:
						//If user enters 0 terminate the while loop by making run = false.
						run = false;
						break;
					case 1:
						//Try to login into the system
						attemptToLogin();
						break;
					default:
						log.error("Invalid Choice!");
				}
			}
			//If user tries to enter non numeric characters exception will be thrown and will caught below.
			catch(InputMismatchException e){
				log.error("Invalid Choice!");
			}		
		}
	}

	//Method To view available options.
	private static void viewOptions() {
		log.info("Enter 0 to Exit.");
		log.info("Enter 1 to Login.");
	}
	//Method To handle login.
	private static void attemptToLogin() {
		String id = null;
		String password = null;
		String role = null;
		int chances = 3;
		//User will get 3 chances to login into the system.
		while(chances-->0){
			log.info("Email Id:");
			id = sc.next();
			log.info("Password:");
			password = sc.next();
			role = LogInAuthentication.authenticate(id, password);
			//If correct credentials are entered then role of user will be fetch from the database. 
			if(role == null) {
				//If role is not fetched, error and left chances will be shown.
				log.error("Invalid EmailId or password");
				log.info("Chances left: "+chances);
			}
			else
				break;
		}
		//After verifying credentials User will be on-board to its portal depending upon the role.
		onboard(id,role);
	}
	//This method opens portal for user based on its role.
	private static void onboard(String id, String role){
		try {
			//If no role is fetched then LogInexception will be thrown
			if(role == null)
				throw new LogInException();
			//Login Time of user will be displayed.
			DateAndTime.logIn(id, role);
			//Opening portal
			switch(role) {
				case UserConstants.STUDENT:
									StudentClient.main(id);
									break;
				case UserConstants.PROFESSOR:
									ProfessorClient.main(id);
									break;
				case UserConstants.ADMIN:
									AdminClient.main(id);
									break;
			}
			//As user exits from its portal logout time of user will be displayed.
			DateAndTime.logOut(id, role);
		}
		//LoginException caught.
		catch(LogInException e) {
			log.error(e.getMessage());
		}
	}
}
