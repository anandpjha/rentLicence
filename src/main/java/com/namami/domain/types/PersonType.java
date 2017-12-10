package com.namami.domain.types;

public enum PersonType {
	
	OWNER	(1),
	TENANT	(2),
	WITNESS	(3)
	;
	

	private final Integer value;
	
	PersonType(Integer personType) {
		this.value = personType;
	}
	
	public Integer getPersonType() {
		return value;
	}
	
}
