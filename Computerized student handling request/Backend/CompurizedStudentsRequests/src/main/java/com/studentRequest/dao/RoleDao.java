package com.studentRequest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.ERole;
import com.studentRequest.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

	Optional<Role>findByName(ERole r);
}
