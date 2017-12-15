package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * AgreementTracker entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agreement_tracker")
//@SequenceGenerator(name = "AGREEMENTTRACKER_SEQUENCE", sequenceName = "AGREEMENTTRACKER_SEQUENCE", allocationSize = 1, initialValue = 0)
public class AgreementTracker extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer trackerId;
	
	private Integer agreementId;
	
	private Integer agreementTrackerStatusId;
	
	private Integer agreementStatusId;

	public Integer getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(Integer trackerId) {
		this.trackerId = trackerId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getAgreementTrackerStatusId() {
		return agreementTrackerStatusId;
	}

	public void setAgreementTrackerStatusId(Integer agreementTrackerStatusId) {
		this.agreementTrackerStatusId = agreementTrackerStatusId;
	}

	public Integer getAgreementStatusId() {
		return agreementStatusId;
	}

	public void setAgreementStatusId(Integer agreementStatusId) {
		this.agreementStatusId = agreementStatusId;
	}
	
	
	
	
	}
