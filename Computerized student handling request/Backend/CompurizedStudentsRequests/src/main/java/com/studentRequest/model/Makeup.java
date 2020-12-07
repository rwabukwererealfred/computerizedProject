package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MAKE_UP")
public class Makeup extends Document {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name="STUDENT")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="COURSE")
	private Course course;
	
	@OneToOne
	@JoinColumn(name="BANK_SLIP")
	private Bankslip bankslip;
	
	
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
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
	public Bankslip getBankslip() {
		return bankslip;
	}
	public void setBankslip(Bankslip bankslip) {
		this.bankslip = bankslip;
	}
	
	
	
	
	
}
