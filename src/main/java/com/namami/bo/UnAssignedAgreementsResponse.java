package com.namami.bo;

import java.util.List;

public class UnAssignedAgreementsResponse {
	
	private List<String> associates;
	
	private List<String> unAssignedAgreements;

	public List<String> getAssociates() {
		return associates;
	}

	public void setAssociates(List<String> associates) {
		this.associates = associates;
	}

	public List<String> getUnAssignedAgreements() {
		return unAssignedAgreements;
	}

	public void setUnAssignedAgreements(List<String> unAssignedAgreements) {
		this.unAssignedAgreements = unAssignedAgreements;
	}
	
	

}
