package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSONMasterServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2771812346786595604L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		System.out.println("confirm");
		JSONRequestHelper.process(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		JSONRequestHelper.process(req, res);
	}
}