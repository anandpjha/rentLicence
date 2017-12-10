package com.namami.service;

public enum PersonType {

	OWNER(1),
	
	TENANT(2),
	
	WITNESS(3);
	
	private int value;
	
	PersonType(int value) {
		this.value = value;
	}

	public int getTypeValue() {
		return value;
	}
}
