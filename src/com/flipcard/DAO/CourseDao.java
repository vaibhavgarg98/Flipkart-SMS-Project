package com.flipcard.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.bean.Course;
import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.utils.DBUtil;
//Interface that contain method to add , update or delete data inside catalogue table.
public interface CourseDao {
	final Logger log = Logger.getLogger(CourseDao.class);
	//This default method gets all the courses inside catalogue table in database.
	default List<Course> getAllCourses() {
		Connection con = DBUtil.getConnection();
		List<Course> courses = new ArrayList<Course>();
		String sql = SqlConstantQueries.VIEW_CATALOGUE;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setId(rs.getLong("id"));
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("description"));
				course.setCourseFee(rs.getInt("courseFee"));
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return courses;
	}
	boolean addCourse(Course course);
	boolean updateCourse(long oldId, Course course);
	boolean dropCourse(long courseId);
}
