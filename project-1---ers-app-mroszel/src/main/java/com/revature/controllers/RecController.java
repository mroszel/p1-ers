package com.revature.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.services.ReimbursementService;

public class RecController {

	public static void getRec(HttpServletRequest req, HttpServletResponse res)
	{
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		
		
		try {
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new ObjectMapper().writeValueAsString(rSrv.retrieveReimbursements()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getDen(HttpServletRequest req, HttpServletResponse res)
	{
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		
		
		try {
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new ObjectMapper().writeValueAsString(rSrv.retrieveReimbursements(Reimbursement.statusType.DENIED)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getPend(HttpServletRequest req, HttpServletResponse res)
	{
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		
		
		try {
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new ObjectMapper().writeValueAsString(rSrv.retrieveReimbursements(Reimbursement.statusType.PENDING)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getApp(HttpServletRequest req, HttpServletResponse res)
	{
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		
		
		try {
		
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new ObjectMapper().writeValueAsString(rSrv.retrieveReimbursements(Reimbursement.statusType.APPROVED)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void empgetRec(HttpServletRequest req, HttpServletResponse res)
	{
		ReimbursementDao rdao = new ReimbursementDao();
		ReimbursementService rSrv = new ReimbursementService(rdao);
		UserDao udao = new UserDao();
		
		
		String username =req.getSession().getAttribute("loggedusername").toString();
		String password = req.getSession().getAttribute("loggedpassword").toString();
		User u = udao.getUser(username, password);
		
		
		try {
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(new ObjectMapper().writeValueAsString(rSrv.retrieveReimbursements(u)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
