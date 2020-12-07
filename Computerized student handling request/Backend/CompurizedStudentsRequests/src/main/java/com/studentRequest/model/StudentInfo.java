package com.studentRequest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentInfo {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Integer credit;
	private String phoneNumber;
	private String gender;
	private Double averageMark;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Double getAverageMark() {
		return averageMark;
	}
	public void setAverageMark(Double averageMark) {
		this.averageMark = averageMark;
	}
	public StudentInfo(String firstName, String lastName, String email, Integer credit, String phoneNumber,
			String gender, Double averageMark) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.credit = credit;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.averageMark = averageMark;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", credit=" + credit + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", averageMark="
				+ averageMark + "]";
	}
	public StudentInfo() {
		super();
		
	}
	
	
}
