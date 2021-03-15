package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.utils.ConnectionUtil;

/*
 * User Data Access object that reads and writes to a relational database
 */
public class UserDao {

	//determine role id based on user enum value
	public int getRoleID(UserRole userRole)
	{
		String role = userRole.toString();
		
		switch(role)
		{
		case "AUTHOR":
			return 1;
		case "RESOLVER":
			return 2;
		default:
			return 0;
		}
	}
	
	//write user into db
	public User addUser(User user) {
		
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

		String sql ="insert into ers_users values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = cu.getConnection().prepareStatement(sql);
			ps.setInt(1,user.getId());
			ps.setString(2,user.getUsername());
			ps.setString(3,user.getPassword());
			ps.setString(4,user.getFirstName());
			ps.setString(5,user.getLastName());
			ps.setString(6,user.getEmail());
			ps.setInt(7, getRoleID(user.getUserRole()));
			ps.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		
		return user;
	}
	
	//read user from database based on provided username and password
		public User getUser(Integer userId) {

			User get = new User();
			try {
				ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
				String sql = "select * from (ers_users u inner join ers_roles r on u.user_role_id = r.ers_user_role_id) "
						+ "as e where e.ers_users_id ='"+userId+"';";
				Statement s =cu.getConnection().createStatement();
				ResultSet rs = s.executeQuery(sql);
				while(rs.next()) {
					get.setId(rs.getInt(1));
					get.setUsername(rs.getString(2));
					get.setPassword(rs.getString(3));
					get.setFirstName(rs.getString(4));
					get.setLastName(rs.getString(5));
					get.setEmail(rs.getString(6));
					get.setUserRole(User.UserRole.valueOf(rs.getString(9).toUpperCase()));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return get;

		}
		
	//read user from database based on provided username and password
	public User getUser(String username, String pass) {

		User get = new User();
		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_users u inner join ers_roles r on u.user_role_id = r.ers_user_role_id) "
					+ "as e where e.ers_username ='"+username+"' AND ers_password = '" + pass + "';";
			Statement s =cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				get.setId(rs.getInt(1));
				get.setUsername(rs.getString(2));
				get.setPassword(rs.getString(3));
				get.setFirstName(rs.getString(4));
				get.setLastName(rs.getString(5));
				get.setEmail(rs.getString(6));
				get.setUserRole(User.UserRole.valueOf(rs.getString(9).toUpperCase()));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return get;

	}
	
	//read all users from database
	public List<User> getAllUsers(){
		
		List<User> users = new ArrayList<>();
		
		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_users u inner join ers_roles r on u.user_role_id = r.ers_user_role_id);";
			Statement s =cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				User get = new User();
				get.setId(rs.getInt(1));
				get.setUsername(rs.getString(2));
				get.setPassword(rs.getString(3));
				get.setFirstName(rs.getString(4));
				get.setLastName(rs.getString(5));
				get.setEmail(rs.getString(6));
				get.setUserRole(User.UserRole.valueOf(rs.getString(9).toUpperCase()));

				users.add(get);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//delete user based on given user object
	public boolean removeUser(User u) {

		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "DELETE FROM ers_users WHERE ers_users_id =?;";
			PreparedStatement ps = cu.getConnection().prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return getUser(u.getId()).getId() == null;
	}
	
	//update stored values in database for user based on given user object
	public User updateUser(User u) {

		User old = this.getUser(u.getId());
		removeUser(old);
		addUser(u);
		return u;
	}
}
