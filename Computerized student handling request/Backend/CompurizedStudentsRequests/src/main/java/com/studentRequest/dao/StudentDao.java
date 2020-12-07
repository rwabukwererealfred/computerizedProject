package com.studentRequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Faculty;
import com.studentRequest.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, String> {

	boolean existsById(String student);
	@Query("SELECT * FROM Student where id =:id")
	List<Student>findListById(String id);
	List<Student>findByFaculty(Faculty faculty);
}
