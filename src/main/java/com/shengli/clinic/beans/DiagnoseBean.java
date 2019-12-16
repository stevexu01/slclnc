package com.shengli.clinic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.GeneralPrescription;
import com.shengli.clinic.model.Patient;
import com.shengli.clinic.model.SubProcess;
import com.shengli.clinic.model.YangshiPrescription;
import com.shengli.clinic.model.YangshiProcess;
import com.shengli.clinic.services.DiagnoseService;
import com.shengli.clinic.services.PatientService;


@Component
@RequestScoped
@ManagedBean
public class DiagnoseBean extends BaseBean{
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DiagnoseService diagnoseService;

	private String useYangshi = "no";
	
	private boolean yangshi = false;

	private Patient patient;
	private Address address;
	private List<GeneralPrescription> generalPrescriptions = new ArrayList<GeneralPrescription>();
	private List<YangshiPrescription> yangshiPrescriptions = new ArrayList<YangshiPrescription>();
	private List<YangshiProcess> yangshiProcesses = new ArrayList<YangshiProcess>();
	private GeneralPrescription newGeneralPrescription;
	private YangshiPrescription newYangshiPrescription = new YangshiPrescription();
	
	private YangshiPrescription currentSelectedPrescription;
	private Long id; 
	
	
	public String viewDiagonse() {
		FacesContext context = FacesContext.getCurrentInstance(); 
		String patientId = context.getExternalContext().getRequestParameterMap().get("id");
		if(StringUtils.isNullOrEmpty(patientId)){
			return "index.xhtml";
		}
	
		id = Long.valueOf(patientId);
		patient = patientService.getpatientById(id);
		
		address = patient.getAddress();
		generalPrescriptions = diagnoseService.getGeneralPrescriptionsBypatientId(id);
		 
		 yangshiPrescriptions = diagnoseService.getYangshiPresecriptons();
		 if(yangshiPrescriptions != null && yangshiPrescriptions.size() > 0){
		   currentSelectedPrescription = yangshiPrescriptions.get(0);
		 }else{
			 cleanUp();
		 }
		
		if(currentSelectedPrescription != null){
			 yangshiProcesses = diagnoseService.getYangshiProcessByPrescriptionIdAndPeronId(id, currentSelectedPrescription.getId());
		}
		
	    return "diagnose.xhtml";
	}
	
	private void cleanUp(){
		 currentSelectedPrescription = null;
		 yangshiProcesses = null;
	}
	

	public boolean isYangshi() {
		yangshi = useYangshi.equals("no") ? false : true;
		return yangshi;
	}
	
	private boolean isLastProcessToBeRemove(){
		YangshiProcess lastProcess = yangshiProcesses.get(yangshiProcesses.size() - 1);
		for(SubProcess s : lastProcess.getSubProcesses()){
			if(s.getOrderNum() == 1 && s.isEditMode() && s.getCreatedDate() == null){
				return true;
			}
		}
		return false;
	}
	
	public String recoverSubProcess(){
		FacesContext context = FacesContext.getCurrentInstance(); 
		String subProcessId = context.getExternalContext().getRequestParameterMap().get("subProcessId");
		String processId = context.getExternalContext().getRequestParameterMap().get("processId");
		YangshiProcess yangshiProcess = null;
		for(YangshiProcess p : yangshiProcesses){
			if(p.getId().equals(Long.valueOf(processId))){
				yangshiProcess = p;
			}
		}
		if(isLastProcessToBeRemove()){
			YangshiProcess lastProcess = yangshiProcesses.get(yangshiProcesses.size() - 1);
			  diagnoseService.removeProcess(lastProcess.getId());
			  yangshiProcess.setFinished(0);
			  diagnoseService.reSetYangshiProcessFinished(yangshiProcess);
		}
		
		Long subId = Long.valueOf(subProcessId);
			
		diagnoseService.removeSubProcess(subId);
		
		if(currentSelectedPrescription != null){
			 yangshiProcesses = diagnoseService.getYangshiProcessByPrescriptionIdAndPeronId(id, currentSelectedPrescription.getId());
		}
		return null;
	}

	public void setYangshi(boolean yangshi) {
		this.yangshi = yangshi;
	}

	public YangshiPrescription getCurrentSelectedPrescription() {
		return currentSelectedPrescription;
	}

	public void setCurrentSelectedPrescription(
			YangshiPrescription currentSelectedPrescription) {
		this.currentSelectedPrescription = currentSelectedPrescription;
	}
	
    public void addNewGeneral(){
    	newGeneralPrescription = new GeneralPrescription(id);
    }
	 
	public void valueChanged(ValueChangeEvent e){
		useYangshi = e.getNewValue().toString();
	}
	
	public void prescriptionChanged(ValueChangeEvent e){
		String prescriptionId = e.getNewValue().toString();
		Long pid = Long.valueOf(prescriptionId);
		for(YangshiPrescription p : yangshiPrescriptions){
			if(p.getId().equals(pid)){
				currentSelectedPrescription = p;
				 yangshiProcesses = diagnoseService.getYangshiProcessByPrescriptionIdAndPeronId(id, currentSelectedPrescription.getId());
			}
		}
	}
	
	
   public String saveProcess(){
    	saveYangshi();
    	return "diagnose.xhtml";
    }
    
    private void saveYangshi(){
    	YangshiProcess lastProcess = yangshiProcesses.get(yangshiProcesses.size() - 1);
    	diagnoseService.updateYanshiProcess(lastProcess);
    	if(currentSelectedPrescription != null){
			 yangshiProcesses = diagnoseService.getYangshiProcessByPrescriptionIdAndPeronId(id, currentSelectedPrescription.getId());
		}
    }

	public void saveDiagnose(){
		
		if(yangshi){
			saveYangshi();
		}else{
			if(newGeneralPrescription != null && !StringUtils.isNullOrEmpty(newGeneralPrescription.getPrescriptionText())){
				diagnoseService.insertGeneralPrescription(newGeneralPrescription);
				generalPrescriptions = diagnoseService.getGeneralPrescriptionsBypatientId(id);
				newGeneralPrescription = null;
			}
		}
		patientService.updateDiagnose(patient);
	}

   

	public String getUseYangshi() {
		return useYangshi;
	}

	public void setUseYangshi(String useYangshi) {
		this.useYangshi = useYangshi;
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

	public List<GeneralPrescription> getGeneralPrescriptions() {
		return generalPrescriptions;
	}

	public void setGeneralPrescriptions(
			List<GeneralPrescription> generalPrescriptions) {
		this.generalPrescriptions = generalPrescriptions;
	}

	public List<YangshiPrescription> getYangshiPrescriptions() {
		return yangshiPrescriptions;
	}

	public void setYangshiPrescriptions(
			List<YangshiPrescription> yangshiPrescriptions) {
		this.yangshiPrescriptions = yangshiPrescriptions;
	}

	public List<YangshiProcess> getYangshiProcesses() {
		return yangshiProcesses;
	}
	
	public void saveProcess(YangshiProcess process){
		if(process.getId() == null){
			diagnoseService.insertYanshiProcess(process);
		}else{
			diagnoseService.updateYanshiProcess(process);
		}
	}

	public void setYangshiProcesses(List<YangshiProcess> yangshiProcesses) {
		this.yangshiProcesses = yangshiProcesses;
	}
	
	public GeneralPrescription getNewGeneralPrescription() {
		return newGeneralPrescription;
	}

	public void setNewGeneralPrescription(GeneralPrescription newGeneralPrescription) {
		this.newGeneralPrescription = newGeneralPrescription;
	}
	
	public YangshiPrescription getNewYangshiPrescription() {
		return newYangshiPrescription;
	}

	public void setNewYangshiPrescription(YangshiPrescription newYangshiPrescription) {
		this.newYangshiPrescription = newYangshiPrescription;
	}



}
