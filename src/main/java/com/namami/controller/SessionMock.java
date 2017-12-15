package com.namami.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.namami.bo.AgreementDto;
import com.namami.entity.Person;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMock {

	private List<AgreementDto> agreementList = new ArrayList<AgreementDto>();
	
	private Map<String, List<Person>> ownerMap = new HashMap<String, List<Person>>();
	
	private Map<String, List<Person>> tenantMap = new HashMap<String, List<Person>>();
	
	private Map<String, List<Person>> witnessesMap = new HashMap<String, List<Person>>();
	
	
	public void addAgreement(AgreementDto agreement) {
		agreementList.add(agreement);
	}
	
	public void addOwner(String agreementId, Person person) {
		if(ownerMap.get(agreementId) == null) {
			ownerMap.put(agreementId, new ArrayList<Person>());
		}
		List<Person> ownerList = ownerMap.get(agreementId);
		ownerList.add(person);
	}
	
	
	public void addTenant(String agreementId, Person person) {
		if(tenantMap.get(agreementId) == null) {
			tenantMap.put(agreementId, new ArrayList<Person>());
		}
		List<Person> tenantList = tenantMap.get(agreementId);
		tenantList.add(person);
	}
	
	
	public void addWitnesses(String agreementId, Person person) {
		if(witnessesMap.get(agreementId) == null) {
			witnessesMap.put(agreementId, new ArrayList<Person>());
		}
		List<Person> witnessesList = witnessesMap.get(agreementId);
		witnessesList.add(person);
	}
	
	public List<Person> getOwners(String agreementId) {
		if(ownerMap.get(agreementId) == null) {
			ownerMap.put(agreementId, new ArrayList<Person>());
		}
		return ownerMap.get(agreementId);
	}
	
	
	public List<Person> getTenants(String agreementId) {
		if(tenantMap.get(agreementId) == null) {
			tenantMap.put(agreementId, new ArrayList<Person>());
		}
		return tenantMap.get(agreementId);
	}
	
	
	public List<Person> getWitnesses(String agreementId) {
		if(witnessesMap.get(agreementId) == null) {
			witnessesMap.put(agreementId, new ArrayList<Person>());
		}
		return witnessesMap.get(agreementId);
	}
}
