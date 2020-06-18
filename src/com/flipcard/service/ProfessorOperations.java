package com.flipcard.service;
//This interface contains all the methods required for performing professor's task.
public interface ProfessorOperations {
	public void addCourse(String professorId, long courseId);
	public void dropCourse(String professorId, long courseId);
	public void viewCoursesToTeach(String professorId);
	public void addGrades(String studentId, int courseId, String grade);
	public void viewStudentsToTeach(String professorId);
}
