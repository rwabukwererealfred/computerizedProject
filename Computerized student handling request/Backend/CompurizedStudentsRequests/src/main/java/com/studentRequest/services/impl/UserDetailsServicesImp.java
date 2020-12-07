package com.studentRequest.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studentRequest.dao.UserDao;
import com.studentRequest.model.User;

@Service
public class UserDetailsServicesImp implements UserDetailsService {
	
	@Autowired
	private UserDao userRepo;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(()->
		new UsernameNotFoundException("username not found with this username"+username));
		return new UserDetailsImp().build(user)	;
	}

}
