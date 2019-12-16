package com.shengli.clinic.dao.ibatis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.shengli.clinic.dao.DiagnoseDao;
import com.shengli.clinic.model.GeneralPrescription;
import com.shengli.clinic.model.SubProcess;
import com.shengli.clinic.model.YangshiPrescription;
import com.shengli.clinic.model.YangshiProcess;

public class DiagnoseIbatisDao  extends SqlMapClientDaoSupport implements DiagnoseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralPrescription> getGeneralPrescriptionsBypatientId(Long patientId) {
		return (List<GeneralPrescription>)getSqlMapClientTemplate().queryForList("getGeneralPrescriptionsByPatientId", patientId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YangshiProcess> getYangshiProcessByPrescriptionIdAndPeronId(
			Long patientId, Long prescriptionId) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("patientId", patientId);
		params.put("prescriptionId", prescriptionId);
		return (List<YangshiProcess>)getSqlMapClientTemplate().queryForList("getYangshiProBypatientIdAndPrescId", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubProcess> getSubProcessesByProcessId(Long processId) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("yangshiProcessId", processId);
		return (List<SubProcess>)getSqlMapClientTemplate().queryForList("getSubProcessByProcessId", params);
	}
	
	public void updatePrescription(YangshiPrescription yangshiPrescription){
		getSqlMapClientTemplate().update("updateYangshiPrescription", yangshiPrescription);
	}

	@Override
	public void insertSubProcess(SubProcess subProcess) {
		getSqlMapClientTemplate().insert("insertSubProcess", subProcess);
	}

	@Override
	public void updateSubProcess(SubProcess subProcess) {
		getSqlMapClientTemplate().update("updateASubProcessById", subProcess);
	}

	@Override
	public void insertYanshiProcess(YangshiProcess yangshiProcess) {
		getSqlMapClientTemplate().insert("insertYangshiProcesses", yangshiProcess);
	
		
	}

	@Override
	public void updateYanshiProcess(YangshiProcess yangshiProcess) {
		getSqlMapClientTemplate().update("updateYangshiProcesses", yangshiProcess);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YangshiPrescription> getYangshiPresecriptons() {
		return (List<YangshiPrescription>)getSqlMapClientTemplate().queryForList("getAllYangshiPrescriptions");
	}

	@Override
	public void insertGeneralPrescription(GeneralPrescription generalPrescription) {
		getSqlMapClientTemplate().insert("insertGeneralPrescription", generalPrescription);
		
	}

	@Override
	public void insertYangshiPrescription(YangshiPrescription yangshiPrescription) {
		getSqlMapClientTemplate().insert("insertYangshiPrescription", yangshiPrescription);
		
	}
	
	@Override
	public Long getLastInsertedId(){
		return (Long)getSqlMapClientTemplate().queryForObject("lastInsertId");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeYangshiPrescription(Long id) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		System.out.println(" **** remvoe 3 : prescription Id: " + id);
		params.put("prescriptionId",id);
		List<YangshiProcess> processes = getSqlMapClientTemplate().queryForList("getYangshiProByPrescriptionId", params);
		
		params = null;
		for(YangshiProcess process : processes){
			params = new LinkedHashMap<String, Object>();
			params.put("processId", process.getId());
			getSqlMapClientTemplate().delete("removeSubProcessByProcessId", params);
			System.out.println(" **** remvoe sub processes ");
		}
		
		params = null;
		params = new LinkedHashMap<String, Object>();
		params.put("prescriptionId",id);
		getSqlMapClientTemplate().delete("removeProcessByPrescriptionId", params);
		System.out.println(" **** remvoe processes ");
		
		params = new LinkedHashMap<String, Object>();
		params.put("prescriptionId", id);
		getSqlMapClientTemplate().delete("removeYangshiPrescriptionById", params);
		System.out.println(" **** finally remove prescription " + id);
	}

	@Override
	public void removeSubProcess(Long id) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", id);
		System.out.println("******* remove subid: " + id);
		getSqlMapClientTemplate().delete("removeSubProcessById", params);
	}
	
	@Override
	public void removeProcess(Long id){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", id);
		getSqlMapClientTemplate().delete("removeProcessById", params);
	}

	

}
