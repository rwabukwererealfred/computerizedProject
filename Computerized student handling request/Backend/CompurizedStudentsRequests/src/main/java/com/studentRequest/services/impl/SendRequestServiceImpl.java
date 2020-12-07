package com.studentRequest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRequest.dao.BankslipDao;
import com.studentRequest.dao.CertificateDao;
import com.studentRequest.dao.InternishipDao;
import com.studentRequest.dao.RecommadationDao;
import com.studentRequest.dao.StudentInfoDao;
import com.studentRequest.dao.TranscriptDao;
import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Student;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.Transcript;
import com.studentRequest.services.SendRequestService;

@Service
public class SendRequestServiceImpl implements SendRequestService {
	
	@Autowired
	private RecommadationDao recommendationDao;
	
	@Autowired
	private BankslipDao bankslipDao;
	
	@Autowired
	private TranscriptDao transcriptdao;
	
	@Autowired
	private StudentInfoDao stInfoDao;
	
	@Autowired
	private CertificateDao certificateDao;
	
	@Autowired
	private InternishipDao intDao;

	@Override
	public Recommendation send(Recommendation recommendation) {
		
		return recommendationDao.save(recommendation);
	}

	@Override
	public Bankslip saveBankslip(Bankslip bankslip) {
		
		return bankslipDao.save(bankslip);
	}

	@Override
	public Transcript sendTrancript(Transcript transcript) {
		
		return transcriptdao.save(transcript);
	}

	@Override
	public List<Recommendation> findByMyRequests(Student student) {
		
		return recommendationDao.findByStudent(student);
	}

	@Override
	public Recommendation findById(int id) {
		
		return recommendationDao.findById(id).get();
	}

	@Override
	public StudentInfo stInfoId(String id) {
		
		return stInfoDao.findById(id).get();
	}

	@Override
	public Certificate sendCertificate(Certificate certificate) {
		
		return certificateDao.save(certificate);
	}

	@Override
	public List<Recommendation> recommandationList() {
		
		return recommendationDao.findAll();
	}

	@Override
	public Internship sendInternshipRequest(Internship intern) {
		
		return intDao.save(intern);
	}

}
