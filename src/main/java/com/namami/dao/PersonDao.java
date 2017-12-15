package com.namami.dao;

import com.namami.bo.AddPersonRequest;
import com.namami.bo.PersonDto;
import com.namami.entity.Person;
import com.namami.service.PersonType;

/**
 * @author Anand Jha
 * 
 */
public interface PersonDao {

	public PersonDto addPerson(AddPersonRequest request, PersonType personType);

	public PersonDto updatePerson(AddPersonRequest request, PersonType personType);
	
	public PersonDto retrivePerson(String userId);

	public void deletePerson(Integer personId);

}
