package com.app.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.User;
import com.app.service.UserService;

@Controller
public class ApiController {
	
	@Autowired
	UserService userService;
	
/*	@RequestMapping( value ="/api/users" )
	public ModelAndView allUser (HttpServletRequest request, HttpServletResponse response) {
		
		
		ArrayList<User> userList = new ArrayList<User>();
		userList = (ArrayList<User>) userService.getAllUser();
		
		if(userList.isEmpty()) {
			ModelAndView mav= new ModelAndView("alluser");
			mav.addObject("alluser", "No user available in the database");
			return mav;
		}
		else {
			ModelAndView mav= new ModelAndView("alluser");
			mav.addObject("alluser", userList);
			return mav;
		}
	}*/
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET)
	@ResponseBody
	public List<User> allUser(){
		System.out.println("all users");
		List<User> userList = userService.getAllUser();
		if(userList.isEmpty()) {
			return userList;
			//return ResponseEntity.accepted().headers(headers).body(userList);
		}
		return userList;
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.GET)
	@ResponseBody
	public User singleUser(@PathVariable int id){
		System.out.println("perticular user");
		User user =  userService.getUser(id);		
		return user;
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.POST)
	@ResponseBody
	public User postUser(User user) {
		System.out.println("post user");
		return userService.postUser(user);
		
	}
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public User deleteUser(@PathVariable int id) {
		System.out.println("delete user");
		return userService.deleteUser(id);
	}
	@RequestMapping(value="/api/users", method=RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	
	
	
	

}
