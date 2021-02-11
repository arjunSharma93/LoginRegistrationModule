package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.User;
import com.app.service.UserService;

@Controller
public class LoginRegistrationController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		
		
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	@RequestMapping("/login")
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping("/registrationProcess")
	public ModelAndView registrationProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
		
		HttpSession session = request.getSession();
		ModelAndView mav;
		long id = userService.registrationProcess(user);
		if(id == 0) {
			mav = new ModelAndView("register");
			mav.addObject("errorFromUserDaoImpl","mobile or email existed, please login");
		}
		else {
			mav = new ModelAndView("welcome");
			mav.addObject("name", user.getName());
			session.setAttribute("user", user);
			
		}
		return mav;
	}
	@RequestMapping("/loginProcess")
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
		
		HttpSession session = request.getSession();
		ModelAndView mav;
		User newUser = userService.loginProcess(user);
		if(newUser != null) {
			mav = new ModelAndView("welcome");
			mav.addObject("name", newUser.getName());
			session.setAttribute("user", newUser);
		}
		else {
			mav = new ModelAndView("login");
			mav.addObject("errorFromUserDaoImpl","wrong credentials");
		}
		return mav;
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession(false).invalidate();
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("logout", "you are successfully logout");
		return mav;
	}

}
