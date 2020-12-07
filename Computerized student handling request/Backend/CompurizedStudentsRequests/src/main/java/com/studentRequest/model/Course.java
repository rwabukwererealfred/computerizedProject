package com.studentRequest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Course")
public class Course {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="COURSE_NAME")
	private String courseName;
	@Column(name="CREDIT")
	private int credit;
	
	@ManyToOne
	@JoinColumn(name="STAFF")
	private Staff staff;
	
	@Transient
	private List<Makeup>makeup;
	
	
	
	public List<Makeup> getMakeup() {
		return makeup;
	}
	public void setMakeup(List<Makeup> makeup) {
		this.makeup = makeup;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	
}
