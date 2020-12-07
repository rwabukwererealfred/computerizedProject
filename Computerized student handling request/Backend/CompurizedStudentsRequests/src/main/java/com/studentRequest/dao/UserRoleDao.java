package com.studentRequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Role;
import com.studentRequest.model.User;
import com.studentRequest.model.UserRole;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

	List<UserRole>findByUser(User user);
	List<UserRole>findById(int id);
	boolean existsByUser(User user);
	boolean existsByRole(Role user);
	boolean existsByActive(boolean active);
	UserRole findByUserAndRole(User user, Role role);

}
