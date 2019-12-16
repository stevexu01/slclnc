package com.shengli.clinic.services;

import java.util.List;

import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.Patient;

public interface PatientService {
	
	String BEAN_ID = "patientService";
	
	public List<Patient> getAllpatient(String firstName, String lastName, String phone);
	
	public List<Patient> getpatientByFirstName(String firstName);
	
	public List<Patient> getpatientByLastName(String lastName);
	
	public Patient getpatientById(Long id);
	
	public List<Patient> getpatientByPhoneNumber(String phoneNumber);
	
	public void createpatient(Patient patient, Address address);
	
	public void updatepatient(Patient patient, Address address);
	
	public void updateDiagnose(Patient patient);

}
