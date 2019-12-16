package com.shengli.clinic.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.shengli.clinic.dao.DiagnoseDao;
import com.shengli.clinic.model.GeneralPrescription;
import com.shengli.clinic.model.SubProcess;
import com.shengli.clinic.model.YangshiPrescription;
import com.shengli.clinic.model.YangshiProcess;
import com.shengli.clinic.services.DiagnoseService;


@Service(DiagnoseService.BEAN_ID)
public class DiagonoseServiceImpl implements DiagnoseService {
	
	@Autowired DiagnoseDao diagnoseDao;
	
	private static final int SUB_PROCESS_NUM = 10;

	@Override
	public List<GeneralPrescription> getGeneralPrescriptionsBypatientId(Long patientId) {
		return diagnoseDao.getGeneralPrescriptionsBypatientId(patientId);
	}

	@Override
	public List<YangshiProcess> getYangshiProcessByPrescriptionIdAndPeronId(
			Long patientId, Long prescriptionId) {
		
		List<YangshiProcess> list = new ArrayList<YangshiProcess>();
		
		list = diagnoseDao.getYangshiProcessByPrescriptionIdAndPeronId(patientId, prescriptionId);
		if(isCreateNew(list)){
			int order = list.size() == 0 ? 1 : list.size() + 1;
			YangshiProcess process = new YangshiProcess(order, patientId, prescriptionId);
			diagnoseDao.insertYanshiProcess(process);
		}
		
		list = diagnoseDao.getYangshiProcessByPrescriptionIdAndPeronId(patientId, prescriptionId);
		
		for(YangshiProcess process : list){
			
			if(list.get(list.size() - 1) == process ){
				int size = process.getSubProcesses().size();
				for(int i = 0; i < size; i++){
					process.getSubProcesses().get(i).setEditMode(false);
					if(i == size -1){
						process.getSubProcesses().get(i).setRecoverable(true);
					}
				}
				
				for(int i = size; i < SUB_PROCESS_NUM; i++){
			    	SubProcess sub2 = new SubProcess();
			    	sub2.setOrderNum(i+1);
			    	if(sub2.getOrderNum() == size + 1){
			    	   sub2.setEditMode(true);
			    	   sub2.setProcessValue("");
			    	}else{
			    		sub2.setEditMode(false);
			    	}
			    	
			    	process.getSubProcesses().add(sub2);
			    }
				
			}else{
				for(SubProcess sub : process.getSubProcesses()){
					YangshiProcess lastProcess = list.get(list.size() - 1);
					
					if((list.indexOf(process) == list.size() - 2) && 
							(lastProcess != null && lastProcess.getSubProcesses().size() == 0 && sub.getOrderNum() == 10)){
						sub.setRecoverable(true);
					}
					sub.setEditMode(false);
				}
			}
		}
		
	
		return list;
	}
	

	private boolean isCreateNew(List<YangshiProcess> list){
		if(list == null || list.size() == 0 || 
				(list.get(list.size()-1).getSubProcesses() != null && list.get(list.size()-1).getSubProcesses().size() == 10) ){
			return true;
		}
		
	    return false;
	}

	@Override
	public List<SubProcess> getSubProcessesByProcessId(Long processId) {
		return diagnoseDao.getSubProcessesByProcessId(processId);
	}

	@Override
	public void insertSubProcess(SubProcess subProcess) {
		diagnoseDao.insertSubProcess(subProcess);

	}

	@Override
	public void updateSubProcess(SubProcess subProcess) {
		diagnoseDao.updateSubProcess(subProcess);

	}

	@Override
	public void insertYanshiProcess(YangshiProcess yangshiProcess) {
		diagnoseDao.insertYanshiProcess(yangshiProcess);

	}

	@Override
	public void updateYanshiProcess(YangshiProcess yangshiProcess) {
		SubProcess sub = null;
    	List<SubProcess> list = yangshiProcess.getSubProcesses();
    	Collections.sort(list);
    	for(SubProcess s : list){
    		if(s.getId() == null && !StringUtils.isNullOrEmpty(s.getProcessValue())){
    			sub = s;
    			sub.setYangshiProcessId(yangshiProcess.getId());
    			break;
    		}
    	}
    	if(sub != null && !StringUtils.isNullOrEmpty(sub.getProcessValue())){
    		diagnoseDao.insertSubProcess(sub);
    	}
    	
    	if(list != null && list.size() == 10 && sub!= null && sub.getOrderNum() == 10){
    		yangshiProcess.setFinished(1);
    		diagnoseDao.updateYanshiProcess(yangshiProcess);
    	}
	

	}
	
	@Override
	public void reSetYangshiProcessFinished(YangshiProcess yangshiProcess){
		diagnoseDao.updateYanshiProcess(yangshiProcess);
	}
	

	@Override
	public List<YangshiPrescription> getYangshiPresecriptons() {
		return diagnoseDao.getYangshiPresecriptons();
	}
	
	@Override
	public void updatePrescription(YangshiPrescription yangshiPrescription){
		diagnoseDao.updatePrescription(yangshiPrescription);
	}

	@Override
	public void insertGeneralPrescription(GeneralPrescription generalPrescription) {
		diagnoseDao.insertGeneralPrescription(generalPrescription);
	}

	@Override
	public void insertYangshiPrescription(YangshiPrescription yangshiPrescription) {
		diagnoseDao.insertYangshiPrescription(yangshiPrescription);
	}

	@Override
	public void removePrescription(Long id) {
		diagnoseDao.removeYangshiPrescription(id);
	}

	@Override
	public void removeSubProcess(Long id) {
		diagnoseDao.removeSubProcess(id);		
	}

	@Override
	public void removeProcess(Long id) {
		diagnoseDao.removeProcess(id);
	}

}
