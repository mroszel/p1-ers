package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.AddController;
import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.RegController;
import com.revature.controllers.SignoutController;
import com.revature.controllers.StatController;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse res)
	{
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/project-1---ers-app-mroszel/login.change":
			System.out.println("In login.change rhelper");
			return LoginController.login(req);
 		case "/project-1---ers-app-mroszel/home.change":
 			System.out.println("In home.change rhelper");
			return HomeController.home(req);
 		case "/project-1---ers-app-mroszel/reg.change":
 			System.out.println("In register.change rhelper");
 			return RegController.form(req);
 		case "/project-1---ers-app-mroszel/regSubmit.change":
 			System.out.println("In regSubmit.change rhelper");
 			return RegController.register(req);
 		case "/project-1---ers-app-mroszel/addForm.change":
 			return AddController.form(req);	
 		case "/project-1---ers-app-mroszel/addReimb.change":
 			return AddController.add(req);
 		case "/project-1---ers-app-mroszel/signout.change":
			return SignoutController.empSign(req,res);
 		case "/project-1---ers-app-mroszel/statSubmit.change":
 			return StatController.stat(req,res);
		default:
			System.out.println("In default");
			return "resources/html/unsuccessfullogin.html";
		}
		
	}
}