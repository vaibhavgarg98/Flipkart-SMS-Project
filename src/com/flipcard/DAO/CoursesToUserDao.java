package com.flipcard.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.bean.Course;
import com.flipcard.bean.Result;
import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.constants.UserConstants;
import com.flipcard.utils.DBUtil;
//This interface contains methods which are used to register course to study or to teach.
public interface CoursesToUserDao {
	final Logger log = Logger.getLogger(CoursesToUserDao.class);
	
	//This method add course to teach or study table depending on role.
	default boolean addEntry(String userId, long courseId, String role) {
		String sql = SqlConstantQueries.REGISTER_COURSE;
		if(role.equals(UserConstants.PROFESSOR))
			sql = SqlConstantQueries.TEACH_COURSE;
		int rows= 0;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setLong(2, courseId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		
		return rows!=0;
	}
	//This method unregister course or remove course depending on users role.
	default boolean dropEntry(String userId, long courseId, String role) {
		String sql = SqlConstantQueries.UNREGISTER_COURSE;
		if(role.equals(UserConstants.PROFESSOR))
			sql = SqlConstantQueries.REMOVE_TEACH_COURSE;
		int rows= 0;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setLong(2, courseId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows!=0;
	}
	
	//This method fetch all the registered course to study of to teach depending on role of user.
	default List<Course> getAllCourses(String userId, String role){
		String sql = SqlConstantQueries.VIEW_REGISTERED_COURSES;
		if(role.equals(UserConstants.PROFESSOR))
			sql = SqlConstantQueries.VIEW_TEACH_COURSES;
		List<Course> registeredCourses = new ArrayList<Course>();
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setId(rs.getLong("id"));
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("Description"));
				course.setCourseFee(rs.getInt("courseFee"));
				registeredCourses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return registeredCourses;
	}
	
	//This method fetch result of user from studentcourses db.
	default boolean courseGrade(String studentId, int courseId, String grade) {
		int rows = 0 ;
		String sql = SqlConstantQueries.ADD_GRADES;
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, grade);
			stmt.setString(2, studentId);
			stmt.setInt(3, courseId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		
		return rows!=0;
	}
	default List<String> getRegisteredStudents(String professorId){
		List<String> studentNames = new ArrayList<String>();
		String sql = SqlConstantQueries.VIEW_STUDENTS_TO_TEACH;
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, professorId);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				studentNames.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return studentNames;
	}
	
	default List<Result> getReportCard(String studentId){
		List<Result> results = new ArrayList<Result>();
		String sql = SqlConstantQueries.GET_GRADES;
		PreparedStatement stmt;
		try {
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, studentId);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				Result result = new Result();
				result.setCourseId(rs.getInt("id"));
				result.setCourseName(rs.getString("name"));
				try {
					result.setGrade(rs.getString("courseGrade"));
				}
				catch(NullPointerException e) {
					result.setGrade("Not Available!");
				}
				results.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return results;
	}
}
