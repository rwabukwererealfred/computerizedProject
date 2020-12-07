package com.studentRequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Student;

@Repository
public interface RecommadationDao extends JpaRepository<Recommendation, Integer> {

	List<Recommendation>findByStudent(Student student);
	List<Recommendation>findByStaff(Staff staff);
	
}
