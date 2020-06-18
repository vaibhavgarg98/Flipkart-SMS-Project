package com.flipcard.service;

import com.flipcard.bean.RegistrationDetails;
//Interface that contains methods to perform student operations. 
public interface StudentOperations {
	public void addCourse(String studentId, long courseId);
	public void dropCourse(String studentId, long courseId);
	public void showRegisteredCourses(String studentId);
	public int initiateRegistration(String studentId);
	public RegistrationDetails completeRegistration(String studentId,int paymentId);
	public RegistrationDetails getRegistrationDetails(String studentId);
	public void viewReportCard(String studentId);
}
