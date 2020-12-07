package com.studentRequest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RECOMMENDATION")
public class Recommendation  {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="REQUEST_DATE")
	private Date requestDate;
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="FILE_NAME")
	private String fileName;
	@Column(name="FILE_TYPE")
	private String fileTye;
	
	@Column(name="IMAGE")
	@Lob
	private byte[]image;
	
	@ManyToOne
	@JoinColumn(name="STUDENT")
	private Student student;
	
	@OneToOne
	@JoinColumn(name="STAFF")
	private Staff staff;
	
	public static enum Status{
		New, Approved, Cancelled
	}
	
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
