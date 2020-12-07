package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="STAFF_REQUEST")
public class StaffRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Enumerated
	@Column(name="STATUS")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="STAFF")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name="CERTIFICATE")
	private Certificate certificate;
	@ManyToOne
	@JoinColumn(name="INTERNSHIP")
	private Internship internship;
	@ManyToOne
	@JoinColumn(name="MAKE_UP")
	private Makeup makeup;
	@ManyToOne
	@JoinColumn(name="RECOMMENDATION")
	private Recommendation recommandation;
	@ManyToOne
	@JoinColumn(name="TRANSCRIPT")
	private Transcript transcript;
	
	
	
	public static enum Status{
		Approved,New
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public Makeup getMakeup() {
		return makeup;
	}

	public void setMakeup(Makeup makeup) {
		this.makeup = makeup;
	}

	public Recommendation getRecommandation() {
		return recommandation;
	}

	public void setRecommandation(Recommendation recommandation) {
		this.recommandation = recommandation;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	
	
}
