package com.studentRequest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;
import com.studentRequest.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	Optional<User>findByUsername(String username);
	boolean existsByStaff(Staff staff);
	boolean existsByStudent(Student student);
	List<User>findByStaff(String s);
	List<User>findByStudent(String s);
	
}
