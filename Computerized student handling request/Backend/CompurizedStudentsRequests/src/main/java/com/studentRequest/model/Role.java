package com.studentRequest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="ROLE")
public class Role {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name="NAME")
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	@JsonIgnore
	@OneToMany(mappedBy="role" , cascade= CascadeType.ALL)
	private List<UserRole>userRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public Role(int id, ERole name) {
		
		this.id = id;
		this.name = name;
	}

	public Role() {
		
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
}
