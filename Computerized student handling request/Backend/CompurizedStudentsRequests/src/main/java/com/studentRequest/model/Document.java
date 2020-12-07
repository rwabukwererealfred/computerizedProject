package com.studentRequest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Document {

	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	@Column(name="AMOUNT")
	private Double amount;
	@Column(name="COMMENT")
	private String commennt;
	
	@Column(name="FILE_NAME")
	private String fileName;
	@Column(name="FILE_TYPE")
	private String fileTye;
	
	@Column(name="IMAGE")
	@Lob
	private byte[]image;
	
	public static enum Status{
		Applied,Pending, Approved, Rejected
	}
	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCommennt() {
		return commennt;
	}
	public void setCommennt(String commennt) {
		this.commennt = commennt;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileTye() {
		return fileTye;
	}
	public void setFileTye(String fileTye) {
		this.fileTye = fileTye;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
