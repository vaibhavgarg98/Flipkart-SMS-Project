package com.flipcard.DAO;
import org.apache.log4j.Logger;

import com.flipcard.bean.Course;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.utils.DBUtil;
//Database level class to implement CourseDao interface.
public class CourseDaoImpl implements CourseDao{
	private static Logger log = Logger.getLogger(CourseDaoImpl.class);
	//This method enters course to catalogue db if course not present.
	@Override
	public boolean addCourse(Course course) {
		int rows = 0;
		String sql = SqlConstantQueries.ADD_COURSE;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setLong(1, course.getId());
			stmt.setString(2, course.getName());
			stmt.setInt(3, course.getCourseFee());
			stmt.setString(4, course.getDescription());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows!=0;
	}
	//This method delete course from catalogue database if course is peresent.
	@Override
	public boolean dropCourse(long courseId) {
		int rows = 0;
		String sql = SqlConstantQueries.DELETE_COURSE;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setLong(1, courseId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows!=0;
	}
	
	//This method update course inside catalogue.
	@Override
	public boolean updateCourse(long oldId, Course course) {
		// TODO Auto-generated method stub
		int rows = 0;
		String sql = SqlConstantQueries.UPDATE_COURSE;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setLong(1, course.getId());
			stmt.setString(2, course.getName());
			stmt.setInt(3, course.getCourseFee());
			stmt.setString(4, course.getDescription());
			stmt.setLong(5, oldId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows!=0;
	}
}
