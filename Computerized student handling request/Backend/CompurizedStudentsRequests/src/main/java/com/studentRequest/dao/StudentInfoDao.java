package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.StudentInfo;

@Repository
public interface StudentInfoDao extends JpaRepository<StudentInfo, String> {

}
