package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Internship;

@Repository
public interface InternishipDao extends JpaRepository<Internship, Integer> {

}
