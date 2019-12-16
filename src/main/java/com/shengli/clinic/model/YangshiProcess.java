package com.shengli.clinic.model;

import java.util.ArrayList;
import java.util.List;

public class YangshiProcess extends EntityBase implements Comparable<YangshiProcess> {

	private static final long serialVersionUID = -7259297676375324623L;
	
	private Long id;
    private int orderNum;
    private Long patientId;
    private int finished;
    private Long yangshiPrescriptionId;

  
	private List<SubProcess> subProcesses = new ArrayList<SubProcess>();
    
    public YangshiProcess(){
    	
    }
    
   
    
    public YangshiProcess(int orderNum, Long patientId, Long yangshiPrescriptionId) {
    	this.orderNum = orderNum;
    	this.patientId = patientId;
    	this.yangshiPrescriptionId = yangshiPrescriptionId;
    }
   
	
	public Long getYangshiPrescriptionId() {
		return yangshiPrescriptionId;
	}

	public void setYangshiPrescriptionId(Long yangshiPrescriptionId) {
		this.yangshiPrescriptionId = yangshiPrescriptionId;
	}


        
	public List<SubProcess> getSubProcesses() {
		return subProcesses;
	}

	public void setSubProcesses(List<SubProcess> subProcesses) {
		this.subProcesses = subProcesses;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public Long getpatientId() {
		return patientId;
	}

	public void setpatientId(Long patientId) {
		this.patientId = patientId;
	}



	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}



	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public int compareTo(YangshiProcess o) {
		return orderNum >= o.getOrderNum() ? 1 : -1;
	}

}
