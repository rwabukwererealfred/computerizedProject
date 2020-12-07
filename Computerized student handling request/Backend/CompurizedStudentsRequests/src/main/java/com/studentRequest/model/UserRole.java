package com.studentRequest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="USER_ROLE")
public class UserRole {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private boolean active;
	
	private Date roleCreatedDate;
	
	
	@ManyToOne
	@JoinColumn(name="USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="ROLE")
	private Role role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	public Date getRoleCreatedDate() {
		return roleCreatedDate;
	}
	public void setRoleCreatedDate(Date roleCreatedDate) {
		this.roleCreatedDate = roleCreatedDate;
	}
	
	
	public UserRole(int id, boolean active, Date roleCreatedDate, User user, Role role) {
		super();
		this.id = id;
		this.active = active;
		this.roleCreatedDate = roleCreatedDate;
		this.user = user;
		this.role = role;
	}
	public UserRole() {
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user=" + user + ", role=" + role + "]";
	}
	
	
}
