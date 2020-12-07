package com.studentRequest.response;

import java.util.List;

import com.studentRequest.model.ERole;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;

public class JwtResponse {

	private int id;
	private String username;
	private String password;
	private Staff staff;
	private Student student;
	private boolean active;
	private List<String>role;
	private String token;
	
	
	
	
	public JwtResponse(int id, String username, String password, Staff staff, Student student, boolean active,
			List<String>role, String token) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.staff = staff;
		this.student = student;
		this.active = active;
		this.role = role;
		this.token = token;
	}


	public JwtResponse() {
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	


	public List<String> getRole() {
		return role;
	}


	public void setRole(List<String> role) {
		this.role = role;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
}
