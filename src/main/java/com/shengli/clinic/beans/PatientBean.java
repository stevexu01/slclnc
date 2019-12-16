package com.shengli.clinic.beans;




import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.Patient;
import com.shengli.clinic.services.PatientService;

@Component
@RequestScoped
@ManagedBean
public class PatientBean extends BaseBean {
	
	private static final String CREATE = "Create";
	private static final String UPDATE = "Update";
	private boolean created;
	
	@Autowired
	PatientService patientService;
	
	private Patient patient = new Patient();
	private Address address = new Address();
	private String action = CREATE;
	private Long id; 
	

	public String addPatient(){
		patient = new Patient();
		address = new Address();
		action = CREATE;
		created = false;
		return "create-patient.xhtml";
	}
	

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String viewpatient() {
		
		FacesContext context = FacesContext.getCurrentInstance(); 
		String patientId = context.getExternalContext().getRequestParameterMap().get("id");
	
		id = Long.valueOf(patientId);
		patient = patientService.getpatientById(id);
		address = patient.getAddress();
		action = UPDATE;
		
		return "create-patient.xhtml";
	}
	
	
	public String updatepatient(){
		patientService.updatepatient(patient, address);
		patient = patientService.getpatientById(patient.getId());
		FacesContext context = FacesContext.getCurrentInstance(); 
		String msg = BaseBean.getMessageFromResource("message.messages", UPDATE_SUCCESSFULLY, null, context.getViewRoot().getLocale());
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,msg);
			FacesContext.getCurrentInstance().addMessage("errmsg", fm);//clientID - "errmsg", id of the UIComponent

		return "create-patient.xhtml";
	}
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Patient getpatient() {
		return patient;
	}

	public void setpatient(Patient patient) {
		this.patient = patient;
	}

	
    public String createPatient(){
    	action= CREATE;
		patientService.createpatient(patient, address);
		FacesContext context = FacesContext.getCurrentInstance(); 
		String msg = BaseBean.getMessageFromResource("message.messages", CREATE_SUCCESSFULLY, null, context.getViewRoot().getLocale());
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,msg);
			FacesContext.getCurrentInstance().addMessage("errmsg", fm);//clientID - "errmsg", id of the UIComponent

		created = true;
		return null;
	}         
	
	
}
