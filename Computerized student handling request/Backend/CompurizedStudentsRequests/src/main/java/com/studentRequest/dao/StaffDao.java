package com.studentRequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Faculty;
import com.studentRequest.model.Staff;

@Repository
public interface StaffDao extends JpaRepository<Staff, String> {

	boolean existsById(String staff);
	@Query("SELECT * FROM Faculty where id =:id")
	List<Staff>findListById(String id);
	List<Staff>findByFaculty(Faculty faculty);
}
