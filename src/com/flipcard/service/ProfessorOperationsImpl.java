package com.flipcard.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.flipcard.DAO.TeachCoursesDaoImpl;
import com.flipcard.bean.Course;


//This class is implementer class for ProfessorOperations interface
public class ProfessorOperationsImpl implements ProfessorOperations{
	//Data Access objects to manage data in and out of database.
	private TeachCoursesDaoImpl teachCourse = new TeachCoursesDaoImpl();
	//Logger object to write to console.
	private static Logger log = Logger.getLogger(ProfessorOperationsImpl.class);
	
	//Method to perform add course to teaching list in client class.
	@Override
	public void addCourse(String professorId, long courseId) {
		try {
			if(professorId==null)
				throw new NullPointerException();
			if(teachCourse.addEntry(professorId, courseId))
				log.info("Added to teach courses!");
			else
				log.error("Cannot be added to teach courses!");
			}
		catch(NullPointerException e) {
			log.error("Professor Id cannot be null!");
		}
	}
	
	//Method to perform operation of removing a course from teaching list.
	@Override
	public void dropCourse(String professorId, long courseId) {
		try {
			if(professorId==null)
				throw new NullPointerException();
			if(teachCourse.dropEntry(professorId, courseId))
					log.info("Course removed from teach list!");
			else
				log.error("Course not present!");
		}
		catch(NullPointerException e) {
			log.error("Professor Id cannot be null!");
		}
	}
	
	//Method to perform view courses to teach operation in client class.
	@Override
	public void viewCoursesToTeach(String professorId) {
		// TODO Auto-generated method stub
		try {
			if(professorId == null)
				throw new NullPointerException();
			List<Course> registeredCourses = teachCourse.getAllCourses(professorId);
			if(registeredCourses.isEmpty()) {
				log.info("No Courses to teach!");
				return;
			}
			log.info("Courses To Teach:");
			log.info(String.format("%-10s %-15s %-10s","ID","NAME","DESCRIPTION"));
			registeredCourses.forEach(course->log.info(String.format("%-10s %-15s %-10s",course.getId(),course.getName(),course.getDescription())));
			log.info("Total Courses To Teach: "+registeredCourses.size());
		}
		catch(NullPointerException e) {
			log.error("Professor Id cannot be null!");
		}
	}
	
	//Method to perform add grades to student results in professor client class.
	@Override
	public void addGrades(String studentId, int courseId, String grade) {
		// TODO Auto-generated method stub
		if(teachCourse.courseGrade(studentId, courseId, grade))
			log.info("Grade added!");
		else
			log.error("Invalid courseId or StudentId!");
	}
	
	//Method to perform view students to teach in Professor clients class.
	@Override
	public void viewStudentsToTeach(String professorId) {
		// TODO Auto-generated method stub
		List<String> students = teachCourse.getRegisteredStudents(professorId);
		log.info("Students to teach:");
		log.info("NAME");
		students.forEach(log::info);
		log.info("Total Students: "+students.size());
	}

}
