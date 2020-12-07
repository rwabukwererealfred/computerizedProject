package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CERTIFICATE")
public class Certificate extends Document {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="STUDENT")
	private Student student;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY")
	private CATEGORY category;
	
	@OneToOne
	@JoinColumn(name="BANK_SLIP")
	private Bankslip bankslip;
	
	public enum CATEGORY{
		A1, COA
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
	public CATEGORY getCategory() {
		return category;
	}
	public void setCategory(CATEGORY category) {
		this.category = category;
	}
	public Bankslip getBankslip() {
		return bankslip;
	}
	public void setBankslip(Bankslip bankslip) {
		this.bankslip = bankslip;
	}
	
	
	
	
}
