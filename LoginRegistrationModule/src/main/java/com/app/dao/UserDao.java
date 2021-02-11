package com.app.dao;

import java.util.List;

import com.app.model.User;

public interface UserDao {
	
	//register the user
		long registrationProcess(User user);
	//login the user
		User loginProcess(User user);
	//get all users
		List<User> getAllUser();
	//get a user
		User getUser(int id);
	//post a user
		User postUser(User user);
	//delete a user
		User deleteUser(int id);
	//update a user
		User updateUser(User user);

}
