package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * An individual reimbursement request. 
 * Reimbursements can have a status of pending, approved, or denied
 * Reimbursement can have a reimbursement type of lodging, travel, food, or other
 */
public class Reimbursement implements Serializable {

	/**
	 * generated unique id
	 */
	private static final long serialVersionUID = -3042741706474175412L;

	public static enum statusType {
		PENDING, APPROVED, DENIED
	}

	public static enum reimbType {
		LODGING, TRAVEL, FOOD, OTHER
	}

	private Integer id;
	private Double amount;
	private String submitTime;
	private String resolveTime;
	private String description;
	private Integer author;
	private Integer resolver;
	private statusType status;
	private reimbType reimbType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime() {
		this.submitTime = LocalDateTime.now().toString();
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime() {
		this.resolveTime = LocalDateTime.now().toString();
	}
	
	public void setResolveTime(String resolveTime) {
		this.resolveTime = resolveTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public Integer getResolver() {
		return resolver;
	}

	public void setResolver(Integer resolver) {
		this.resolver = resolver;
	}

	public statusType getStatus() {
		return status;
	}

	public void setStatus(statusType status) {
		this.status = status;
	}

	public reimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(reimbType reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((submitTime == null) ? 0 : submitTime.hashCode());
		result = prime * result + ((resolveTime == null) ? 0 : resolveTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (submitTime == null) {
			if (other.submitTime != null)
				return false;
		} else if (!submitTime.equals(other.submitTime))
			return false;
		if (resolveTime == null) {
			if (other.resolveTime != null)
				return false;
		} else if (!resolveTime.equals(other.resolveTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;

		return true;
	}

	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitTime=" + submitTime + ", resolveTime="
				+ resolveTime + ", description=" + description + ", author=" + author + ", resolver=" + resolver
				+ ", status=" + status + ", reimbType=" + reimbType + "]\n";
	}
}
