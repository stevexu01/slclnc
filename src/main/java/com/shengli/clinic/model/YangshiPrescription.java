package com.shengli.clinic.model;

import java.util.Date;


public class YangshiPrescription extends EntityBase {


	private static final long serialVersionUID = -7176305256664383643L;
	
	private Long id;
	private String prescriptionName;
	private Date createdDate;
	private String prescriptionText;
	private boolean editable;
	
	
	
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPrescriptionText() {
		return prescriptionText;
	}

	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
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
