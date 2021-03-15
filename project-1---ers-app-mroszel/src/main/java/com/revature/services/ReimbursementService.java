package com.revature.services;

import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.statusType;
import com.revature.beans.User;
import com.revature.dao.ReimbursementDao;
import com.revature.exceptions.TicketNotPresentException;

/*
 * This class contains the business logic for performing operations on reimbursements
 */
public class ReimbursementService {

	ReimbursementDao rdao;

	public ReimbursementService(ReimbursementDao rdao) {
		this.rdao = rdao;
	}

	// retrieve all reimbursement tickets
	public List<Reimbursement> retrieveReimbursements() {
		return rdao.getReimbs();
	}

	// retrieve all reimbursement tickets for a given user
	public List<Reimbursement> retrieveReimbursements(User user) {
		return rdao.getReimbs(user.getId());
	}

	// retrieve all reimbursement tickets filtered by a given status
	public List<Reimbursement> retrieveReimbursements(Reimbursement.statusType status) {
		return rdao.getReimbs(status);
	}

	/**
	 * Approve or reject a reimbursement ticket
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(int res, Reimbursement r, boolean approval) {

		if(r.getId() == null)
		{
			throw new TicketNotPresentException();
		}
		
		
		if(approval)
		{
			r.setStatus(statusType.APPROVED);
			r.setResolveTime();
			r.setResolver(res);
			rdao.updateReimb(r);
			return true;
		}
		else
		{
			r.setStatus(statusType.DENIED);
			r.setResolveTime();
			r.setResolver(res);
			rdao.updateReimb(r);
			return false;
		}
	}
}
