package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class AddController {

	public static String add(HttpServletRequest req)
	{
		UserDao udao = new UserDao();
		UserService uSrv = new UserService(udao);
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		Reimbursement reimb = new Reimbursement();
		
		String username =req.getSession().getAttribute("loggedusername").toString();
		String password = req.getSession().getAttribute("loggedpassword").toString();
		User u = udao.getUser(username, password);
		
		List<Reimbursement> reimbs = rSrv.retrieveReimbursements();
		int rid = Integer.parseInt(uSrv.genId());
		
		for(Reimbursement r : reimbs)
		{
			while(r.getId() == rid)
			{
				rid = Integer.parseInt(uSrv.genId());
			}
		}
		reimb.setId(rid);
		reimb.setAmount(Double.parseDouble(req.getParameter("amount")));
		reimb.setDescription(req.getParameter("comment"));
		reimb.setSubmitTime();
		reimb.setAuthor(u.getId());
		reimb.setStatus(Reimbursement.statusType.PENDING);
		reimb.setReimbType(Enum.valueOf(Reimbursement.reimbType.class, req.getParameter("type").toUpperCase()));
		reimb.setResolver(0);
		reimb.setResolveTime("");
		rdao.addReimb(reimb);

		return "resources/html/addSuc.html";
	}
	
	public static String form(HttpServletRequest req)
	{
		return "resources/html/addform.html";
	}
}
