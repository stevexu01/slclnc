package com.shengli.clinic.dao;

import java.util.List;

import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.Patient;

public interface PatientDao {
	
	String BIN_ID = "patientDao";

	
	public List<Patient> getAllpatient(String firstName, String lastName, String phone);
	
    public List<Patient> getpatientByFirstName(String firstName);
	
	public List<Patient> getpatientByLastName(String lastName);
	
	public Patient getpatientById(Long id);
	
	public List<Patient> getpatientByPhoneNumber(String phoneNumber);
	
	public void createpatient(Patient patient);
	
	public void createAddress(Address address);
	
	public void updatepatient(Patient patient);
	
	public void updateAddress(Address address);

}
