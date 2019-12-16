package com.shengli.clinic.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengli.clinic.dao.PatientDao;
import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.Patient;
import com.shengli.clinic.services.PatientService;

@Service(PatientService.BEAN_ID)
public class PatientServiceImpl implements PatientService {
	
	@Autowired PatientDao patientDao;

	
	@Override
	public List<Patient> getAllpatient(String firstName, String lastName, String phone) {
		return patientDao.getAllpatient(firstName, lastName, phone);
	}

	@Override
	public List<Patient> getpatientByFirstName(String firstName) {
		return patientDao.getpatientByFirstName(firstName);
	}

	@Override
	public List<Patient> getpatientByLastName(String lastName) {
		return patientDao.getpatientByLastName(lastName);
	}


	@Override
	public Patient getpatientById(Long id) {
		return patientDao.getpatientById(id);
	}


	@Override
	public List<Patient> getpatientByPhoneNumber(String phoneNumber) {
		return patientDao.getpatientByPhoneNumber(phoneNumber);
	}
	
	@Override
	public void createpatient(Patient patient, Address address){
		
		patientDao.createpatient(patient);
		patientDao.createAddress(address);
	
	}
	
	@Override
	public void updatepatient(Patient patient, Address address){
		patientDao.updateAddress(address);
		patientDao.updatepatient(patient);
	}

	@Override
	public void updateDiagnose(Patient patient) {
		patientDao.updatepatient(patient);
		
	}



}
