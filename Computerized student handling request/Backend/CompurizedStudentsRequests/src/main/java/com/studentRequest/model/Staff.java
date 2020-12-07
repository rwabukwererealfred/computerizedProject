package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STAFF")
public class Staff extends Person{

	@Id
	@Column(name="ID")
	private String id;
	
	
	@Column(name="ROLE")
	private String role;
	
	@ManyToOne
	@JoinColumn(name="FACULTY")
	private Faculty faculty;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	

	

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Staff(String firstName, String lastName, String gender, String email, String phoneNumber, String id,
			String role, Faculty faculty) {
		super(firstName, lastName, gender, email, phoneNumber);
		this.id = id;
		this.role = role;
		this.faculty = faculty;
	}

	public Staff() {
		
	}
	
	
	
}
