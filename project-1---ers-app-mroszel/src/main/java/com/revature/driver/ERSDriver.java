package com.revature.driver;

import java.util.*;

import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.reimbType;
import com.revature.beans.Reimbursement.statusType;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;

public class ERSDriver {

	public static void main(String[] args) {

//		try {
//			System.out.println(Class.forName("org.postgresql.Driver"));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		UserDao uDao = new UserDao();
		User test = new User();
		List<Reimbursement> testTickets = new ArrayList<>();
		
		ReimbursementDao rDao = new ReimbursementDao();
		Reimbursement reimb = new Reimbursement();
		
		test.setEmail("testemail");
		test.setId(703);
		test.setFirstName("testfirst");
		test.setLastName("testlast");
		test.setPassword("testpassword");
		test.setUsername("testusername");
		test.setUserRole(UserRole.AUTHOR);
		test.setTickets(testTickets);
		
		//uDao.addUser(test); 
		
		//User test2 = uDao.getUser("testy", "zesty");
		//System.out.println(test2); 
		
		//List<User> testUsers = uDao.getAllUsers();
		//System.out.println(testUsers);

		//User test3 = uDao.getUser(132);
		//System.out.println(test3);
		
		//uDao.removeUser(test);
		//test.setEmail("New email");
		//uDao.updateUser(test);
		
		
		System.out.println(test);
		
		reimb.setId(123);
		reimb.setAmount(100.00);
		reimb.setAuthor(116);
		reimb.setResolver(132);
		reimb.setDescription("this is the description test");
		reimb.setReimbType(reimbType.FOOD);
		reimb.setSubmitTime();
		reimb.setResolveTime();
		reimb.setStatus(statusType.APPROVED);
		
		Reimbursement reimb2 = new Reimbursement();
		reimb2.setId(154);
		reimb2.setAmount(145.00);
		reimb2.setAuthor(116);
		reimb2.setResolver(132);
		reimb2.setDescription("this is the description test");
		reimb2.setReimbType(reimbType.LODGING);
		reimb2.setSubmitTime();
		reimb2.setResolveTime();
		reimb2.setStatus(statusType.APPROVED);
		//added reimb successful
		//rDao.addReimb(reimb2);
		//rDao.addReimb(reimb);
		//System.out.println(reimb);
		
		//successfully retrieved reimbursement
		//Reimbursement nReimb = rDao.getReimb(54);
		//System.out.println(nReimb);
		
		List<Reimbursement> tickets = rDao.getReimbs();
		System.out.println(tickets);
		
		//rDao.removeUser(reimb2);
		//System.out.println("checky or get recky");
		
		reimb.setAmount(1250.00);
		rDao.updateReimb(reimb);
	}

}