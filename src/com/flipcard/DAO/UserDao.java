package com.flipcard.DAO;

import java.util.List;

import com.flipcard.bean.User;
//This interface contains to add, view, delete and update users inside databse.
public interface UserDao {
	List<User> viewUser(String role);
	boolean addEntry(User user,String role);
	boolean removeEntry(String userId, String role);
	boolean updateEntry(String oldUserId, User user,String role);
}
