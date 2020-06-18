package com.flipcard.service;

import com.flipcard.bean.Course;
import com.flipcard.bean.User;
//This interface contain methods that are required for performing add operations
//AdminClient class will call this methods depending on the task need to be done.
public interface AdminOperations {
	void addCourse(Course course);
	void updateCourse(long oldId, Course course);
	void deleteCourse(long id);
	void viewCourses();
	void addUser(User user,String role);
	void editUser(String oldId,User user, String role);
	void deleteUser(String userId, String role);
	void viewUsers(String role);
}
