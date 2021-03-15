package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SignoutController {

	public static String empSign(HttpServletRequest req, HttpServletResponse res)
	{
		HttpSession session = req.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
		res.setContentType("text/html");
		return "resources/html/index.html";
	}
}
