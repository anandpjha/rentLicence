package com.namami.service;

import com.namami.bo.AddPersonRequest;
import com.namami.bo.PersonDto;
import com.namami.entity.Person;

/**
 * @author Anand Jha
 * 
 */
public interface PersonService {

	public PersonDto addPerson(AddPersonRequest request, PersonType personType);
	
	public PersonDto retrivePerson(String userId);

	public PersonDto updatePerson(AddPersonRequest request);

	public void deletePerson(AddPersonRequest addPersonRequest);

}
