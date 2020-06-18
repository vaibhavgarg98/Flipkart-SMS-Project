package com.flipcard.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.flipcard.bean.RegistrationDetails;
import com.flipcard.service.CourseCatalogue;
import com.flipcard.service.StudentOperations;
import com.flipcard.service.StudentOperationsImpl;
//This Client class enables Student to perform student operations.
public class StudentClient {
	
	//This logger object is used to write the output console. 
	private static Logger log = Logger.getLogger(StudentClient.class);

	//This registration details object stores the registration details of student.
	private static RegistrationDetails registrationDetails = null;
	//This Scanner object is used to take input from the user.
	
	private static Scanner sc = new Scanner(System.in);
	//Student service object to perform student operations.
	
	private static StudentOperations operations = new StudentOperationsImpl();
	//Method to perform operations which has student id in its arguments.
	
	public static void main(String... args) {
		log.info("Student Portal");
		String studentId = "vg@fk";
		//Get the student id which is passed as an argument during login session.
		if(args.length>0)
			studentId = args[0];
		//Initializing details
		registrationDetails = operations.getRegistrationDetails(studentId);
		//This loop displays the options user can perform inside the portal.
		boolean run = true; 
		while(run) {
			//Various options will be displayed.
			viewOptions();
			try {
				int option = sc.nextInt();
				//Based on option selected student can perform operation by calling repective method.
				switch(option) {
				case 0:
					run = false;
					break;
				case 1:
					viewCatalogue();
					break;
				case 2:
					addCourseToregisteredList(studentId);
					break;
				case 3:
					dropCourseFromRegisteredList(studentId);
					break;
				case 4:
					showRegisteredCourses(studentId);
					break;
				case 5:
					doRegistration(studentId);
					break;
				case 6:
					viewReportCard(studentId);
					break;
				default:
					log.error("Invalid Choice!");
				}
			}
			//If student tries to enter non numeric characters exception will be thrown and will caught below.
			catch(InputMismatchException e){
				log.error("Invalid Choice!");
			}	
		}
	}
	
	//This method is used to display various operations student can perform.
	private static void viewOptions() {
		// TODO Auto-generated method stub
		log.info("Enter 0 to exit.");
		log.info("Enter 1 to view Course Catalogue.");
		log.info("Enter 2 to register course.");
		log.info("Enter 3 to unregister courses.");
		log.info("Enter 4 to see registered courses.");
		log.info("Enter 5 to register for semester.");
		log.info("Enter 6 to view Report Card.");
	}
	
	//This method is used to display course catalogue to student.  
	private static void viewCatalogue() {
		CourseCatalogue.viewCourses();
	}
	
	//This method is used to add course to registered courses of student only if student is not registered.
	private static void addCourseToregisteredList(String studentId) {
		if(registrationDetails!=null) {
			log.info("Registered Students cannot add or drop course!");
			return;
		}
		log.info("courseId:");
		int courseId = sc.nextInt();
		operations.addCourse(studentId, courseId);
	}
	
	//This method is used to remove course from registered courses of student only if student is not registered.
	private static void dropCourseFromRegisteredList(String studentId) {
		if(registrationDetails!=null) {
			log.info("Registered Students cannot add or drop course!");
			return;
		}
		log.info("courseId:");
		int courseId = sc.nextInt();
		operations.dropCourse(studentId, courseId);
	}
	
	//This method is used to show the courses registered by user.
	private static void showRegisteredCourses(String studentId) {
		// TODO Auto-generated method stub
		operations.showRegisteredCourses(studentId);
	}
	
	//This method is used to display report card of student.
	private static void viewReportCard(String studentId) {
		operations.viewReportCard(studentId);
	}
	
	//This method is used to register student if registration is pending and student have registered minimum 4 courses.
	private static void doRegistration(String studentId) {
		if(registrationDetails!=null) {
			log.info("You are already registered!");
			return;
		}
		
		int totalFees = operations.initiateRegistration(studentId);
		if(totalFees == -1) {
			log.info("At least 4 courses are needed for registration!");
			return;
		}
		//Registration requires payment of fees which can be done in following ways.
		log.info("Total payable amount for registrations: "+totalFees);
		log.info("Pay fees to complete Registration:(Select modes)");
		log.info("1. Scholarship");
		log.info("2. Net Banking");
		log.info("3. Debit/Credit Card");
		log.info("4. UPI");
		int pay = sc.nextInt();
		//If payment is successful.
		if(pay>=1 && pay <= 4)
			registrationDetails = operations.completeRegistration(studentId, pay);
		else
			log.info("Payment Not Suceessful!");
		//If registration fails show error.
		if(registrationDetails == null)
			log.info("Registration not completed!");
		else
			log.info("RegistrationId is: "+registrationDetails.getRegisterId());
	}
}
