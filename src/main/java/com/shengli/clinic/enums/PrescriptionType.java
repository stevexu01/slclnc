package com.shengli.clinic.enums;

public enum PrescriptionType {
	YANGSHI("Yangshi"),GENERAL("General");
	
	private String value;

	private PrescriptionType(String value) {
		this.value = value;
	}

	public String getPrescriptionType() {
		return value;
	}
}
