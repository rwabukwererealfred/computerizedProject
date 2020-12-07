package com.studentRequest.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.studentRequest.dao.UserRoleDao;
import com.studentRequest.model.ERole;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;


public class UserDetailsImp implements UserDetails {
	
//	@Autowired
//	private UserRoleDao userRoleDao;
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private Staff staff;
	private Student student;
	private boolean active;
	private Collection<? extends GrantedAuthority>autho;
	
	
	
	
	public UserDetailsImp() {
		
	}
	
	public UserDetailsImp build(User user) {
		List<GrantedAuthority>list = user.getUserRole().stream().filter(i->i.isActive()==true).
				map(role -> new SimpleGrantedAuthority(role.getRole().getName().name())).collect(Collectors.toList());
		
		return new UserDetailsImp(user.getId(), user.getUsername(), user.getPassword(),
				user.getStaff(),user.getStudent(),isAccountNonExpired(),  list);	
	}

	

	public UserDetailsImp(int id, String username, String password, Staff staff, Student student, boolean active,
			Collection<? extends GrantedAuthority> autho) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.staff = staff;
		this.student = student;
		this.active = active;
		this.autho = autho;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public int getId() {
		return id;
	}

	public Staff getStaff() {
		return staff;
	}

	public Student getStudent() {
		return student;
	}


	public boolean isActive() {
		return active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autho;
	}


}
