package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {

}
