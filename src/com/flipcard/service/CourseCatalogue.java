package com.flipcard.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.*;
import com.flipcard.bean.Course;
// Course Catalogue to display courses
public class CourseCatalogue {
	//Logger object to write to stdout.
	private static Logger log = Logger.getLogger(CourseCatalogue.class);
	//Method to view all the courses present inside the catalogue,this method is called by client classes.
	public static void viewCourses() {
		CourseDao coursedao = new CourseDaoImpl();
		List<Course> courses = coursedao.getAllCourses();
		if(courses.isEmpty()) {
			log.info("Catalgue is empty!");
			return;
		}
		log.info("Course Catalogue:");
		log.info(String.format("%-10s %-15s %-10s","ID","NAME","DESCRIPTION"));
		courses.forEach(course->log.info(String.format("%-10s %-15s %-10s",course.getId(),course.getName(),course.getDescription())));
		log.info("Total Courses: "+courses.size());
	}
}
