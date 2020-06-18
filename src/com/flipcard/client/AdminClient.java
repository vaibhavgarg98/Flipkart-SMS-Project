package com.flipcard.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.flipcard.bean.Admin;
import com.flipcard.bean.Course;
import com.flipcard.bean.Professor;
import com.flipcard.bean.Student;
import com.flipcard.bean.User;
import com.flipcard.constants.UserConstants;
import com.flipcard.service.AdminOperations;
import com.flipcard.service.AdminOperationsImpl;
//This Admin class enables Student to perform student operations.
public class AdminClient {
	//This logger object is used to write the output console. 
	private static Logger log = Logger.getLogger(AdminClient.class); 
	//Admin service object to perform student operations.
	public static AdminOperations operations = new AdminOperationsImpl();
	//This Scanner object is used to take input from the user.
	public static Scanner sc = new Scanner(System.in);
	@SuppressWarnings("unused")
	public static void main(String... args) {
		log.info("--Admin Portal--");
		String adminId = "ag@fk";
		//Get the admin id which is passed as an argument during login session.
		if(args.length>0)
			adminId = args[0];
		boolean run = true;
		//This loop displays the options user can perform inside the portal.
		while(run) {
			viewOptions();
			int option = sc.nextInt();
			try {
				switch(option) {
				case 0:
					run = false;
					break;
				case 1:
					addUserDetails();
					break;
				case 2:
					deleteUser();
					break;
				case 3:
					updateUser();
					break;
				case 4:
					viewUsers();
					break;
				case 5:	
					addCourseDetails();
					break;
				case 6:
					deleteCourse();
					break;
				case 7:
					updateCourseDetails();
					break;
				case 8:
					viewCourseCatalogue();
					break;
				default:
					log.error("Invalid Choice!");
				}
			}
			catch(InputMismatchException e) {
				log.error("Invalid Option!");
			}
		}
	}
	
	//method to view admin options
	private static void viewOptions() {
		log.info("Enter 0 to exit.");
		log.info("Enter 1 to add User.");
		log.info("Enter 2 to remove User.");
		log.info("Enter 3 to update User.");
		log.info("Enter 4 to view Users.");
		log.info("Enter 5 to add Course.");
		log.info("Enter 6 to remove Course.");
		log.info("Enter 7 to update Course.");
		log.info("Enter 8 to view Courses.");
	}
	
	//method to view course catalogue
	private static void viewCourseCatalogue() {
		// TODO Auto-generated method stub
		operations.viewCourses();
	}
	
	//method to perform update course operation
	private static void updateCourseDetails() {
		log.info("Course Id:");
		long id = sc.nextLong();
		log.info("New Couse Name:");
		String name = sc.next();
		log.info("New Course Description:");
		String descripton = sc.next();
		log.info("New Course Fees:");
		int courseFee = sc.nextInt();
		Course course = new Course(id, name, descripton, courseFee);
		operations.updateCourse(id, course);
	}
	
	//method to peform delete course operation
	private static void deleteCourse() {
		log.info("Course Id:");
		long id = sc.nextLong();
		operations.deleteCourse(id);
	}
	
	//method to add course to catalogue
	private static void addCourseDetails() {
		// TODO Auto-generated method stub
		log.info("Course id:");
		long id = sc.nextLong();
		log.info("Couse Name:");
		String name = sc.next();
		log.info("Course Description:");
		String descripton = sc.next();
		log.info("Course Fees:");
		int courseFee = sc.nextInt();
		Course course = new Course(id, name, descripton, courseFee);
		operations.addCourse(course);
	}
	
	//method to view all courses
	private static void viewUsers() {
		String role = null;
		log.info("Enter Role: 1. Admin 2. Professor 3. Student");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			role = UserConstants.ADMIN;break;
		case 2:
			role = UserConstants.PROFESSOR;break;			
		case 3:
			role = UserConstants.STUDENT; break;
		}
		if(role == null) {
			log.info("Error in input data!");
				return;
		}
		operations.viewUsers(role);
	}
	
	//method to perform update user details operation
	private static void updateUser() {
		log.info("Enter Email ID");
		String emailId = sc.next();
		log.info("New Name:");
		String name = sc.next();
		log.info("New Password:");
		String password = sc.next();
		log.info("New Gender:");
		String gender = sc.next();
		String role = null;
		User user = null;
		log.info("Enter old Role: 1. Admin 2. Professor 3. Student");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			user = new Admin(emailId, password, option, name, gender);
			role = UserConstants.ADMIN;break;
		case 2:
			user = new Professor(emailId, password, option, name, gender);
			role = UserConstants.PROFESSOR;break;	
		case 3:
			user = new Student(emailId, password, option, name, gender);
			role = UserConstants.STUDENT; break;
		}
		if(role == null) {
			log.info("Error in input data!");
				return;
		}
		operations.editUser(emailId, user, role);
	}
	
	//method to perform delete user details operation
	private static void deleteUser() {
		log.info("EmailId:");
		String emailId	= sc.next();
		String role = null;
		log.info("Enter Role: 1. Admin 2. Professor 3. Student");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			role = UserConstants.ADMIN;break;
		case 2:
			role = UserConstants.PROFESSOR;break;	
		case 3:
			role = UserConstants.STUDENT; break;
		}
		if(role == null) {
			log.info("Error in input data!");
				return;
		}
		operations.deleteUser(emailId, role);
	}
	
	//method to add user details operation
	private static void addUserDetails() {
		log.info("Name:");
		String name = sc.next();
		log.info("EmailID:");
		String emailId = sc.next();
		log.info("Password:");
		String password = sc.next();
		log.info("Gender:");
		String gender = sc.next();
		String role = null;
		User user = null;
		log.info("Enter Role: 1. Admin 2. Professor 3. Student");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			user = new Admin(emailId, password, option, name, gender);
			role = UserConstants.ADMIN;break;
		case 2:
			user = new Professor(emailId, password, option, name, gender);
			role = UserConstants.PROFESSOR;break;	
		case 3:
			user = new Student(emailId, password, option, name, gender);
			role = UserConstants.STUDENT; break;
		}
		if(role == null) {
			log.info("Error in input data!");
				return;
		}
		 
		operations.addUser(user, role);
	}
}
