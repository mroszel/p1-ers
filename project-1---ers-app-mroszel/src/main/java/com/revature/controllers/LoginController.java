package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class LoginController {
	
	public static String login(HttpServletRequest req)
	{
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}

		UserDao udao = new UserDao();
		UserService uSrv = new UserService(udao);
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			uSrv.verifyLoginCredentials(username, password);
			
			req.getSession().setAttribute("loggedusername", username);
			req.getSession().setAttribute("loggedpassword", password);
			
			if(udao.getUser(username, password).getUserRole().name().equals("RESOLVER"))
			{
				req.getSession().setAttribute("reimbursements", rdao.getReimbs());
				return "resources/html/resolver.html";
			}
			else
			{
				req.getSession().setAttribute("uReceipts", rSrv.retrieveReimbursements());
				return "resources/html/author.html";
			}
			
		} catch (InvalidCredentialsException e) {

			return "resources/html/unsuccessfullogin.html";
		}
		
	}
}