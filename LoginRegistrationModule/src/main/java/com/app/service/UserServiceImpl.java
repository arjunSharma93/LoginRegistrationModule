package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	UserDao userDao;

	@Override
	public long registrationProcess(User user) {
		return userDao.registrationProcess(user);
	}

	@Override
	public User loginProcess(User user) {
		return userDao.loginProcess(user);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	
	}

	@Override
	public User postUser(User user) {
		return userDao.postUser(user);
	}

	@Override
	public User deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}
	

}
