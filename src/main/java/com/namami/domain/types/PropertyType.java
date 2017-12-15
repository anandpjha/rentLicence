package com.namami.domain.types;

public enum PropertyType {

	APARTMENT			(1),
	INDEPENDENT_HOUSE	(2),
	;
	

	private final Integer value;
	
	PropertyType(Integer propertyType) {
		this.value = propertyType;
	}
	
	public Integer getPropertyType() {
		return value;
	}
	
}
