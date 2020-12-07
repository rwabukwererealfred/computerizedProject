package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Transcript;

@Repository
public interface BankslipDao extends JpaRepository<Bankslip, Integer> {

	
}
