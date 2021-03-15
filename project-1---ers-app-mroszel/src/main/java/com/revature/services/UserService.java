package com.revature.services;

import java.util.List;
import java.util.Random;

import com.revature.beans.*;
import com.revature.dao.UserDao;
import com.revature.exceptions.EmailInUseException;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;

/*
 * This class contains the business logic for performing operations on users
 */
public class UserService {

	UserDao udao;
	
	public UserService(UserDao udao) {
		this.udao = udao;
	}
	
	//generate id for user in the process of registering
	public String genId()
	{
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}
	
	//return user if provided login credentials correspond to a saved user in the db
	//@throws InvalidCredentialsException if provided login information is not present in db
	public User verifyLoginCredentials(String uname, String pass)
	{
		if (!(udao.getUser(uname, pass).getId() == null)) 
		{
			return udao.getUser(uname,pass);
		}
		else 
		{
			throw new InvalidCredentialsException();
		}
	}
	
	/*register new user
	 * @throws UsernameAlreadyExistsException if the given User's username is taken
	 * @throws EmailInUseExeption if the provided email has already been used in registration
	 */
	public void registerUser(User user)
	{
		List<User> users = udao.getAllUsers();
		
		Integer newId = Integer.parseInt(genId());
		
		for(User u : users)
		{
			String uname = u.getUsername();
			String newname = user.getUsername();
			
			String uemail = u.getEmail();
			String newemail = user.getEmail();
			
			Integer uid = user.getId();
			
			//generate ids until unique id is found
			while(uid == newId)
			{
				newId = Integer.parseInt(genId());
			}
			user.setId(newId);
			
			if(uname.equals(newname))
			{
				throw new UsernameAlreadyExistsException();
			}
			
			if(uemail.equals(newemail))
			{
				throw new EmailInUseException();
			}
		}
		
		udao.addUser(user);
	}
}

