package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.TicketNotPresentException;
import com.revature.services.ReimbursementService;

public class StatController {

	public static String stat(HttpServletRequest req, HttpServletResponse res) {

		res.setContentType("text/html");

		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		UserDao udao = new UserDao();

		String username = req.getSession().getAttribute("loggedusername").toString();
		String password = req.getSession().getAttribute("loggedpassword").toString();

		int resolve = udao.getUser(username, password).getId();

		String stat = req.getParameter("stattype");
		String rid = req.getParameter("rid");

		Reimbursement change = rdao.getReimb(Integer.parseInt(rid));
		if (stat.equals("approve")) 
		{
			try
			{
			rSrv.approveOrRejectAccount(resolve, change, true);	
			}
			catch(TicketNotPresentException e)
			{
				return "resources/html/statFail.html";
			}
		} 
		else if (stat.equals("deny")) 
		{
			try
			{
			rSrv.approveOrRejectAccount(resolve, change, false);	
			}
			catch(TicketNotPresentException e)
			{
				return "resources/html/statFail.html";
			}
		}


		res.setContentType("text/html");
		return "resources/html/stat.html";
	}
}