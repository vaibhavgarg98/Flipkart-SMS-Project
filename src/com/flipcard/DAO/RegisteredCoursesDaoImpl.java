package com.flipcard.DAO;

import java.util.List;
import com.flipcard.bean.Course;
import com.flipcard.constants.UserConstants;
//Implementer class for CourseToUserDao class.
public class RegisteredCoursesDaoImpl implements CoursesToUserDao {
	
	public boolean addEntry(String userId, long courseId) {
		return addEntry(userId, courseId, UserConstants.STUDENT);
	}

	public boolean dropEntry(String userId, long courseId) {
		return dropEntry(userId, courseId, UserConstants.STUDENT);
	}

	public List<Course> getAllCourses(String userId) {
		return getAllCourses(userId, UserConstants.STUDENT);
	}
	
}
