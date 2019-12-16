package com.shengli.clinic.model;

public class Address extends EntityBase {
	

	private static final long serialVersionUID = -3237685284930270459L;
	
	private Long id;
	private String streetAddress;
	private String city;
	private String province;
	private String county;
	private String postalCode;
	private Long patientId;
	
	public Long getpatientId() {
		return patientId;
	}

	public void setpatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	

	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String debugPrint(){
		StringBuilder b = new StringBuilder();
		b.append(" streetAddress: ").append(streetAddress);
		b.append(" county: ").append(county);
		b.append(" city: ").append(city);
		b.append(" province : ").append(province);
		b.append(" postalcode : ").append(postalCode);
		return b.toString();
	}
	
	
}
