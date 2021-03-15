package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.UserDao;
import com.revature.exceptions.EmailInUseException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.services.UserService;

public class RegController {

	public static String register(HttpServletRequest req)
	{
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		UserDao udao = new UserDao();
		UserService uSrv = new UserService(udao);
		User usr = new User();
		
		usr.setEmail(req.getParameter("demail"));
		usr.setUsername(req.getParameter("dusername"));
		usr.setPassword(req.getParameter("dpassword"));
		usr.setFirstName(req.getParameter("dfirst"));
		usr.setLastName(req.getParameter("dlast"));
		usr.setUserRole(UserRole.AUTHOR);
		
		try
		{
			uSrv.registerUser(usr);
			
			return "resources/html/regSuc.html";
		}
		catch(UsernameAlreadyExistsException e)
		{
			return "resources/html/dupUser.html";
		}
		catch(EmailInUseException e)
		{
			return "resources/html/dupEmail.html";
		}
		
	}
	
	public static String form(HttpServletRequest req)
	{
		
		return "resources/html/registerform.html";
	}
}