package com.shengli.clinic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shengli.clinic.util.StringUtils;
import com.shengli.clinic.model.Patient;
import com.shengli.clinic.services.PatientService;

@Component
@RequestScoped
@ManagedBean
public class PatientSearchBean extends BaseBean{
	
	@Autowired
	PatientService patientService;

	private String firstName;
	private String lastName;
	private String phone;
	
	private List<Patient> patients = new ArrayList<Patient>();


	public String init(){
		firstName="";
		lastName="";
		phone="";
		getAllpatient();
		return "index.xhtml"; 
	}
	

	public String getAllpatient() {
		firstName = StringUtils.isNullOrEmpty(firstName) ? null : firstName;
		lastName = StringUtils.isNullOrEmpty(lastName) ? null : lastName;
		phone = StringUtils.isNullOrEmpty(phone) ? null : phone;
		List<Patient> list = patientService.getAllpatient(firstName, lastName, phone);
		setpatients(list);
		return "index.xhtml"; 
	}

	public List<Patient> getpatientByFirstName() {
		List<Patient> list = patientService.getpatientByFirstName(firstName);
		setpatients(list);
		return list; 
	}

	public List<Patient> getpatientByLastName() {
		List<Patient> list = patientService.getpatientByLastName(lastName);
		setpatients(list);
		return list; 
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Patient> getpatients() {
		if(patients == null || patients.size() == 0){
			return patientService.getAllpatient("", "", "");
		}
		return patients;
	}

	public void setpatients(List<Patient> patients) {
		this.patients = patients;
	}
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}
