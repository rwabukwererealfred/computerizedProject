package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Makeup;

@Repository
public interface MakeupDao extends JpaRepository<Makeup, Integer> {

}
