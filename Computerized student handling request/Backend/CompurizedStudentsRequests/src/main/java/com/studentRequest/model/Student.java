package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student extends Person{
	@Id
	@Column(name="ID")
	private String id;
	
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

	public Student(String firstName, String lastName, String gender, String email, String phoneNumber, String id,
			Faculty faculty) {
		super(firstName, lastName, gender, email, phoneNumber);
		this.id = id;
		this.faculty = faculty;
	}

	public Student() {
		
	}

	public Student(String firstName, String lastName, String gender, String email, String phoneNumber) {
		super(firstName, lastName, gender, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	
}
