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

import org.springframework.data.annotation.TypeAlias;

@Entity
@Table(name="TRANSCRIPT")
public class Transcript extends Document {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="STUDENT")
	private Student student;
	
	@OneToOne
	@JoinColumn(name="BANK_SLIP")
	private Bankslip bankslip;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Bankslip getBankslip() {
		return bankslip;
	}
	public void setBankslip(Bankslip bankslip) {
		this.bankslip = bankslip;
	}
	
	
	
	
	
	
}
