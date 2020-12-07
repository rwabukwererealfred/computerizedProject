package com.studentRequest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRequest.dao.BankslipDao;
import com.studentRequest.dao.CertificateDao;
import com.studentRequest.dao.InternishipDao;
import com.studentRequest.dao.RecommadationDao;
import com.studentRequest.dao.TranscriptDao;
import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Transcript;
import com.studentRequest.services.StaffRequestService;

@Service
public class StaffRequestServiceImp implements StaffRequestService {
	
	@Autowired
	private RecommadationDao recommendationDao;
	
	@Autowired
	private TranscriptDao transcriptDao;
	
	@Autowired
	private BankslipDao bankslipDao;
	
	@Autowired
	private CertificateDao certificateDao;
	
	@Autowired
	private InternishipDao intDao;

	@Override
	public List<Recommendation> findStaffRecommendation(Staff staff) {
		
		return recommendationDao.findByStaff(staff);
	}

	@Override
	public Recommendation findById(int id) {
		
		return recommendationDao.findById(id).get();
	}

	@Override
	public List<Transcript> findTrascriptList() {
		
		return transcriptDao.findAll();
	}

	@Override
	public Transcript findByTrascriptId(int id) {
		
		return transcriptDao.findById(id).get();
	}

	@Override
	public Bankslip findByBankslipId(int id) {
		
		return bankslipDao.findById(id).get();
	}

	@Override
	public List<Bankslip> bankSlipList() {
		
		return bankslipDao.findAll();
	}

	@Override
	public List<Certificate> certificateList() {
		
		return certificateDao.findAll();
	}

	@Override
	public Certificate findByCertificateId(int id) {
		//
		return certificateDao.findById(id).get();
	}

	@Override
	public List<Internship> internshipList() {
		
		return intDao.findAll();
	}

}
