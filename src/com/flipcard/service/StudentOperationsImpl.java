package com.flipcard.service;

import java.util.List;


import org.apache.log4j.Logger;

import com.flipcard.DAO.RegisteredCoursesDaoImpl;
import com.flipcard.DAO.RegistrationDetailsDao;
import com.flipcard.DAO.RegistrationDetailsDaoImpl;
import com.flipcard.bean.Course;
import com.flipcard.bean.RegistrationDetails;
import com.flipcard.bean.Result;
//Implementer class for StudentOperations
public class StudentOperationsImpl implements StudentOperations{
	//Get DAO object for performing Student level operations
	private RegistrationDetailsDao register = new RegistrationDetailsDaoImpl();
	private RegisteredCoursesDaoImpl registerCourse = new RegisteredCoursesDaoImpl();
	//Logger object to write on console. 
	private static Logger log = Logger.getLogger(StudentOperationsImpl.class);
	
	//Method to perform unregister course by student client.
	@Override
	public void addCourse(String studentId, long courseId) {
		try {
			if(studentId == null)
				throw new NullPointerException();
			if(registerCourse.addEntry(studentId, courseId))
			log.info("Added to registered courses!");
		else
			log.error("Cannot be added to registered courses!");
		}
		catch(NullPointerException e) {
			log.error("studentID cannot be null!");
		}
	}
	
	//Method to perform unregister course by student client.
	@Override
	public void dropCourse(String studentId, long courseId) {
		try {
			if(studentId == null)
				throw new NullPointerException();
			if(registerCourse.dropEntry(studentId, courseId))
				log.info("Course removed from registered list!");
			else
				log.error("Course not present!");
		}
		catch(NullPointerException e) {
			log.error("studentID cannot be null!");
		}
	}

	//Method to view Registered Courses List.
	@Override
	public void showRegisteredCourses(String studentId) {
		try {
			if(studentId == null)
				throw new NullPointerException();
			List<Course> registeredCourses = registerCourse.getAllCourses(studentId);
			if(registeredCourses.isEmpty()) {
				log.info("No Registered Courses!");
				return;
			}
			log.info("Registered Courses:");
			log.info(String.format("%-10s %-15s %-10s","ID","NAME","DESCRIPTION"));
			registeredCourses.forEach(course->log.info(String.format("%-10s %-15s %-10s",course.getId(),course.getName(),course.getDescription())));
			log.info("Total Courses Registered: "+registeredCourses.size());
		}
		catch(NullPointerException e) {
			log.error("studentID cannot be null!");
		}
	}

	//Method to calculate fees for registration and verifying registration requirements.
	@Override
	public int initiateRegistration(String studentId) {
		int totalFees = -1; 
		try {
			if(studentId == null)
				throw new NullPointerException();
			List<Course> registeredCourses = registerCourse.getAllCourses(studentId);
			if(registeredCourses.size() < 4) {
				return totalFees;
			}
			totalFees = registeredCourses.stream().mapToInt(course->course.getCourseFee()).sum();
		}
		catch(NullPointerException e) {
			log.error("studentID cannot be null!");
		}
		return totalFees;
	}
	
	//Method to generate registration Id and register student.
	@Override
	public RegistrationDetails completeRegistration(String studentId,int paymentId) {
		// TODO Auto-generated method stub
		return register.doRegistration(studentId, paymentId);
	}
	
	//Method to fetch registration details.
	@Override
	public RegistrationDetails getRegistrationDetails(String studentId) {
		// TODO Auto-generated method stub
		return register.getRegisterationDetails(studentId);
	}
	
	//Method to view grades of students in registered courses.
	@Override
	public void viewReportCard(String studentId) {
		// TODO Auto-generated method stub
		List<Result> results = registerCourse.getReportCard(studentId);
		log.info("REPORT CARD:");
		log.info(String.format("%-10s %-15s %-10s","COURSE-ID","COURSE-NAME","GRADE"));
		results.forEach(result->log.info(String.format("%-10s %-15s %-10s",result.getCourseId(),result.getCourseName(),result.getGrade())));
		log.info("Total Courses Registered: "+results.size());
	}
	
}
