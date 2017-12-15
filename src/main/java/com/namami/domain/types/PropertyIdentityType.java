package com.namami.domain.types;

public enum PropertyIdentityType {

	PLOT_NO			(1),
	CTS_NO			(2),
	SURVEY_NO		(3),
	GATA_NO			(4),
	;
	

	private final Integer value;
	
	PropertyIdentityType(Integer propertyType) {
		this.value = propertyType;
	}
	
	public Integer getPropertyType() {
		return value;
	}
	
}

