package com.shengli.clinic.model;


import java.util.Date;
import java.util.List;

public class Patient extends EntityBase {
	
	private static final long serialVersionUID = 8150326276567343959L;
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private Integer age;
	
	private String email;
	
	private String companyName;
	
	private Address address;
	
    private String phone;
    
    private String mainProblem;
    
    private String currentSickHistory;
    
    private String familySickHistory;
    
    private String diagnose;
    
    private String therapy;
    
    private String inspect;
    
    private Date createdDate;
    
    
    
    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private List<GeneralPrescription> generalPrescription;
    
    private List<YangshiProcess> yangshiProcesses;
    
	public List<YangshiProcess> getYangshiProcesses() {
		return yangshiProcesses;
	}

	public void setYangshiProcesses(List<YangshiProcess> yangshiProcesses) {
		this.yangshiProcesses = yangshiProcesses;
	}

	public List<GeneralPrescription> getGeneralPrescription() {
		return generalPrescription;
	}

	public void setGeneralPrescription(List<GeneralPrescription> generalPrescription) {
		this.generalPrescription = generalPrescription;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMainProblem() {
		return mainProblem;
	}

	public void setMainProblem(String mainProblem) {
		this.mainProblem = mainProblem;
	}

	public String getCurrentSickHistory() {
		return currentSickHistory;
	}

	public void setCurrentSickHistory(String currentSickHistory) {
		this.currentSickHistory = currentSickHistory;
	}

	public String getFamilySickHistory() {
		return familySickHistory;
	}

	public void setFamilySickHistory(String familySickHistory) {
		this.familySickHistory = familySickHistory;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getTherapy() {
		return therapy;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}

	public String getInspect() {
		return inspect;
	}

	public void setInspect(String inspect) {
		this.inspect = inspect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String debugPrint(){
		StringBuilder b = new StringBuilder();
		b.append(" firstName: ").append(firstName);
		b.append(" lastName: ").append(lastName);
		b.append(" age: ").append(age);
		b.append(" phone : ").append(phone);
		if(address != null){
			b.append(address.debugPrint());
		}
		b.append(" mainProblem: ").append(mainProblem);
		b.append(" sickHistory: ").append(currentSickHistory);
		b.append(" FamilySickHistory: ").append(familySickHistory);
		b.append(" diagnose: ").append(diagnose);
		b.append(" inspect: ").append(inspect);
		b.append(" therapy :" ).append(therapy);
		return b.toString();
	}
}
