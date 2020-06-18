package com.flipcard.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.bean.Admin;
import com.flipcard.bean.Professor;
import com.flipcard.bean.Student;
import com.flipcard.bean.User;
import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.constants.UserConstants;
import com.flipcard.utils.DBUtil;
//This is implementer class for UserDao.
public class UserDaoImpl implements UserDao{
	//Logger object to write output to console.
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	
	//This method add user to user table and inside student, admin or professor table depending on the role.
	@Override
	public boolean addEntry(User user,String role) {
		// TODO Auto-generated method stub
		int rows1 = 0;
		int rows2 = 0;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			sql = SqlConstantQueries.ADD_USER;
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, user.getEmailId());
			stmt.setInt(2, user.getRoleId());
			stmt.setString(3, user.getPassword());
			rows1 = stmt.executeUpdate();
			sql = SqlConstantQueries.ADD_USER_DETAIL;
			sql = sql.replace("$tablename", role);
			stmt = DBUtil.getConnection().prepareStatement(sql);
			switch(role) {
			case UserConstants.STUDENT:
					Student student = (Student)user;
					stmt.setString(1, student.getEmailId());
					stmt.setString(2, student.getName());
					stmt.setString(3, student.getGender());
					break;
			case UserConstants.ADMIN:
				Admin admin = (Admin)user;
				stmt.setString(1, admin.getEmailId());
				stmt.setString(2, admin.getName());
				stmt.setString(3, admin.getGender());
				break;
			case UserConstants.PROFESSOR:
				Professor professor = (Professor)user;
				stmt.setString(1, professor.getEmailId());
				stmt.setString(2, professor.getName());
				stmt.setString(3, professor.getGender());
				break;	
			}
		rows2 = stmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows1!=0 && rows2!=0;
	}

	//This method remove user from user table and other table depending in the role.
	@Override
	public boolean removeEntry(String userId,String role) {
		// TODO Auto-generated method stub
		int rows1 = 0, rows2 = 0;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			sql = SqlConstantQueries.DELETE_USER_DETAIL;
			sql = sql.replace("$tablename", role);
			switch(role) {
			case UserConstants.STUDENT:
				sql = sql.replace("$id", "studentId");
						break;
			case UserConstants.ADMIN:
				sql = sql.replace("$id", "adminId");
						break;
			case UserConstants.PROFESSOR:
				sql = sql.replace("$id", "professorId");
						break;	
			}
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, userId);
			rows2 = stmt.executeUpdate();
			if(rows2 == 0)
				return false;
			sql = SqlConstantQueries.DELETE_USER;
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, userId);
			rows1 = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows1 != 0 && rows2 != 0;
	}
	
	//This method update password inside user table and name and gender inside student or admin or professor table depending on the role.
	@Override
	public boolean updateEntry(String oldUserId, User user,String role) {
		// TODO Auto-generated method stub
		int rows1 = 0, rows2 = 0;
		PreparedStatement stmt = null;
		String sql = null;
		try {
			sql = SqlConstantQueries.UPDATE_USER_DETAIL;
			sql = sql.replace("$tablename", role);
			switch(role) {
			case UserConstants.STUDENT:
					sql = sql.replace("$id", "studentId");
					stmt = DBUtil.getConnection().prepareStatement(sql);
					Student student = (Student)user;
					stmt.setString(1, student.getName());
					stmt.setString(2, student.getGender());
					break;
			case UserConstants.ADMIN:
				sql = sql.replace("$id", "adminId");
				stmt = DBUtil.getConnection().prepareStatement(sql);
				Admin admin = (Admin)user;
				stmt.setString(1, admin.getName());
				stmt.setString(2, admin.getGender());
				break;
			case UserConstants.PROFESSOR:
				sql = sql.replace("$id", "professorId");
				stmt = DBUtil.getConnection().prepareStatement(sql);
				Professor professor = (Professor)user;
				stmt.setString(1, professor.getName());
				stmt.setString(2, professor.getGender());
				break;	
			}
			stmt.setString(3, oldUserId);	
			rows2 = stmt.executeUpdate();
			if(rows2 == 0)
				return false;
			sql = SqlConstantQueries.UPDATE_USER;
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, oldUserId);
			rows1= stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return rows1!=0 && rows2 != 0;
	}

	//This method view user details from Student or Admin or Professor table depending on the role.
	@Override
	public List<User> viewUser(String role) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = SqlConstantQueries.VIEW_USER;
		sql = sql.replace("$tablename", role);
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				User user = null;
				String emailId = rs.getString(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				switch(role) {
					case UserConstants.ADMIN:
						user = new Admin(emailId, name, gender);
						break;
					case UserConstants.STUDENT:
						user = new Student(emailId, name, gender);
						break;	
					case UserConstants.PROFESSOR:
						user = new Professor(emailId, name, gender);
						break;	
				}
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		
		return users;
	}

}
