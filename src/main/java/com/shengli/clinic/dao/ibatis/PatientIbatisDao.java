package com.shengli.clinic.dao.ibatis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.shengli.clinic.dao.PatientDao;
import com.shengli.clinic.model.Address;
import com.shengli.clinic.model.Patient;

@Repository(PatientDao.BIN_ID)
public class PatientIbatisDao extends SqlMapClientDaoSupport implements PatientDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getAllpatient(String firstName, String lastName, String phone) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("firstName", firstName);
		params.put("lastName", lastName);
		params.put("phone", phone);
		return (List<Patient>)getSqlMapClientTemplate().queryForList("getAllpatient", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getpatientByFirstName(String firstName) {
		Map<String, Object> params = new LinkedHashMap<String, Object>(1);
		params.put("firstName", firstName);
		return (List<Patient>)getSqlMapClientTemplate().queryForList("getpatientByFirstName", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getpatientByLastName(String lastName) {
		 Map<String, Object> params = new LinkedHashMap<String, Object>(1);
		 params.put("lastName", lastName);
		return (List<Patient>)getSqlMapClientTemplate().queryForList("getpatientByLastName", params);
	}

	@Override
	public Patient getpatientById(Long id) {
		 Map<String, Object> params = new LinkedHashMap<String, Object>(1);
		 params.put("id", id);
		return (Patient)getSqlMapClientTemplate().queryForObject("getpatientById", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getpatientByPhoneNumber(String phone) {
		 Map<String, Object> params = new LinkedHashMap<String, Object>(1);
		 params.put("phone", phone);
		return (List<Patient>)getSqlMapClientTemplate().queryForList("getpatientByLastName", params);
	}
	
	@Override
	public void createpatient(Patient patient){
		 getSqlMapClientTemplate().insert("insertpatient", patient);	
	}
	
	@Override
	public void  createAddress(Address address){
		 getSqlMapClientTemplate().insert("insertAddress", address);
	}
	
	@Override
	public void updatepatient(Patient patient){
		getSqlMapClientTemplate().update("updatepatient", patient);
	}
	
	@Override
	public void updateAddress(Address address){
		getSqlMapClientTemplate().update("updateAddress", address);
	}

}
