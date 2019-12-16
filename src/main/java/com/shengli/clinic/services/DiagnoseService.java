package com.shengli.clinic.services;

import java.util.List;

import com.shengli.clinic.model.GeneralPrescription;
import com.shengli.clinic.model.SubProcess;
import com.shengli.clinic.model.YangshiPrescription;
import com.shengli.clinic.model.YangshiProcess;

public interface DiagnoseService {
	String BEAN_ID = "diagnoseService";
	
	public List<GeneralPrescription> getGeneralPrescriptionsBypatientId(Long patientId);
	
	public List<YangshiProcess> getYangshiProcessByPrescriptionIdAndPeronId(Long patientId, Long prescriptionId);
	
	public List<SubProcess> getSubProcessesByProcessId(Long processId);
	
	public void insertSubProcess(SubProcess subProcess);
	
	public void updateSubProcess(SubProcess subProcess);
	
	public void insertYanshiProcess(YangshiProcess yangshiProcess);
	
	public void insertGeneralPrescription(GeneralPrescription generalPrescription);
	
	public void insertYangshiPrescription(YangshiPrescription yangshiPrescription);
	
	public void updateYanshiProcess(YangshiProcess yangshiProcess);
	
	public List<YangshiPrescription> getYangshiPresecriptons();
	
	public void updatePrescription(YangshiPrescription yangshiPrescription);
	
	public void removePrescription(Long id);
	
	public void removeSubProcess(Long id);
	
	public void removeProcess(Long id);
	
	public void reSetYangshiProcessFinished(YangshiProcess yangshiProcess);

}
