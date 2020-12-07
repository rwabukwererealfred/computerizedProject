package com.studentRequest.services;

import java.util.List;

import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Student;
import com.studentRequest.model.StudentInfo;
import com.studentRequest.model.Transcript;

public interface SendRequestService {

	Recommendation send(Recommendation recommendation);
	Bankslip saveBankslip(Bankslip bankslip);
	Transcript sendTrancript(Transcript transcript);
	List<Recommendation>findByMyRequests(Student student);
	Recommendation findById(int id);
	StudentInfo stInfoId(String id);
	Certificate sendCertificate(Certificate certificate);
	List<Recommendation>recommandationList();
	Internship sendInternshipRequest(Internship intern);
	
	
}
