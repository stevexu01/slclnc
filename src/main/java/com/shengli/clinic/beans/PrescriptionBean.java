package com.shengli.clinic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.shengli.clinic.util.StringUtils;
import com.shengli.clinic.model.YangshiPrescription;

import com.shengli.clinic.services.DiagnoseService;

@Component
@RequestScoped
@ManagedBean
public class PrescriptionBean extends BaseBean{

	
	@Autowired
	DiagnoseService diagnoseService;
	
	private List<YangshiPrescription> yangshiPrescriptions = new ArrayList<YangshiPrescription>();
	
	private YangshiPrescription newYangshiPrescription = new YangshiPrescription();
	
	private boolean createSucess;
	private boolean updateSucess;
	private boolean removeSucess;
	
	public String addYangshi(){
		newYangshiPrescription.setPrescriptionText("test");
		
		if(StringUtils.isNullOrEmpty(newYangshiPrescription.getPrescriptionName())){
			FacesContext context = FacesContext.getCurrentInstance(); 
			String msg = BaseBean.getMessageFromResource("message.messages", "prescription.name.required", null, context.getViewRoot().getLocale());
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,msg);
				FacesContext.getCurrentInstance().addMessage("form:newName", fm);//clientID - "errmsg", id of the UIComponent
				return null;
		}
		diagnoseService.insertYangshiPrescription(newYangshiPrescription);
		loadPage();
		createSucess = true;
		return null;
	}
	
	public void recover(){
		FacesContext context = FacesContext.getCurrentInstance();                         
		String prescriptionId = context.getExternalContext().getRequestParameterMap().get("prescriptionId");
		Long id = Long.valueOf(prescriptionId.trim());
	
		for(YangshiPrescription p : yangshiPrescriptions){
			if(p.getId().equals(id)){
				p.setEditable(false);
			}
		}
	}
	
	public void edit(){
		FacesContext context = FacesContext.getCurrentInstance();                         
		String prescriptionId = context.getExternalContext().getRequestParameterMap().get("prescriptionId");
		Long id = Long.valueOf(prescriptionId.trim());
	
		for(YangshiPrescription p : yangshiPrescriptions){
			if(p.getId().equals(id)){
				p.setEditable(true);
			}
		}
		
	}
	
	public String update(){
		FacesContext context = FacesContext.getCurrentInstance();                         
		String prescriptionId = context.getExternalContext().getRequestParameterMap().get("prescriptionId");
		Long id = Long.valueOf(prescriptionId);

		for(YangshiPrescription p : yangshiPrescriptions){
			if(p.getId().equals(id)){
				
				  diagnoseService.updatePrescription(p);
				  p.setEditable(false);
				
			}
		}
		
		loadPage();
		updateSucess = true;
	
		return "prescription-management.xhtml";
	}
	
	public String remove(){
		FacesContext context = FacesContext.getCurrentInstance();   
	
		String prescriptionId = context.getExternalContext().getRequestParameterMap().get("prescriptionId");
		Long id = Long.valueOf(prescriptionId);
	
		for(YangshiPrescription p : yangshiPrescriptions){
			if(p.getId().equals(id)){
				diagnoseService.removePrescription(id);
			}
		}
		
		loadPage();
	    removeSucess = true;
		return "prescription-management.xhtml";
	}
	

	public String loadPage(){
		yangshiPrescriptions = diagnoseService.getYangshiPresecriptons();
		newYangshiPrescription = new YangshiPrescription();
		return "prescription-management.xhtml";
	}
	
	public List<YangshiPrescription> getYangshiPrescriptions() {
		if(yangshiPrescriptions.size() == 0){
			yangshiPrescriptions = diagnoseService.getYangshiPresecriptons();
		}
		return yangshiPrescriptions;
	}
	
	public void setYangshiPrescriptions(
			List<YangshiPrescription> yangshiPrescriptions) {
		this.yangshiPrescriptions = yangshiPrescriptions;
	}
	
	public YangshiPrescription getNewYangshiPrescription() {
		return newYangshiPrescription;
	}
	
	public void setNewYangshiPrescription(YangshiPrescription newYangshiPrescription) {
		this.newYangshiPrescription = newYangshiPrescription;
	}

	public boolean isCreateSucess() {
		return createSucess;
	}

	public void setCreateSucess(boolean createSucess) {
		this.createSucess = createSucess;
	}

	public boolean isUpdateSucess() {
		return updateSucess;
	}

	public void setUpdateSucess(boolean updateSucess) {
		this.updateSucess = updateSucess;
	}

	public boolean isRemoveSucess() {
		return removeSucess;
	}

	public void setRemoveSucess(boolean removeSucess) {
		this.removeSucess = removeSucess;
	}
	

}
