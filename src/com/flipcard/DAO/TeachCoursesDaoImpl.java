package com.flipcard.DAO;

import java.util.List;
import com.flipcard.bean.Course;
import com.flipcard.constants.UserConstants;
//This is implementer class for CoursesToUserDao used for professor courses table. 
public class TeachCoursesDaoImpl implements CoursesToUserDao{
	public boolean addEntry(String userId, long courseId) {
		return addEntry(userId, courseId, UserConstants.PROFESSOR);
	}

	public boolean dropEntry(String userId, long courseId) {
		return dropEntry(userId, courseId, UserConstants.PROFESSOR);
	}

	public List<Course> getAllCourses(String userId) {
		return getAllCourses(userId, UserConstants.PROFESSOR);
	}

}
