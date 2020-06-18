package com.flipcard.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.CourseDao;
import com.flipcard.DAO.CourseDaoImpl;
import com.flipcard.DAO.UserDao;
import com.flipcard.DAO.UserDaoImpl;
import com.flipcard.bean.Admin;
import com.flipcard.bean.Course;
import com.flipcard.bean.Professor;
import com.flipcard.bean.Student;
import com.flipcard.bean.User;
import com.flipcard.constants.UserConstants;
//Implementer class for Admin Operations interface
public class AdminOperationsImpl implements AdminOperations{
	
	//Logger object to write to console.
	private static Logger log = Logger.getLogger(AdminOperationsImpl.class);
	
	//Data access objects to manage the flow of data in and out of database.
	UserDao userDao = new UserDaoImpl();
	CourseDao courseDao = new CourseDaoImpl();
	
	//Method called by client class to perform add user to system operation.
	@Override
	public void addUser(User user, String role) {
		// TODO Auto-generated method stub
			try {
				if(role == null || user == null)
					throw new NullPointerException();
				if(userDao.addEntry(user, role))
					log.info(role+" with id "+user.getEmailId()+" added!");
				else
					log.error("UserId already taken!");
			}
			catch(NullPointerException e) {
				log.error(e.getMessage());
			}
	}
	
	//Method called by client class to perform update user details inside system.
	@Override
	public void editUser(String oldId,User user, String role) {
		// TODO Auto-generated method stub
		try {
			if(role == null || user == null || oldId == null)
				throw new NullPointerException();
			if(userDao.updateEntry(oldId,user, role))
				log.info(role+" with id "+user.getEmailId()+" updated!");
			else
				log.error("No such "+role+" !");
		}
		catch(NullPointerException e) {
			log.error(e.getMessage());
		}	
	}
	
	//Method called by client class to perform delete user from system operation.
	@Override
	public void deleteUser(String userId, String role) {
		// TODO Auto-generated method stub
		try {
			if(role == null || userId == null)
				throw new NullPointerException();
			if(userDao.removeEntry(userId, role))
				log.info(role+" removed!");
			else
				log.error("No such "+role+" !");
		}
		catch(NullPointerException e) {
			log.error(e.getMessage());
		}
	}
	
	//Method called by client class to perform add course to catalogue operation.
	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			if(course == null)
				throw new NullPointerException();
			if(courseDao.addCourse(course))
				log.info("Course added to Catalogue!");
			else
				log.error("Course Id already present!");
		}
		catch(NullPointerException e) {
			log.error(e.getMessage());
		}
		
	}
	
	//Method called by client class to perform update course in catalogue operation.
	@Override
	public void updateCourse(long oldId, Course course) {
		// TODO Auto-generated method stub
		try {
			if(course == null)
				throw new NullPointerException();
			if(courseDao.updateCourse(oldId, course))
				log.info("Course Updated!");
			else
				log.error("No such course with id "+oldId);
		}
		catch(NullPointerException e) {
			log.error(e.getMessage());
		}
	}
	
	//Method called by client class to perform delete course from catalogue operation.
	@Override
	public void deleteCourse(long id) {
		// TODO Auto-generated method stub
		if(courseDao.dropCourse(id))
			log.info("Course removed from Catalogue!");
		else
			log.error("No such course with id "+id);
	}
	
	//Method called by client class to perform view courses in catalogue operation.
	@Override
	public void viewCourses() {
		// TODO Auto-generated method stub
		CourseCatalogue.viewCourses();
	}

	@Override
	public void viewUsers(String role) {
		// TODO Auto-generated method stub
		List<User> users = userDao.viewUser(role);
		if(users.isEmpty()) {
			log.info("No "+role+"s are added!");
			return;
		}
		log.info(role+" Records:");
		log.info(String.format("%-10s %-15s %-6s","ID","NAME","GENDER"));
		users.stream().
		forEach(user -> {
				String gender = null;
				String userName = null;
				
				if(user instanceof Student ) {
					userName = ((Student)user).getName();
					gender = ((Student)user).getGender();
				}
				
				if(user instanceof Professor ) {
					userName = ((Professor)user).getName();
					gender = ((Professor)user).getGender();
				}
				
				if(user instanceof Admin ) {
					userName = ((Admin)user).getName();
					gender = ((Admin)user).getGender();
				}
				
				if(gender.equals(UserConstants.MALE))
					userName = "Mr. "+userName;
				else
					userName = "Ms. "+userName;
				log.info(String.format("%-10s %-15s %-6s",user.getEmailId(), userName, gender));
			});
		log.info("Total Records: "+users.size());
	}
	
}
