package com.studentRequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRequest.model.Certificate;

@Repository
public interface CertificateDao extends JpaRepository<Certificate, Integer> {

}
