package com.shengli.clinic.model;

import java.util.Date;

public class SubProcess extends EntityBase implements Comparable<SubProcess>{

    
	private static final long serialVersionUID = -8370434421445363444L;
	
	private Long id;
	private int orderNum;
	private String processValue;
	private Date createdDate;
	private Long yangshiProcessId;
	private boolean editMode;
    private boolean recoverable;
	
	public SubProcess(){
		
	}
	
	public SubProcess(int orderNum, Long yangshiProcessId){
		this.orderNum = orderNum;
		this.yangshiProcessId = yangshiProcessId;
	}
	
	
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getProcessValue() {
		return processValue;
	}

	public void setProcessValue(String processValue) {
		this.processValue = processValue;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getYangshiProcessId() {
		return yangshiProcessId;
	}

	public void setYangshiProcessId(Long yangshiProcessId) {
		this.yangshiProcessId = yangshiProcessId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(SubProcess o) {
		
		return orderNum > o.getOrderNum() ? 1 : 0;
	}

	public boolean isRecoverable() {
		return recoverable;
	}

	public void setRecoverable(boolean recoverable) {
		this.recoverable = recoverable;
	}
	
	

}
