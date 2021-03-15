package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.reimbType;
import com.revature.beans.Reimbursement.statusType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDao {

	// determine reimbursement type based on reimbursement enum value
	public int getReimbTypeID(reimbType reimbType) {
		String type = reimbType.toString();

		switch (type) {
		case "LODGING":
			return 1;
		case "TRAVEL":
			return 2;
		case "FOOD":
			return 3;
		case "OTHER":
			return 4;
		default:
			return 0;
		}
	}

	// determine reimbursement status based on reimbursement enum value
	public int getStatusId(statusType status) {
		String sid = status.toString();

		switch (sid) {
		case "APPROVED":
			return 1;
		case "PENDING":
			return 2;
		case "DENIED":
			return 3;
		default:
			return 0;
		}
	}

	// write reimbursement into database
	public Reimbursement addReimb(Reimbursement reimb) {

		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

		String sql = "insert into ers_reimbursement values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = cu.getConnection().prepareStatement(sql);
			ps.setInt(1, reimb.getId());
			ps.setDouble(2, reimb.getAmount());
			ps.setString(3, reimb.getSubmitTime());
			ps.setString(4, reimb.getResolveTime());
			ps.setString(5, reimb.getDescription());
			ps.setInt(6, reimb.getAuthor());
			ps.setInt(7, reimb.getResolver());
			ps.setInt(8, getStatusId(reimb.getStatus()));
			ps.setInt(9, getReimbTypeID(reimb.getReimbType()));
			ps.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return reimb;
	}

	// read reimbursement from database using provided reimbursement id
	public Reimbursement getReimb(Integer reimbId) {

		Reimbursement get = new Reimbursement();
		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_reimbursement_status es inner join ers_reimbursement r \r\n"
					+ "	on es.status_id = r.reimb_status_id\r\n"
					+ "	inner join ers_reimbursement_type t on r.reimb_type_id = t.type_id)"
					+ " as comp where comp.reimb_id ='" + reimbId + "';";
			Statement s = cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				get.setId(rs.getInt(3));
				get.setAmount(rs.getDouble(4));
				get.setSubmitTime(rs.getString(5));
				get.setResolveTime(rs.getString(6));
				get.setDescription(rs.getString(7));
				get.setAuthor(rs.getInt(8));
				get.setResolver(rs.getInt(9));
				get.setStatus(Reimbursement.statusType.valueOf(rs.getString(2).toUpperCase()));
				get.setReimbType(Reimbursement.reimbType.valueOf(rs.getString(13).toUpperCase()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return get;

	}

	// retrieve all reimbursement with the provided author id
	public List<Reimbursement> getReimbs(Integer authorId) {

		List<Reimbursement> tickets = new ArrayList<>();

		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_reimbursement_status es inner join ers_reimbursement r \r\n"
					+ "	on es.status_id = r.reimb_status_id\r\n"
					+ "	inner join ers_reimbursement_type t on r.reimb_type_id = t.type_id)"
					+ " as comp where comp.reimb_author ='" + authorId + "';";
			Statement s = cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement stat = new Reimbursement();
				stat.setId(rs.getInt(3));
				stat.setAmount(rs.getDouble(4));
				stat.setSubmitTime(rs.getString(5));
				stat.setResolveTime(rs.getString(6));
				stat.setDescription(rs.getString(7));
				stat.setAuthor(rs.getInt(8));
				stat.setResolver(rs.getInt(9));
				stat.setStatus(Reimbursement.statusType.valueOf(rs.getString(2).toUpperCase()));
				stat.setReimbType(Reimbursement.reimbType.valueOf(rs.getString(13).toUpperCase()));

				tickets.add(stat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;

	}
	
	public List<Reimbursement> getReimbs() 
	{

		List<Reimbursement> tickets = new ArrayList<>();

		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_reimbursement_status es inner join ers_reimbursement r \r\n"
					+ "	on es.status_id = r.reimb_status_id\r\n"
					+ "	inner join ers_reimbursement_type t on r.reimb_type_id = t.type_id);";
			Statement s = cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement stat = new Reimbursement();
				stat.setId(rs.getInt(3));
				stat.setAmount(rs.getDouble(4));
				stat.setSubmitTime(rs.getString(5));
				stat.setResolveTime(rs.getString(6));
				stat.setDescription(rs.getString(7));
				stat.setAuthor(rs.getInt(8));
				stat.setResolver(rs.getInt(9));
				stat.setStatus(Reimbursement.statusType.valueOf(rs.getString(2).toUpperCase()));
				stat.setReimbType(Reimbursement.reimbType.valueOf(rs.getString(13).toUpperCase()));

				tickets.add(stat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;

	}
	
	public List<Reimbursement> getReimbs(Reimbursement.statusType status) {

		List<Reimbursement> tickets = new ArrayList<>();

		int id = getStatusId(status);
		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "select * from (ers_reimbursement_status es inner join ers_reimbursement r \r\n"
					+ "	on es.status_id = r.reimb_status_id\r\n"
					+ "	inner join ers_reimbursement_type t on r.reimb_type_id = t.type_id)"
					+ " as comp where comp.reimb_status_id ='" + id + "';";
			Statement s = cu.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement stat = new Reimbursement();
				stat.setId(rs.getInt(3));
				stat.setAmount(rs.getDouble(4));
				stat.setSubmitTime(rs.getString(5));
				stat.setResolveTime(rs.getString(6));
				stat.setDescription(rs.getString(7));
				stat.setAuthor(rs.getInt(8));
				stat.setResolver(rs.getInt(9));
				stat.setStatus(Reimbursement.statusType.valueOf(rs.getString(2).toUpperCase()));
				stat.setReimbType(Reimbursement.reimbType.valueOf(rs.getString(13).toUpperCase()));

				tickets.add(stat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;

	}

	// delete user based on given user object
	public boolean removeUser(Reimbursement r) {

		try {
			ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			String sql = "DELETE FROM ers_reimbursement WHERE reimb_id =?;";
			PreparedStatement ps = cu.getConnection().prepareStatement(sql);
			ps.setInt(1, r.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return getReimb(r.getId()).getId() == null;
	}
	
	//update stored values in database for reimbursement based on given reimbursement object
		public Reimbursement updateReimb(Reimbursement r) {

			Reimbursement old = this.getReimb(r.getId());
			removeUser(old);
			addReimb(r);
			return r;
		}

}
