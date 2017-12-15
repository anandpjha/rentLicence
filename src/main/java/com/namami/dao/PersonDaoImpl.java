package com.namami.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AddPersonRequest;
import com.namami.bo.PersonDto;
import com.namami.common.Converter;
import com.namami.entity.Person;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.PersonRepository;
import com.namami.service.PersonType;

/**
 * @author Anand Jha
 * 
 */
@Service ( "personDao" )
public class PersonDaoImpl implements PersonDao {
	
	
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private Converter converter;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(PersonDaoImpl.class);

	@Override
	public PersonDto addPerson(AddPersonRequest request, PersonType personType) {
		
		try{
			
		Person person = converter.preparePerson(request, personType);
		//converter.convertToPersonDto(person);
		return converter.convertToPersonDto(personRepository.saveAndFlush(person));
		
		}catch (Exception e){
			
			SLF4JLOGGER.error("addPerson :: Error while saving person in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while saving person in DB" );
			
		}
	}


	@Override
	public PersonDto updatePerson(AddPersonRequest request, PersonType personType) {
		
		//Person personUpdated = converter.preparePerson(request, personType);
		try{
		Person person = personRepository.findByPersonId(new Integer(request.getPersonId())).get(0);
		
		if(null != person){
			person.setPersonId(request.getPersonId());
			person.setAadharUid(request.getAadharUid());
			person.setEmailAddress(request.getEmail());
			person.setPersonName(request.getPersonName());
			person.setPersonAge(request.getPersonAge());
			person.setOccupation(request.getOccupation());
			person.setGender(request.getGender());
			person.setMobNumber(request.getMobNumber());
			person.setPanNo(request.getPanNo());
			
		}else{
			
			SLF4JLOGGER.error("updatePerson :: Error while updating person in DB: Person Id not found in DB");
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue() , "Error while updating person in DB : Person Id not found in DB" );
			
		}
			return converter.convertToPersonDto(personRepository.saveAndFlush(person));
		
		}catch (Exception e){
			
			SLF4JLOGGER.error("updatePerson :: Error while updating person in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while updating person in DB" );
			
		}
		
	}

	@Override
	public PersonDto retrivePerson(String userId) {
			Person person = personRepository.findByPersonId(new Integer(userId)).get(0);
			return converter.convertToPersonDto(person);
		}

	@Override
	public void deletePerson(Integer personId) {
		
		try{
			
			personRepository.delete(personId);
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("deletePerson :: Error while deleting person in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while deliting person in DB" );
			
		}
		
	}

}
