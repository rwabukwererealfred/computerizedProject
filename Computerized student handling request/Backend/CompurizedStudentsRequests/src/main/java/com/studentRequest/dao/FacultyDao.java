package com.studentRequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Faculty;

@Repository
public interface FacultyDao extends JpaRepository<Faculty, Integer> {

	List<Faculty>findByFaculty(Faculty faculty);
	
}
