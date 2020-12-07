package com.studentRequest.services;

import java.util.List;

import com.studentRequest.model.Bankslip;
import com.studentRequest.model.Certificate;
import com.studentRequest.model.Internship;
import com.studentRequest.model.Recommendation;
import com.studentRequest.model.Staff;
import com.studentRequest.model.Transcript;

public interface StaffRequestService {

	List<Recommendation>findStaffRecommendation(Staff staff);
	Recommendation findById(int id);
	List<Transcript>findTrascriptList();
	Transcript findByTrascriptId(int id);
	Bankslip findByBankslipId(int id);
	List<Bankslip>bankSlipList();
	List<Certificate>certificateList();
	Certificate findByCertificateId(int id);
	List<Internship>internshipList();
}
