package com.flipcard.client;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.flipcard.service.CourseCatalogue;
import com.flipcard.service.ProfessorOperations;
import com.flipcard.service.ProfessorOperationsImpl;

public class ProfessorClient {
	private static Logger log = Logger.getLogger(ProfessorClient.class);
	private static Scanner sc = new Scanner(System.in);
	private static ProfessorOperations operations = new ProfessorOperationsImpl(); 
	public static void main(String... args) {
		log.info("Professor Portal");
		String professorId = "ng@fk";
		if(args.length>0)
			professorId = args[0];
		boolean run = true;
		while(run) {
			viewOptions();
			int option = sc.nextInt();
			switch(option) {
			case 0:
				run = false;
				break;
			case 1:
				viewCourseCatalogue();
				break;
			case 2:
				addCourseToTeachingList(professorId);
				break;
			case 3:
				dropCourseFromTeachingList(professorId);
				break;
			case 4:
				viewCoursesTeachByProfessor(professorId);
				break;
			case 5:
				uploadGrades();
				break;
			case 6:
				viewStudentsToTeach(professorId);
				break;
			default:
				log.error("Invalid Choice!");
			}
		}
	}
	private static void viewOptions() {
		// TODO Auto-generated method stub
		log.info("Enter 0 to exit.");
		log.info("Enter 1 to view all courses.");
		log.info("Enter 2 to add course.");
		log.info("Enter 3 to drop courses.");
		log.info("Enter 4 to view registered courses.");
		log.info("Enter 5 to add grades.");
		log.info("Enter 6 to vew students to teach");
	}
	private static void viewCourseCatalogue() {
		// TODO Auto-generated method stub
		CourseCatalogue.viewCourses();
	}
	private static void addCourseToTeachingList(String professorId) {
		// TODO Auto-generated method stub
		log.info("courseId:");
		int courseId = sc.nextInt();
		operations.addCourse(professorId, courseId);
	}
	private static void dropCourseFromTeachingList(String professorId) {
		// TODO Auto-generated method stub
		log.info("courseId:");
		int courseId = sc.nextInt();
		operations.dropCourse(professorId, courseId);
	}
	private static void viewCoursesTeachByProfessor(String professorId) {
		// TODO Auto-generated method stub
		operations.viewCoursesToTeach(professorId);
	}
	private static void uploadGrades() {
		// TODO Auto-generated method stub
		log.info("Student Id:");
		String studentId = sc.next();
		log.info("courseId:");
		int courseId = sc.nextInt();
		log.info("Grade:");
		String grade = sc.next();
		operations.addGrades(studentId, courseId, grade);
	}
	private static void viewStudentsToTeach(String professorId) {
		// TODO Auto-generated method stub
		operations.viewStudentsToTeach(professorId);
	}
}
