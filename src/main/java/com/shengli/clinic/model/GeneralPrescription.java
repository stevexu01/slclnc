package com.shengli.clinic.model;

import java.util.Date;


public class GeneralPrescription extends EntityBase{
	         

	private static final long serialVersionUID = -3719775581149797183L;
	
	public static String newline = System.getProperty("line.separator");
	
	private Long id;
	private String prescriptionText;
	private Date createdDate;
	private Long patientId;
	private String htmlText;

	public GeneralPrescription(){
		
	}
	
	public String getHtmlText() {
		htmlText =	prescriptionText.replaceAll(newline, "<br />");
	     return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public GeneralPrescription(Long patientId){
		this.patientId = patientId;
	}

	public Long getpatientId() {
		return patientId;
	}

	public void setpatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPrescriptionText() {
		return prescriptionText;
	}

	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
