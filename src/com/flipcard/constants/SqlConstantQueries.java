package com.flipcard.constants;

public class SqlConstantQueries {
	// SQL Queries to perform database operation.
	public static final String VIEW_USER = "SELECT * from $tablename order by name";
	public static final String ADD_USER = "INSERT into users values(?, ?, ?)";
	public static final String ADD_USER_DETAIL = "INSERT into $tablename values(?, ?, ?)";
	public static final String DELETE_USER = "DELETE from users where emailId = ?";
	public static final String DELETE_USER_DETAIL = "DELETE from $tablename where $id = ?";
	public static final String UPDATE_USER = "UPDATE users set password = ? where emailId = ?";
	public static final String UPDATE_USER_DETAIL = "UPDATE $tablename set name = ? , gender = ? where $id = ?";
	public static final String ADD_COURSE = "INSERT into catalogue values(?, ?, ?, ?)";
	public static final String DELETE_COURSE = "DELETE from catalogue where id = ?";
	public static final String UPDATE_COURSE = "UPDATE catalogue set id = ?, name = ?, courseFee = ?, description = ? where id = ?";
	public static final String VIEW_CATALOGUE = "SELECT * from catalogue order by name";
	public static final String VERIFY_USER = "SELECT role.name from users INNER JOIN role  where role.roleId = users.roleId AND users.emailId = ? AND users.password = ?";
	public static final String REGISTER_COURSE = "INSERT into studentCourses(studentId, courseId) values(?, ?)";
	public static final String UNREGISTER_COURSE = "DELETE from studentCourses where studentId = ? and courseId = ?";
	public static final String VIEW_REGISTERED_COURSES = "SELECT * from catalogue, studentCourses where studentCourses.courseId = catalogue.id AND studentCourses.studentId = ?";
	public static final String TEACH_COURSE = "INSERT into professorCourses values(?, ?)";
	public static final String REMOVE_TEACH_COURSE = "DELETE from professorCourses where professorId = ? and courseId = ?";
	public static final String VIEW_TEACH_COURSES = "SELECT catalogue.name, catalogue.id, catalogue.credits, catalogue.hours from catalogue, professorCourses where professorCourses.courseId = catalogue.id AND professorCourses.professorId = ?";
	public static final String CHECK_REGISTRATION_DETAILS = "SELECT * from registerdetails where studentId = ?";
	public static final String DO_REGISTRATION = "INSERT into registerdetails values(?, ?, ?, ?)";
	public static final String GET_REGISTRATION_ID = "SELECT count(*) from registerdetails"; 
	public static final String STUDENTS_TO_TEACH = "select users.emailId, users.name from users, professorCourses, studentCourses where professorCourses.professorId = ? AND professorCourses.courseId = studentCourses.courseId AND studentCourses.studentId = users.emailId";
	public static final String ADD_GRADES = "UPDATE studentCourses set courseGrade = ? where studentId = ? AND courseId = ?";
	public static final String VIEW_STUDENTS_TO_TEACH = "SELECT student.name from student, studentCourses, professorCourses where student.studentId = studentCourses.studentId AND professorCourses.courseId = studentCourses.courseId AND professorCourses.professorId = ?";
	public static final String GET_GRADES = "Select catalogue.id, catalogue.`name`, studentCourses.courseGrade from catalogue, studentCourses where catalogue.id = studentCourses.courseId AND studentCourses.studentId = ?";
}
