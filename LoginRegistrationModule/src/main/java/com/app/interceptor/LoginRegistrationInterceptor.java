package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.app.model.User;

public class LoginRegistrationInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			ModelAndView mav = new ModelAndView("welcome");
			mav.addObject("name",user.getName());
			throw new ModelAndViewDefiningException(mav);
		}
		return true;
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception{
		
		String ip = request.getRemoteAddr();
		mav.addObject("ipAdrs", "your ip address is: "+ip);
	}
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	}

}
