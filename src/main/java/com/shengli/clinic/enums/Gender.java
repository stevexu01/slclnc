package com.shengli.clinic.enums;

public enum Gender {

	Male("Male"), Female("Female");

	private String value;

	private Gender(String value) {
		this.value = value;
	}

	public String getGender() {
		return value;
	}
}
