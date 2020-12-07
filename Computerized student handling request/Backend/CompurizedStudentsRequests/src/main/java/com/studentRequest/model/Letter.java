package com.studentRequest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Letter {

	@Column(name="COMPANY_NAME")
	private String companyName;
	@Column(name="REGARDING_TO")
	private String regardingTo;
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name="DESCRIPTION")
	private String description;
	
	public static enum Status{
		Applied, Rejected,Approved
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRegardingTo() {
		return regardingTo;
	}
	public void setRegardingTo(String regardingTo) {
		this.regardingTo = regardingTo;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
