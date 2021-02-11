package com.app.service;

import java.util.List;

import com.app.model.User;

public interface UserService {
	
	//register the user
		long registrationProcess(User user);
	//login the user
		User loginProcess(User user);
	//get all user
		List<User> getAllUser();
	//get single user
		User getUser(int id);
	//post a user
		User postUser(User user);
	//delete a user
		User deleteUser(int id);
	//update a user
		User updateUser(User user);

}
