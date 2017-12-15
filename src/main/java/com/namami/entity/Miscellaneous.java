package com.namami.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Anand Jha
 */

@Entity
@Table(name = "miscellaneous")
public class Miscellaneous extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long miscellaneousId;

	private Integer agreementId;
	
	private String miscellaneousTerms;
	
	private String regChargePaidByType;
	
	private String petAllowType;
	
	private boolean furnitureAndAppliancesSelected;
	
	private Integer fans;
	
	private Integer tubeLight;
	
	private Integer bed;
	
	private Integer sofa;
	
	private Integer table;
	
	private Integer chair;
	
	private Integer airConditioner;
	
	private Integer geyser;

	

	
	public boolean isFurnitureAndAppliancesSelected() {
		return furnitureAndAppliancesSelected;
	}

	public void setFurnitureAndAppliancesSelected(
			boolean furnitureAndAppliancesSelected) {
		this.furnitureAndAppliancesSelected = furnitureAndAppliancesSelected;
	}

	public Integer getFans() {
		return fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}

	public Integer getTubeLight() {
		return tubeLight;
	}

	public void setTubeLight(Integer tubeLight) {
		this.tubeLight = tubeLight;
	}

	public Integer getBed() {
		return bed;
	}

	public void setBed(Integer bed) {
		this.bed = bed;
	}

	public Integer getSofa() {
		return sofa;
	}

	public void setSofa(Integer sofa) {
		this.sofa = sofa;
	}

	public Integer getTable() {
		return table;
	}

	public void setTable(Integer table) {
		this.table = table;
	}

	public Integer getChair() {
		return chair;
	}

	public void setChair(Integer chair) {
		this.chair = chair;
	}

	public Integer getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(Integer airConditioner) {
		this.airConditioner = airConditioner;
	}

	public Integer getGeyser() {
		return geyser;
	}

	public void setGeyser(Integer geyser) {
		this.geyser = geyser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getMiscellaneousId() {
		return miscellaneousId;
	}

	public void setMiscellaneousId(long miscellaneousId) {
		this.miscellaneousId = miscellaneousId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public String getMiscellaneousTerms() {
		return miscellaneousTerms;
	}

	public void setMiscellaneousTerms(String miscellaneousTerms) {
		this.miscellaneousTerms = miscellaneousTerms;
	}

	public String getRegChargePaidByType() {
		return regChargePaidByType;
	}

	public void setRegChargePaidByType(String regChargePaidByType) {
		this.regChargePaidByType = regChargePaidByType;
	}

	public String getPetAllowType() {
		return petAllowType;
	}

	public void setPetAllowType(String petAllowType) {
		this.petAllowType = petAllowType;
	}

	

	
}