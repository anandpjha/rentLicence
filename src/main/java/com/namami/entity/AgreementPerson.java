package com.namami.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agreement_person")
//@SequenceGenerator(name = "PERSONAGREEMENT_SEQUENCE", sequenceName = "PERSONAGREEMENT_SEQUENCE", allocationSize = 1, initialValue = 0)
public class AgreementPerson extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer personAgreementId;
	
	private Integer agreementId;
	
	private Integer personId;
	
	private int personType;
	
	private int personOrder;

	public Integer getPersonAgreementId() {
		return personAgreementId;
	}

	public void setPersonAgreementId(Integer personAgreementId) {
		this.personAgreementId = personAgreementId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public int getPersonType() {
		return personType;
	}

	public void setPersonType(int personType) {
		this.personType = personType;
	}

	public int getPersonOrder() {
		return personOrder;
	}

	public void setPersonOrder(int personOrder) {
		this.personOrder = personOrder;
	}
	
}
