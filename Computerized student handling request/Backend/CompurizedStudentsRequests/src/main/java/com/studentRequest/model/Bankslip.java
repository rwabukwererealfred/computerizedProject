package com.studentRequest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BANK_SLIP")
public class Bankslip {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="TYPE")
	private String type;
	@Column(name="NAME")
	private String name;
	@Lob
	@Column(name="IMAGE")
	private byte[] image;
	@Column(name="BANK_SLEEP_NUMBER")
	private String banksleepNumber;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getBanksleepNumber() {
		return banksleepNumber;
	}
	public void setBanksleepNumber(String banksleepNumber) {
		this.banksleepNumber = banksleepNumber;
	}
	
	
	
}
