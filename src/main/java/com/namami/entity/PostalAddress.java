package com.namami.entity;

/**
 * @author Anand Jha
 * 
 * PostalAddressDto entity class 
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.namami.domain.types.PropertyIdentityType;
import com.namami.domain.types.PropertyType;

@Entity(name = "postal_address")
//@SequenceGenerator(name = "POSTALADDRESS_SEQUENCE", sequenceName = "POSTALADDRESS_SEQUENCE", allocationSize = 1, initialValue = 0)
public class PostalAddress extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer postalAddressId;
	
	/**
	 * Flat, Individual House
	 */
	private String propertyType;

	/**
	 * Value of propertyNumber will be based on the propertyType.
	 * eg. FlatNo, houseNo, etc..
	 */
	private String propertyNumber;
	
	private Integer propertyIdentityType;
	
	/**
	 * Value of this field will depend on propertyIdentityType.
	 * eg. CTS no, Gata no, etc
	 */
	private String propertyIdentity;
	
	private Integer builtUpArea;

	private Integer floorNo;
	
	private String buildingName;
	
	private String road;
	
	private String city;
	
	private String district;
		
	private String state;
	
	private String pinCode;
	
	private String tahasil;
	
	private String municipal;

	
	public String getTahasil() {
		return tahasil;
	}

	public void setTahasil(String tahasil) {
		this.tahasil = tahasil;
	}

	public String getMunicipal() {
		return municipal;
	}

	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}


	public Integer getPostalAddressId() {
		return postalAddressId;
	}

	public void setPostalAddressId(Integer postalAddressId) {
		this.postalAddressId = postalAddressId;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public Integer getPropertyIdentityType() {
		return propertyIdentityType;
	}

	public void setPropertyIdentityType(Integer propertyIdentityType) {
		this.propertyIdentityType = propertyIdentityType;
	}

	public String getPropertyIdentity() {
		return propertyIdentity;
	}

	public void setPropertyIdentity(String propertyIdentity) {
		this.propertyIdentity = propertyIdentity;
	}

	public Integer getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Integer builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
}
